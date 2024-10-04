package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.04
 @link https://www.acmicpc.net/problem/1535
 @timecomplex
 @performance 14264kb, 100ms
 @category
 @note
 */
public class two1535 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int totalEnerge = 100;
    static int totalJoy = 0;
    static int n;
    static int[] energes;
    static int[] joys;

    static int[][] dp;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        energes = new int[n + 1];
        joys = new int[n + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            energes[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            joys[i] = Integer.parseInt(tokenizer.nextToken());
        }

        dp = new int[n + 1][101];

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int energe = energes[i];
            int joy = joys[i];
            for (int j = 1; j < 100; j++) {
                if (j < energe) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - energe] + joy);
                answer = Math.max(answer, dp[i][j]);
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new two1535().solution();
    }
}
