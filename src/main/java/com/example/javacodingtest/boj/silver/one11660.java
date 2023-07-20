package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11660
public class one11660 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int size = Integer.parseInt(infoToken.nextToken());
        int points = Integer.parseInt(infoToken.nextToken());

        int[][] board = new int[size + 1][size + 1];
        for (int i = 1; i <= size; i++) {
            StringTokenizer rowToken = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= size; j++) {
                board[i][j] = Integer.parseInt(rowToken.nextToken());
            }
        }
        int[][] dp = new int[size + 1][size + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                // 전체 구간 합 구해 두기
                dp[i][j] = board[i][j]
                        + dp[i - 1][j]
                        + dp[i][j - 1]
                        - dp[i - 1][j - 1];
            }
        }
        // 구하고자 하는 점들마다 결과 확인
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < points; i++) {
            StringTokenizer pointToken = new StringTokenizer(reader.readLine());
            int x1 = Integer.parseInt(pointToken.nextToken());
            int y1 = Integer.parseInt(pointToken.nextToken());
            int x2 = Integer.parseInt(pointToken.nextToken());
            int y2 = Integer.parseInt(pointToken.nextToken());
            int sum = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
            answer.append(sum).append('\n');
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new one11660().solution();
    }
}
