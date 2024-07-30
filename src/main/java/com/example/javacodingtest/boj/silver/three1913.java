package com.example.javacodingtest.boj.silver;

import java.io.*;

public class three1913 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        int n = Integer.parseInt(reader.readLine());
        int[][] map = new int[n][n];
        int val = n * n;
        int size = n;
        int x = 0;
        int y = 0;
        int full = 0;
        while (val > 0) {
            // 아래
            x = full;
            for (int i = y; i < size; i++) {
                map[i][x] = val--;
            }

            y = size - 1;
            for (int i = x + 1; i < size; i++) {
                map[y][i] = val--;
            }

            x = size - 1;
            for (int i = y - 1; i >= full; i--) {
                map[i][x] = val--;
            }

            y = full;
            for (int i = x - 1; i > full; i--) {
                map[y][i] = val--;
            }
            full++;
            size--;
            y = full;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                builder.append(map[i][j]).append(" ");
            }
            builder.append('\n');
        }
        int num = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == num) {
                    builder.append(i + 1).append(" ").append(j + 1);
                    break;
                }
            }
        }
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new three1913().solution();
    }
}
