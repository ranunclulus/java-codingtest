package com.example.javacodingtest.boj.gold;
// https://www.acmicpc.net/problem/9251

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class five9251 {
    static int[][] dp;
    static char[] first;
    static char[] second;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        first = reader.readLine().toCharArray();
        second = reader.readLine().toCharArray();
        dp = new int[first.length + 1][second.length + 1];

        for (int i = 1; i <= first.length; i++) {
            for (int j = 1; j <= second.length; j++) {
                if (first[i - 1] != second[j - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int[] row : dp) {
            for(int value : row) {
                max = Math.max(max, value);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        new five9251().solution();
    }
}
