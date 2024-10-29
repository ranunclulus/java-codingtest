package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.28
 @link https://www.acmicpc.net/problem/20922
 @timecomplex
 @performance 32944kb 344ms
 @category
 @note
 */
public class one20922 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k, start, end, max, length;
    static int[] count;
    static int[] sequence;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        count = new int[100001];
        sequence = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }

        start = 0;
        end = 0;

        while (end < n) {
            while (end < n && count[sequence[end]] + 1 <= k) {
                count[sequence[end]]++;
                end++;
            }
            length = end - start;
            max = Math.max(max, length);
            count[sequence[start]]--;
            start++;
        }

        builder.append(max);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new one20922().solution();
    }
}
