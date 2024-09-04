package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.04
 @link https://www.acmicpc.net/problem/12865
 @timecomplex
 @performance 53748kb,	200ms
 @category
 @note
 */
public class five12865_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k;
    static int[][] knapsacks;
    static int[][] dp;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        knapsacks = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            knapsacks[i][0] = Integer.parseInt(tokenizer.nextToken());
            knapsacks[i][1] = Integer.parseInt(tokenizer.nextToken());
        }

        dp = new int[n + 1][k + 1];

        for (int w = 1; w <= k; w++) {
            for (int i = 1; i <= n; i++) {
                dp[i][w] = dp[i - 1][w];
                if (w >= knapsacks[i][0]) {
                    dp[i][w] = Math.max(dp[i - 1][w], knapsacks[i][1] + dp[i - 1][w - knapsacks[i][0]]);
                }
            }
        }
        builder.append(dp[n][k]);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five12865_2().solution();
    }
}
