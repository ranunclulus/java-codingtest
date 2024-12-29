package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.29
 @link https://www.acmicpc.net/problem/15810
 @timecomplex
 @performance
 @category
 @note
 */
public class two15810 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static long m;
    static long[] times;
    static long start, end, middle, answer;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Long.parseLong(tokenizer.nextToken());

        times = new long[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            times[i] = Long.parseLong(tokenizer.nextToken());
        }
        Arrays.sort(times);
        end = times[n - 1] * m;

        while (start <= end) {
            middle = (start + end) / 2;

            long count = 0;
            for(long time : times) {
                count += (middle / time);
            }
            if (count >= m) {
                answer = middle;
                end = middle - 1;
            }
            else start = middle + 1;
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new two15810().solution();
    }

}
