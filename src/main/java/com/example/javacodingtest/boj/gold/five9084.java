package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class five9084 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        for (int test = 0; test < testCase; test++) {
            int coinNum = Integer.parseInt(reader.readLine());
            int[] coins = new int[coinNum];

            StringTokenizer coinToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < coinNum; i++) {
                coins[i] = Integer.parseInt(coinToken.nextToken());
            }

            int total = Integer.parseInt(reader.readLine());
            int[] dp = new int[total + 1];

            for (int i = 0; i < coinNum; i++) {
                int coin = coins[i];
                for (int j = 1; j <= total; j++) {
                    if (j - coin > 0) {
                        dp[j] += dp[j - coin];
                    } else if (j - coin == 0) {
                        dp[j]++;
                    }
                }
            }
            System.out.println(dp[total]);
        }
    }

    public static void main(String[] args) throws IOException {
        new five9084().solution();
    }
}
