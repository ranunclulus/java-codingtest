package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.08
 @link https://www.acmicpc.net/problem/1806
 @timecomplex
 @performance 23708kb, 252ms
 @category
 @note
 */
public class four1806 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, s, right, left, sum, length;
    static int[] numbers;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        s = Integer.parseInt(tokenizer.nextToken());
        numbers = new int[n];
        tokenizer = new StringTokenizer(reader.readLine());
        numbers[0] = Integer.parseInt(tokenizer.nextToken());
        right = 0;
        left = -1;
        sum = 0;
        length = Integer.MAX_VALUE;

        if (numbers[0] >= s) {
            left = 0;
            length = 1;
        }

        for (int i = 1; i < n; i++) {
            numbers[i] = numbers[i - 1] + Integer.parseInt(tokenizer.nextToken());
            if (numbers[i] >= s && left == -1) {
                left = i;
                length = i + 1;
            }
        }

        for (int i = left; i < n; i++) {
            for (int j = right; j < left; j++) {
                if (numbers[i] - numbers[j] >= s) {
                    left = i;
                    right = j;
                    length = Math.min(length, left - right);
                } else break;
            }
            left = i;
        }
        if (length == Integer.MAX_VALUE) length = 0;
        builder.append(length);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four1806().solution();
    }
}
