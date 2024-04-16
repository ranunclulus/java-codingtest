package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class three7579 {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());
        int answer = Integer.MAX_VALUE;

        int[] memories = new int[n];
        int[] costs = new int[n];
        int[][] dp = new int[n][100001];

        StringTokenizer memoryToken = new StringTokenizer(reader.readLine());
        StringTokenizer costToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            memories[i] = Integer.parseInt(memoryToken.nextToken());
            costs[i] = Integer.parseInt(costToken.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int memory = memories[i];
            int cost = costs[i];

            for (int j = 0; j <= 10000; j++) {
                if (i == 0) {
                    if (cost <= j) dp[i][j] = memory;
                } else {
                    if (j >= cost) dp[i][j] = Math.max(dp[i - 1][j], memory + dp[i - 1][j - cost]);
                    else dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] >= m) answer = Math.min(answer, j);
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new three7579().solution();
    }
}
