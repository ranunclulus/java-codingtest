package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.04
 @link
 @timecomplex
 @performance 31740 kb 167 ms
 @category
 @note
 */
public class three5215 {
  

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n, l;
    static int[][] dp;



    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            l = Integer.parseInt(tokenizer.nextToken());
            dp = new int[n + 1][l];
            for (int i = 0; i < l; i++) {
                dp[0][i] = 0;
            }
            for (int i = 1; i <= n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int taste = Integer.parseInt(tokenizer.nextToken());
                int calrorie = Integer.parseInt(tokenizer.nextToken());

                for (int j = 0; j < calrorie; j++) {
                    dp[i][j] = dp[i - 1][j];
                }
                for (int j = calrorie; j < l; j++) {
                    dp[i][j] = Math.max(taste + dp[i - 1][j - calrorie], dp[i - 1][j]);
                }
            }
            builder.append("#").append(test).append(" ").append(dp[n][l - 1]).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three5215().solution();
    }
}
