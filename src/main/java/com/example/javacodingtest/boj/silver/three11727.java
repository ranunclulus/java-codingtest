package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class three11727 {
    private int[] dp = new int[10001];

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 2; i < 10001; i++) {
            if (i % 2 == 0) {
                dp[i] = (dp[i - 1] * 2 + 1) % 10007;
            } else {
                dp[i] = (dp[i - 2] * 4 + 1) % 10007;
            }
        }
        System.out.println(dp[n]);
    }

    public static void main(String[] args) throws IOException {
        new three11727().solution();
    }
}
