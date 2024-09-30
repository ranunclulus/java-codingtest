package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.30
 @link
 @timecomplex
 @performance 25776kb, 232ms
 @category
 @note
 */
public class three3282 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n, k, v, c;
    static int[][] dp;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++)  {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            k = Integer.parseInt(tokenizer.nextToken());
            dp = new int[n + 1][k + 1];

            for (int i = 1; i <= n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                v = Integer.parseInt(tokenizer.nextToken());
                c = Integer.parseInt(tokenizer.nextToken());

                for (int j = 0; j < v; j++) {
                    dp[i][j] = dp[i - 1][j];
                }

                for (int j = v; j <= k; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], c + dp[i - 1][j - v]);
                }
            }
            builder.append("#").append(test).append(" ").append(dp[n][k]).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three3282().solution();
    }
}
