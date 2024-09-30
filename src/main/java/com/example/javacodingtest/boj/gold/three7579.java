package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.30
 @link
 @timecomplex
 @performance 54036kb, 168ms
 @category DP
 @note
 */
public class three7579 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, answer;
    static int[] memories, costs;
    static int[][] dp;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        answer = Integer.MAX_VALUE;

        memories = new int[n];
        costs = new int[n];
        dp = new int[n][100001];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            memories[i] = Integer.parseInt(tokenizer.nextToken());
        }
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int memory = memories[i];
            int cost = costs[i];

            for (int j = 0; j <= 10000; j++) {
                if (i == 0) {
                    if (cost <= j) dp[i][j] = memory;
                } else {
                    if (j >= cost) dp[i][j] = Math.max(dp[i - 1][j], memory + dp[i - 1][j - cost]);
                    else dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] >= m) answer = Math.min(answer, j);
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three7579().solution();
    }
}
