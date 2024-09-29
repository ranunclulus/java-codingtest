package com.example.javacodingtest.boj.bronze;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/2399
 @timecomplex
 @performance
 @category
 @note
 */
public class two2399 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static long answer;
    static int[] numbers;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        numbers = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                answer += Math.abs(numbers[i] - numbers[j]);
            }
        }

        builder.append(answer * 2);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new two2399().solution();
    }
}
