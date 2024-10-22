package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.22
 @link https://www.acmicpc.net/problem/14225
 @timecomplex
 @performance 17428kb 176ms
 @category
 @note
 */
public class one14225 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[] numbers;
    static boolean[] visited, choice;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        numbers = new int[n];
        choice = new boolean[n];
        visited = new boolean[20 * 100000 + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
            visited[numbers[i]] = true;
        }

        checkSubset(0);

        for (int i = 0; i < 20 * 100000 + 1; i++) {
            if (!visited[i]) {
                builder.append(i);
                break;
            }
        }

        writer.write(builder.toString());
        writer.flush();
    }

    private void checkSubset(int depth) {
        if (depth == n) {
            int subSum = 0;
            for (int i = 0; i < n; i++) {
                if (choice[i]) subSum += numbers[i];
            }
            visited[subSum] = true;
            return;
        }

        choice[depth] = true;
        checkSubset(depth + 1);
        choice[depth] = false;
        checkSubset(depth + 1);
    }

    public static void main(String[] args) throws IOException {
        new one14225().solution();
    }
}
