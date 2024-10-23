package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.23
 @link https://www.acmicpc.net/problem/1749
 @timecomplex
 @performance 20968kb, 1912ms
 @category
 @note
 */
public class four1749 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static long answer = Long.MIN_VALUE;
    static int[][] scores, subsum;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        scores = new int[n + 1][m + 1];
        subsum = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= m; j++) {
                scores[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                subsum[i][j] = scores[i][j] + subsum[i - 1][j] + subsum[i][j - 1] - subsum[i - 1][j - 1];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = i; k <= n; k++) {
                    for (int l = j; l <= m; l++) {
                        answer = Math.max(answer,
                                subsum[k][l] - subsum[k][j - 1] - subsum[i - 1][l] + subsum[i - 1][j - 1]);
                    }
                }
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four1749().solution();
    }
}
