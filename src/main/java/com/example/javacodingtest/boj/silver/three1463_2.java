package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.04
 @link https://www.acmicpc.net/problem/1463
 @timecomplex
 @performance 18092	kb, 120 ms
 @category
 @note
 */
public class three1463_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static int n;
    static int[] dp;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());

        dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) dp[i] = Math.min((dp[i / 2] + 1), dp[i]);
            if (i % 3 == 0) dp[i] = Math.min((dp[i / 3] + 1), dp[i]);

        }
        builder.append(dp[n]);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three1463_2().solution();
    }
}
