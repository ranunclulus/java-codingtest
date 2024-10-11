package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.11
 @link
 @timecomplex
 @performance 107,844 kb, 1,550 ms
 @category
 @note
 */
public class none5656 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n, w, h;
    static int[][] map;
    static int rest;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[] selected;


    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            w = Integer.parseInt(tokenizer.nextToken());
            h = Integer.parseInt(tokenizer.nextToken());

            map = new int[h][w];
            rest = Integer.MAX_VALUE;
            for (int i = 0; i < h; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            selected = new int[n];
            makePermutation(0);
            builder.append("#").append(test).append(" ").append(rest).append("\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void makePermutation(int depth) {
        if (depth == n) {
            marbleGame(mapCopy(map));
            return;
        }

        for (int i = 0; i < w; i++) {
            selected[depth] = i;
            makePermutation(depth + 1);
        }
    }

    private void marbleGame(int[][] map) {

        for(int select : selected) {
            int[] position = getPosition(select, map);
            popping(position[0], position[1], map);
            falling(map);
        }
        int blockCount = countBlock(map);
        if (blockCount < rest) {
            rest = blockCount;
        }
    }

    private int[] getPosition(int select, int[][] map) {
        for (int i = 0; i < h; i++) {
            if (map[i][select] != 0) return new int[] {i, select};
        }
        return new int[] {h - 1, select};
    }

    private void falling(int[][] map) {
        for (int i = 0; i < w; i++) {
            Deque<Integer> column = new ArrayDeque<>();
            for (int j = 0; j < h; j++) {
                if (map[j][i] == 0) continue;
                column.add(map[j][i]);
                map[j][i] = 0;
            }

            int col = h - 1;
            while (!column.isEmpty() && col >= 0) {
                map[col][i] = column.pollLast();
                col--;
            }
        }
    }

    private void popping(int col, int row, int[][] map) {
        boolean[][] visited = new boolean[h][w];
        visited[col][row] = true;
        Deque<int[]> toPop = new ArrayDeque<>();
        toPop.add(new int[] {col, row});

        while (!toPop.isEmpty()) {
            int[] now = toPop.poll();
            int power = map[now[0]][now[1]];
            map[now[0]][now[1]] = 0;
            for (int p = 0; p < power; p++) {
                for (int i = 0; i < 4; i++) {
                    int nextCol = now[0] + deltas[i][0] * p;
                    int nextRow = now[1] + deltas[i][1] * p;

                    if (nextCol < 0 || nextCol >= h || nextRow < 0 || nextRow >= w) continue;
                    if (visited[nextCol][nextRow]) continue;

                    toPop.add(new int[] {nextCol, nextRow});
                    visited[nextCol][nextRow] = true;
                }
            }
        }
    }

    private int countBlock(int[][] map) {
        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] != 0) count++;
            }
        }
        return count;
    }

    private int[][] mapCopy(int[][] map) {
        int[][] result = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[i][j] = map[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        new none5656().solution();
    }
}

/*
1
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1

1
2 9 10
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
1 1 0 0 1 0 0 0 0
1 1 0 1 1 1 0 1 0
1 1 0 1 1 1 0 1 0
1 1 1 1 1 1 1 1 0
1 1 3 1 6 1 1 1 1
1 1 1 1 1 1 1 1 1

1
3 6 7
1 1 0 0 0 0
1 1 0 0 1 0
1 1 0 0 4 0
4 1 0 0 1 0
1 5 1 0 1 6
1 2 8 1 1 6
1 1 1 9 2 1
 */
