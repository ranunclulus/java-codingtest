package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.30
 @link https://www.acmicpc.net/problem/20366
 @timecomplex
 @performance 14692kb 384ms
 @category
 @note
 */
public class three20366 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[] snows;
    static int answer = Integer.MAX_VALUE;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        snows = new int[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            snows[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(snows);

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 3; j < n; j++) {
                int left = i + 1;
                int right = j - 1;

                while (left < right) {
                    int anna = snows[i] + snows[j];
                    int elsa = snows[left] + snows[right];

                    int result = elsa - anna;
                    answer = Math.min(answer, Math.abs(result));

                    if (result > 0) right--;
                    else left++;
                }
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three20366().solution();
    }
}
