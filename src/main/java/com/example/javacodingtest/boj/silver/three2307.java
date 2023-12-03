package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class three2307 {
    BigInteger[][] dp = new BigInteger[101][101];

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i <= 100; i++) {
            dp[i][0] = BigInteger.ONE;
            dp[i][i] = BigInteger.ONE;
        }

        for (int i = 2; i <= 100; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);
            }
        }
        System.out.println(dp[n][m]);
    }

    public static void main(String[] args) throws IOException {
        new three2307().solution();
    }
}
