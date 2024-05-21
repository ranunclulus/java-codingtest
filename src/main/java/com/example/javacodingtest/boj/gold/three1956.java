package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class three1956 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int v = Integer.parseInt(infoToken.nextToken());

        int[][] edges = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(edges[i], 1000000000);
        }

        for (int i = 0; i < v; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(infoToken.nextToken()) - 1;
            int end = Integer.parseInt(infoToken.nextToken()) - 1;
            int weight = Integer.parseInt(infoToken.nextToken());
            edges[start][end] = weight;
        }

        for (int i = 0; i < n; i++) {
            edges[i][i] = 0;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                minDistance = Math.min(minDistance, edges[i][j] + edges[j][i]);
            }
        }
        if (minDistance >= 1000000000) minDistance = -1;
        System.out.println(minDistance);
    }

    public static void main(String[] args) throws IOException {
        new three1956().solution();
    }
}
