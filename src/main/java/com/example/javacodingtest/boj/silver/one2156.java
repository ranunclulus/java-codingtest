package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class one2156 {
    private int n;
    private int[] arr;
    private Integer[] dp;


    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n + 1];
        dp = new Integer[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        dp[0] = 0;
        dp[1] = arr[1];

        if (n > 1) {
            dp[2] = arr[1] + arr[2];
        }

        System.out.println(recur(n));
    }

    private int recur(int n) {
        if (dp[n] == null) {
            dp[n] = Math.max(Math.max(recur(n - 2), recur(n - 3) + arr[n - 1]) + arr[n], recur(n - 1));
        }
        return dp[n];
    }


    public static void main(String[] args) throws IOException {
        new one2156().solution();
    }
}
