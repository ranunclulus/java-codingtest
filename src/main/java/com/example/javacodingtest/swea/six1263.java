package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.10
 @link
 @timecomplex
 @performance 98616 kb 1386 ms
 @category
 @note
 */
public class six1263 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n, minIndex, minValue;
    static int[][] adjArray;
    static final int MAX = Integer.MAX_VALUE / 2;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());

            adjArray = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjArray[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adjArray[i][j] == 0) adjArray[i][j] = MAX;
                    if (i == j) adjArray[i][j] = 0;
                }
            }

            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        adjArray[i][j] = Math.min(adjArray[i][j], adjArray[i][k] + adjArray[k][j]);
                    }
                }
            }

            minValue = MAX;
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += adjArray[i][j];
                }
                if (sum < minValue) {
                    minValue = sum;
                }
            }
            minIndex++;
            builder.append("#" + test + " " + minValue + "\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new six1263().solution();
    }
}
