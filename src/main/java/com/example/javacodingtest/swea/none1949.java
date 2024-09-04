package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2024.09.04
 @link
 @timecomplex
 @performance 20824 kb, 118 ms
 @category
 @note
 */
public class none1949 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum;
    static int n;
    static int k;
    static int[][] map;
    static int maxHeight;
    static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int maxLength;
    static boolean[][] visited;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1;  test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            k = Integer.parseInt(tokenizer.nextToken());
            map = new int[n][n];
            maxHeight = 0;
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }


            visited = new boolean[n][n];
            maxLength = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == maxHeight) {
                        visited[i][j] = true;
                        dfs(i, j, map[i][j], 1, false);
                        visited[i][j] = false;
                    }
                }
            }

            builder.append("#").append(test).append(" ").append(maxLength).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void dfs(int startRow, int startCol, int height, int length, boolean used) {
        maxLength = Math.max(maxLength, length);
        for (int i = 0; i < 4; i++) {
            int nextRow = startRow + deltas[i][0];
            int nextCol = startCol + deltas[i][1];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
            if (visited[nextRow][nextCol]) continue;

            if (map[nextRow][nextCol] < height) {
                visited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, map[nextRow][nextCol], length + 1, used);
                visited[nextRow][nextCol] = false;
            } else {
                if (!used && (map[nextRow][nextCol] - k < height)) {
                    visited[nextRow][nextCol] = true;
                    dfs(nextRow, nextCol, height - 1, length + 1, true);
                    visited[nextRow][nextCol] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new none1949().solution();
    }
}

