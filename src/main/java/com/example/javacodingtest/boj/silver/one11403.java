package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class one11403 {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        int[][] map = new int[size][size];

        for (int i = 0; i < size; i++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(infoToken.nextToken());
            }
        }

        for (int node = 0; node < size; node++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (map[i][node] == 1 && map[node][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static void main(String[] args) throws IOException {
        new one11403().solution();
    }
}
