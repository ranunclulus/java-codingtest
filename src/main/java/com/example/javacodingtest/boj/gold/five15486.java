package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

public class five15486 {
    private int n;
    private int[] time;
    private int[] price;
    private int[] dp;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        time = new int[n];
        price = new int[n];
        dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            time[i] = sc.nextInt();
            price[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            int timeVal = time[i];
            int priceVal = price[i];
            if (i + timeVal <= n) {
                dp[i + timeVal] = Math.max(dp[i + timeVal], dp[i] + priceVal);
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
        }

        System.out.println(dp[n]);
    }

    public static void main(String[] args) throws IOException {
        new five15486().solution();
    }
}
