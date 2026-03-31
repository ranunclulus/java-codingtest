package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

public class four14502_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int N, M;
    static int[][] map;
    static int safeArea = Integer.MIN_VALUE;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solution() throws IOException {
        init();
        dfs(0);
        builder.append(safeArea);
        writer.write(builder.toString());
        writer.flush();
        writer.close();

    }

    public void dfs(int depth) {
        if (depth == 3) {
            bfs();
            return;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (map[i][j] != 0) continue;

                map[i][j] = 1;
                dfs(depth + 1);
                map[i][j] = 0;
            }
        }
    }

    public void bfs() {
        boolean[][] affected = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (map[i][j] != 2) continue;

                Deque<int[]> toVisit = new ArrayDeque<>();
                boolean[][] visited = new boolean[N][M];

                toVisit.add(new int[] {i, j});
                affected[i][j] = true;
                visited[i][j] = true;

                while(!toVisit.isEmpty()) {
                    int[] now = toVisit.poll();

                    for(int[] delta : deltas) {
                        int nextRow = now[0] + delta[0];
                        int nextCol = now[1] + delta[1];

                        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
                        if (visited[nextRow][nextCol]) continue;
                        if (map[nextRow][nextCol] != 0) continue;

                        visited[nextRow][nextCol] = true;
                        affected[nextRow][nextCol] = true;
                        toVisit.add(new int[] {nextRow, nextCol});
                    }
                }

            }
        }

        int safeCount = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (map[i][j] != 0) continue;
                if (!affected[i][j]) safeCount++;
            }
        }

        safeArea = Math.max(safeArea, safeCount);

    }

    public void init() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four14502_2().solution();
    }

}