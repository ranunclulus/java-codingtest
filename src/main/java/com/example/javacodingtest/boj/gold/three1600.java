package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.03
 @link https://www.acmicpc.net/problem/1600
 @timecomplex
 @performance 61028 KB,	432 MS
 @category
 @note
 */
public class three1600 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int k, w, h;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] monkeyMoves = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] horseMoves = new int[][] {{-1, -2}, {-2, -1}, {1, -2}, {2, -1},
            {-1, 2}, {-2, 1}, {1, 2}, {2, 1}};

    public void solution() throws IOException {
        k = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());
        w = Integer.parseInt(tokenizer.nextToken());
        h = Integer.parseInt(tokenizer.nextToken());
        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        visited = new boolean[h][w][k + 1];
        Deque<int[]> toVisit = new ArrayDeque<>();
        toVisit.offer(new int[] {0, 0, 0, k});
        builder.append(-1);
        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();

            if (now[0] == h - 1 && now[1] == w - 1) {
                builder = new StringBuilder();
                builder.append(now[2]);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = now[0] + monkeyMoves[i][0];
                int nextCol = now[1] + monkeyMoves[i][1];
                if (!boundary(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol][now[3]]) continue;
                if (map[nextRow][nextCol] == 1) continue;
                visited[nextRow][nextCol][now[3]] = true;
                toVisit.add(new int[] {nextRow, nextCol, now[2] + 1, now[3]});
            }
            if (now[3] > 0) {
                for (int i = 0; i < 8; i++) {
                    int nextRow = now[0] + horseMoves[i][0];
                    int nextCol = now[1] + horseMoves[i][1];
                    if (!boundary(nextRow, nextCol)) continue;
                    if (map[nextRow][nextCol] == 1) continue;
                    if (visited[nextRow][nextCol][now[3] - 1]) continue;
                    visited[nextRow][nextCol][now[3] - 1] = true;
                    toVisit.add(new int[] {nextRow, nextCol, now[2] + 1, now[3] - 1});
                }
            }
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private boolean boundary(int nextRow, int nextCol) {
        return !(nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w);
    }

    public static void main(String[] args) throws IOException {
        new three1600().solution();
    }

}
