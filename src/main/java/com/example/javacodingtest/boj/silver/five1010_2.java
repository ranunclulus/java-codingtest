package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.05
 @link https://www.acmicpc.net/problem/1010
 @timecomplex
 @performance 14156	kb 100 ms
 @category DP
 @note
 */
public class five1010_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int[][] combination;
    static int testNum;
    public void solution() throws IOException {
        combination = new int[31][31];
        for (int n = 1; n <= 30; n++) {
            combination[n][n] = 1;
            combination[n][1] = n;
        }

        for (int n = 3; n <= 30; n++) {
            for (int r = 2; r < n; r++) {
                combination[n][r] = (combination[n - 1][r - 1] + combination[n - 1][r]);
            }
        }
        testNum = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testNum; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int r = Integer.parseInt(tokenizer.nextToken());
            int n = Integer.parseInt(tokenizer.nextToken());
            builder.append(combination[n][r]).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five1010_2().solution();
    }
}
