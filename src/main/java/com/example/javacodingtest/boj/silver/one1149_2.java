package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.04
 @link
 @timecomplex 14608 kb, 116 ms
 @performance
 @category
 @note
 */
public class one1149_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    public static int[][] coloredCost;
    public static int[][] dp;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        coloredCost = new int[n][3];
        for (int i = 0; i < n; i++) {
            tokenizer  = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 3; j++) {
                coloredCost[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        dp = new int[n][3];
        for (int i = 0; i < 3; i++) {
            dp[0][i] = coloredCost[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                int addValue = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
                dp[i][j] = coloredCost[i][j] + addValue;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        builder.append(result);
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new one1149_2().solution();
    }
}
