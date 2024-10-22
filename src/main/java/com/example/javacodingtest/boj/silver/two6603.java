package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.22
 @link https://www.acmicpc.net/problem/6603
 @timecomplex
 @performance 14304kb 104ms
 @category
 @note
 */
public class two6603 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[] numbers, combination;
    static boolean[] visited;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());

        while (n != 0) {
            numbers = new int[n];
            combination = new int[6];
            visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(tokenizer.nextToken());
            }

            backTracking(0, 0);
            builder.append('\n');

            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
        }

        writer.write(builder.toString());
        writer.flush();
    }

    private void backTracking(int depth, int start) {
        if (start >= n) return;
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                builder.append(combination[i]).append(' ');
            }
            builder.append('\n');
            return;
        }

        for (int i = start; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            combination[depth] = numbers[i];
            backTracking(depth + 1, i);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        new two6603().solution();
    }
}

