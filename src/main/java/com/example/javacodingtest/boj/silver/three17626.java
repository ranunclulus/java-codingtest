package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class three17626 {
    private int number;
    private int[] dp = new int[50000 + 1];

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        number = sc.nextInt();
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= 50000; i++) {
            int minValue = Integer.MAX_VALUE;
            for (int j = 1; j < Math.floor(Math.sqrt(i)) + 1; j++) {
                minValue = Math.min(minValue, dp[i - j * j]);
            }
            dp[i] = minValue + 1;
        }
        System.out.println(dp[number]);
    }

    public static void main(String[] args) throws IOException {
        new three17626().solution();
    }
}
