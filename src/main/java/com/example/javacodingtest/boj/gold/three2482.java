package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.04.08
 @link https://www.acmicpc.net/problem/2482
 @timecomplex
 @performance
 @category
 @note
 */
public class three2482 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, K, answer;
	static int MOD = 1000000003;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		new three2482().solution();
	}

	private void solution() throws IOException {
		N = Integer.parseInt(reader.readLine());
		K = Integer.parseInt(reader.readLine());
		dp = new int[N + 1][K + 1];

		for (int i = 0; i <= N; i++) {
			dp[i][1] = i;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
			}
		}

		if (K == 1) answer = N;
		// N번째 색을 선택하지 않은 경우 + N번째 색을 선택한 경우
		else answer = (dp[N - 1][K] + dp[N - 3][K - 1]) % MOD;

		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
	}
}
