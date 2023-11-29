package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class five1010 {
    static int[][] dp = new int[30][30];

    public void solution() throws IOException {

        dp[0][0] = 1;
        for (int j = 1; j < 30; j++) {
            dp[j][0] = 1;
            for (int k = 1; k <= j; k++)
                dp[j][k] = dp[j - 1][k - 1] + dp[j - 1][k];
        }

        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        for (int i = 0; i < testCase; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            System.out.println(dp[m][n]);
        }
    }


    public static void main(String[] args) throws IOException {
        new five1010().solution();
    }
}
