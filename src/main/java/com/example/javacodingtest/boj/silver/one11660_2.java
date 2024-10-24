package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.23
 @link https://www.acmicpc.net/problem/11660
 @timecomplex
 @performance 118908kb 720ms
 @category
 @note
 */
public class one11660_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, answer;
    static int[][] sums;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        sums = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= n; j++) {
                int value = Integer.parseInt(tokenizer.nextToken());
                sums[i][j] = value + sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1];
            }
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int startCol = Integer.parseInt(tokenizer.nextToken());
            int startRow = Integer.parseInt(tokenizer.nextToken());
            int endCol = Integer.parseInt(tokenizer.nextToken());
            int endRow = Integer.parseInt(tokenizer.nextToken());

            answer = sums[endCol][endRow] - sums[endCol][startRow - 1] - sums[startCol - 1][endRow] + sums[startCol - 1][startRow - 1];
            builder.append(answer).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new one11660_2().solution();
    }
}
