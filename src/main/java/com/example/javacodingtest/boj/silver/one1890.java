package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class one1890 {
    private int n;
    private int[][] board;
    private long[][] dp;
    private boolean[][] visited;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        board = new int[n][n];
        dp = new long[n][n];
        visited = new boolean[n][n];

        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int jump = board[i][j];
                if (jump == 0) break;

                if (i + jump < n) {
                    dp[i + jump][j] += dp[i][j];
                }
                if (j + jump < n) {
                    dp[i][j + jump] += dp[i][j];
                }
            }
        }

        System.out.println(dp[n - 1][n - 1]);
    }

    public static void main(String[] args) throws IOException {
        new one1890().solution();
    }
}
