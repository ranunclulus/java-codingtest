package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/17069
 @timecomplex
 @performance 14444kb, 108ms
 @categorya
 @note
 */
public class four17069 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[][] map;
    static long[][][]dp;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        map = new int[n + 1][n + 1];
        dp = new long[n + 1][n + 1][3]; // i, j 좌표에 d 방향으로 도달할 수 있는 경우의 수

        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        long result = 0;
        if (map[n][n] != 1) {
            dp[1][2][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 3; j <= n; j++) {
                    if (map[i][j] == 1) continue;
                    // 가로
                    dp[i][j][0] = (dp[i][j - 1][0] + dp[i][j - 1][2]);

                    if (i == 1) continue;

                    // 세로
                    dp[i][j][1] = (dp[i - 1][j][1] + dp[i - 1][j][2]);

                    if (map[i - 1][j] == 1 || map[i][j - 1] == 1) continue;

                    // 대각선
                    dp[i][j][2] = (dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]);

                }
            }
            result = (dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
        }
        builder.append(result);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four17069().solution();
    }
}
