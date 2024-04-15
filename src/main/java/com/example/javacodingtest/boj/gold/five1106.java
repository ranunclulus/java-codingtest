package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class five1106 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int c = Integer.parseInt(infoToken.nextToken());
        int n = Integer.parseInt(infoToken.nextToken());
        int[] dp = new int[c + 101];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            int cost = Integer.parseInt(infoToken.nextToken());
            int people = Integer.parseInt(infoToken.nextToken());

            for (int j = people; j < c + 101; j++) {
                dp[j] = Math.min(dp[j], dp[j - people] + cost);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = c; i < c + 101; i++) {
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new five1106().solution();
    }
}
