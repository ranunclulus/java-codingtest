package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class three11276 {
    private int n;
    private int[] dp;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n + 5];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;

        }

        System.out.println(dp[n]);
    }

    public static void main(String[] args) throws IOException {
        new three11276().solution();
    }
}
