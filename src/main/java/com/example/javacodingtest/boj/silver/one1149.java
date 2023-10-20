package com.example.javacodingtest.boj.silver;
// https://www.acmicpc.net/problem/1149
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class one1149 {
    public static int houseNum;
    public static int[][] coloredCost;
    public static int[][] dp;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        houseNum = Integer.parseInt(reader.readLine());
        coloredCost = new int[houseNum][3];
        dp = new int[houseNum][3];

        for (int i = 0; i < houseNum; i++) {
            StringTokenizer colorToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 3; j++) {
                coloredCost[i][j] = Integer.parseInt(colorToken.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            dp[0][i] = coloredCost[0][i];
        }

        for (int i = 1; i < houseNum; i++) {
            for (int j = 0; j < 3; j++) {
                int maxValue = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
                dp[i][j] = coloredCost[i][j] + maxValue;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            result = Math.min(result, dp[houseNum - 1][i]);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new one1149().solution();
    }
}