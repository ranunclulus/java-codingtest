package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.13
 @link https://www.acmicpc.net/problem/17953
 @timecomplex
 @performance 92348kb 548ms
 @category
 @note
 */
public class five17953 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static int[][] taste, dp;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        taste = new int[m][n];

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                taste[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = taste[i][0];
        }

        for (int day = 1; day < n; day++) {
            for (int typeOne = 0; typeOne < m; typeOne++) {
                for (int typeTwo = 0; typeTwo < m; typeTwo++) {
                    if (typeOne == typeTwo) {
                        dp[typeOne][day] = Math.max(dp[typeOne][day], dp[typeTwo][day - 1] + taste[typeOne][day] / 2);
                    } else {
                        dp[typeOne][day] = Math.max(dp[typeOne][day], dp[typeTwo][day - 1] + taste[typeOne][day]);
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, dp[i][n - 1]);
        }

        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five17953().solution();
    }
}
