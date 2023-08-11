package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1912
public class two1912 {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        StringTokenizer numberToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(numberToken.nextToken());
        }

        // 여태까지의 수열의 합 중에서 i 번째 숫자는 무조건 포함한 수열의 최대값
        int[] dp = new int[n];
        dp[0] = numbers[0];
        int max = numbers[0];

        // 직전 거와 비교하기 때문에 1 부터
        for (int i = 1; i < n; i++) {
            // 지금까지의 합 + 현재 숫자 vs 현재 숫자
            dp[i] = Math.max(dp[i - 1] + numbers[i], numbers[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new two1912().solution());
    }
}
