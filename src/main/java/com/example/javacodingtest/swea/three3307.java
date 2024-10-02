package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.02
 @link
 @timecomplex
 @performance 30128 kb, 225 ms
 @category LIS
 @note
 */
public class three3307 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n, max;
    static int[] number, dp;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());

        for (int test = 1; test <= testNum; test++) {
            n = Integer.parseInt(reader.readLine());
            number = new int[n];
            dp = new int[n];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                number[i] = Integer.parseInt(tokenizer.nextToken());
            }

            max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (number[j] < number[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                    max = Math.max(max, dp[i]);
                }
            }
            builder.append("#" + test).append(" ").append(max).append("\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three3307().solution();
    }
}
