package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class one10844 {
    private Long[][] dp;
    private int n;
    private long MOD = 1000000000;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new Long[n + 1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1L;
        }

        long result = 0;

        for (int i = 1; i <= 9; i++) {
            result += recur(n, i);
        }
        System.out.println(result % MOD);
    }

    private long recur(int digit, int val) {
        if (digit == 1) {
            return dp[digit][val];
        }

        if (dp[digit][val] == null) {
            if (val == 0) {
                dp[digit][val] = recur(digit - 1, 1);
            } else if (val == 9) {
                dp[digit][val] = recur(digit - 1, 8);
            } else {
                dp[digit][val] = recur(digit - 1, val - 1) + recur(digit - 1, val + 1);
            }
        }
        return dp[digit][val] % MOD;
    }


    public static void main(String[] args) throws IOException {
        new one10844().solution();
    }
}
