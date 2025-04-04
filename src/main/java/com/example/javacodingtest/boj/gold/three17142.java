package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.04.04
 @link https://www.acmicpc.net/problem/17142
 @timecomplex
 @performance 34028KB 224MS
 @category
 @note
 */
public class three17142 {
    class Virus {
        int row;
        int col;
        int time;

        Virus(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int N, M, zeroCount;
    static int[][] map;
    static List<Virus> viruses;
    static Virus[] active;
    static int[][] deltas = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int minTime = Integer.MAX_VALUE;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        map = new int[N][N];

        viruses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == 0) zeroCount++;
                if (map[i][j] == 2) viruses.add(new Virus(i, j, 0));
            }
        }

        if (zeroCount == 0) {
            minTime = 0;
        } else {
            active = new Virus[M];
            activeVirus(0, 0);
        }

        builder.append(minTime == Integer.MAX_VALUE ? -1 : minTime);
        writer.write(builder.toString());
        writer.flush();

    }

    private void activeVirus(int depth, int start) {
        if (depth == M) {
            spread();
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            active[depth] = viruses.get(i);
            activeVirus(depth + 1, i + 1);
        }
    }

    private void spread() {
        Deque<Virus> toSpread = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        for(Virus virus : active) {
            visited[virus.row][virus.col] = true;
            toSpread.add(virus);
        }

        int removeZeroCount = 0;
        while (!toSpread.isEmpty()) {
            Virus virus = toSpread.poll();

            for(int[] delta : deltas) {
                int nextRow = virus.row + delta[0];
                int nextCol = virus.col + delta[1];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
                if (visited[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] == 1) continue;
                if (map[nextRow][nextCol] == 0) removeZeroCount++;

                toSpread.add(new Virus(nextRow, nextCol, virus.time + 1));
                visited[nextRow][nextCol] = true;

                if (removeZeroCount == zeroCount) {
                    minTime = Math.min(minTime, virus.time + 1);
                    return;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new three17142().solution();
    }
}
