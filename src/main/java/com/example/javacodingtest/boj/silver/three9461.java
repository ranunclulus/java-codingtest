package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class three9461 {
    public void solution() throws IOException {
        long[] dp = new long[101];

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;

        for (int i = 6; i <= 100; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        for (int i = 0; i < testCase; i++) {
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }

    public static void main(String[] args) throws IOException {
        new three9461().solution();
    }
}
