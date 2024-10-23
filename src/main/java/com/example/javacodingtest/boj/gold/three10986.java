package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.23
 @link https://www.acmicpc.net/problem/10986
 @timecomplex
 @performance 120684kb, 456ms
 @category
 @note
 */
public class three10986 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static long result;
    static long[] sequence, rest;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        result = 0;

        sequence = new long[n + 1];
        rest = new long[m];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i < n + 1; i++) {
            sequence[i] = (sequence[i - 1] + Integer.parseInt(tokenizer.nextToken())) % m;
            if (sequence[i] == 0) {
                result++;
            }
            rest[(int) sequence[i]]++;
        }

        for (int i = 0; i < m; i++) {
            if (rest[i] > 1) {
                result += ((rest[i] * (rest[i] - 1)) / 2);
            }
        }
        builder.append(result);
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new three10986().solution();
    }
}
