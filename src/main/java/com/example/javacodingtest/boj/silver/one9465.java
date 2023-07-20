package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9465
public class one9465 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(reader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < testCases; i++) {
            int n = Integer.parseInt(reader.readLine());
            int[][] stickers = new int[2][n];
            for (int j = 0; j < 2; j++) {
                StringTokenizer stickerToken = new StringTokenizer(reader.readLine());
                for (int k = 0; k < n; k++) {
                    stickers[j][k] = Integer.parseInt(stickerToken.nextToken());
                }
            }
            answer.append(solve(n, stickers)).append('\n');
        }
        System.out.println(answer);
    }

    private int solve(int cols, int[][] stickers) {
        int[][] dp = new int[2][cols];
        // 첫 번째 배열은 스티커값 그대로
        dp[0][0] = stickers[0][0];
        dp[1][0] = stickers[1][0];
        // 두 번째 열은 자신의 값 + 왼쪽 대각선
        dp[0][1] = dp[1][0] + stickers[0][1];
        dp[1][1] = dp[0][0] + stickers[1][1];
        // 나머지 만들기
        for (int i = 2; i < cols; i++) {
            dp[0][i] = Math.max(
                    // 왼쪽 아래 대각선 활용
                    dp[1][i - 1] + stickers[0][i],
                    // 왼쪽 왼쪽 아래 대각선 활용
                    dp[1][i - 2] + stickers[0][i]);
            dp[1][i] = Math.max(
                    // 왼쪽 위 대각선 활용
                    dp[0][i - 1] + stickers[1][i],
                    // 왼쪽 왼쪽 위 대각선 활용
                    dp[0][i - 2] + stickers[1][i]);
        }
        return Math.max(dp[0][cols - 1], dp[1][cols - 1]);
    }

    public static void main(String[] args) throws IOException {
        new one9465().solution();
    }
}
