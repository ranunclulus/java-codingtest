package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.30
 @link https://www.acmicpc.net/problem/17951
 @timecomplex
 @performance 22860kb 252ms
 @category
 @note
 */
public class three17951 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k;
    static int[] scores;
    static int start, end, middle;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        scores = new int[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(tokenizer.nextToken());
            end += scores[i];
        }

        while (start <= end) {
            middle = (start + end) / 2;

            int sum = 0;
            int groupCount = 0;

            for (int i = 0; i < n; i++) {
                sum += scores[i];
                if (middle <= sum) {
                    sum = 0;
                    groupCount++;
                }

            }

            if (groupCount >= k) {
                start = middle + 1;
            } else end = middle - 1;
        }
        builder.append(end);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three17951().solution();
    }
}
