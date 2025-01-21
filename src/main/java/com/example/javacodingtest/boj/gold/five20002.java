package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.21
 @link https://www.acmicpc.net/problem/20002
 @timecomplex
 @performance 23652kb 348ms
 @category
 @note
 */
public class five20002 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, answer;
    static int[][] profits, sums;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        profits = new int[n + 1][n + 1];
        sums = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= n; j++) {
                profits[i][j] = Integer.parseInt(tokenizer.nextToken());
                sums[i][j] = sums[i][j - 1] + sums[i - 1][j] - sums[i - 1][j - 1] + profits[i][j];
            }
        }

        answer = Integer.MIN_VALUE;
        int k = -1;
        while (k++ < n) {
            for (int i = 1; i < n - k + 1; i++) {
                for (int j = 1; j < n - k + 1; j++) {
                    int profit = sums[i + k][j + k] - sums[i + k][j - 1] - sums[i - 1][j + k] + sums[i - 1][j - 1];
                    answer = Math.max(answer, profit);
                }
            }
        }

        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }


    public static void main(String[] args) throws IOException {
        new five20002().solution();
    }
}
