package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.06
 @link https://www.acmicpc.net/problem/14719
 @timecomplex
 @performance 14324kb 116ms
 @category
 @note
 */
public class five14719 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, water;
    static int[] height;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        height = new int[m];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            height[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 1; i < m - 1; i++) {
            int left = 0;
            int right = 0;

            for (int j = 0; j < i; j++) {
                left = Math.max(height[j], left);
            }

            for (int j = i + 1; j < m; j++) {
                right = Math.max(height[j], right);
            }

            if (height[i] < left && height[i] < right) water += Math.min(left, right) - height[i];
        }

        builder.append(water);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five14719().solution();
    }
}


