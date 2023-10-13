package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class three2579 {
    public static int stairNum;
    public static int[] stairs;
    public static int[][] dp;
    public void solution() throws IOException {
        // 입력받기
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        stairNum = Integer.parseInt(reader.readLine()) + 1;
        stairs = new int[stairNum];
        dp = new int[stairNum][2];
        for (int i = 1; i < stairNum; i++) {
            stairs[i] = Integer.parseInt(reader.readLine());
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        // 계단이 하나일 경우
        if (stairNum == 2) {
            System.out.println(stairs[1]);
        }
        else {
            // 계단이 여럿일 경우 초기화
            dp[1][0] = stairs[1];
            dp[2][0] = stairs[2];
            dp[2][1] = stairs[1] + stairs[2];

            for (int i = 3; i < stairNum; i++) {
                int max = Math.max(dp[i - 2][0], dp[i - 2][1]); // 두 칸 전에서 오는 경우 최대값
                if (max > -1) { // max 값이 존재한다면
                    dp[i][0] = max + stairs[i]; // 두 칸 뛰는 경우
                }
                if (dp[i - 1][0] > -1) { // 한 칸 뛰는 경우도 조사
                    dp[i][1] = dp[i - 1][0] + stairs[i];
                }
            }


            System.out.println(Math.max(dp[stairNum - 1][0], dp[stairNum - 1][1]));
        }
    }

    public static void main(String[] args) throws IOException {
        new three2579().solution();
    }
}
