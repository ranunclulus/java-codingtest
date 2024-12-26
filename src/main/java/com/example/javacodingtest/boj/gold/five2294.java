package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.26
 @link https://www.acmicpc.net/problem/2294
 @timecomplex
 @performance 14304kb 120ms
 @category
 @note
 */
public class five2294 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k;
    static int[] coin, dp;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        coin = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            coin[i] = Integer.parseInt(reader.readLine());
        }

        dp = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
        }

        for(int i = 1; i <= n; i++){
            for(int j = coin[i]; j <= k; j++){
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
            }
        }

        builder.append(dp[k] == Integer.MAX_VALUE - 1 ? -1 : dp[k]);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five2294().solution();
    }

}
