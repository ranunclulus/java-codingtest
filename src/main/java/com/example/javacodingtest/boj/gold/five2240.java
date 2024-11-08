package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.08
 @link https://www.acmicpc.net/problem/2240
 @timecomplex
 @performance 14416kb 104ms
 @category
 @note
 */
public class five2240 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int t, w, result;
    static int[][] dp;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        t = Integer.parseInt(tokenizer.nextToken());
        w = Integer.parseInt(tokenizer.nextToken());

        dp = new int[t + 1][w + 1];

        for (int i = 1; i <= t; i++) {
            int plum = Integer.parseInt(reader.readLine());
            for (int j = 0; j <= w; j++) {
                // 안 움직인 경우 1에만 추가
                if (j == 0) {
                    if (plum == 1) dp[i][j] = dp[i - 1][j] + 1;
                    else dp[i][j] = dp[i - 1][j];
                    continue;
                }
                // 짝수번 움직이면 나무 1
                if (j % 2 == 0) {
                    if (plum == 1) dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);
                    else dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
                }
                // 홀수번 움직이면 나무 2
                else {
                    if (plum == 1) dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
                    else dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);
                }

            }
        }

        for (int i = 0; i <= w; i++) {
            result = Math.max(result, dp[t][i]);
        }
        builder.append(result);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five2240().solution();
    }
}
