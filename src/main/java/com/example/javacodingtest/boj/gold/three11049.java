package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.02.03
 @link https://www.acmicpc.net/problem/11049
 @timecomplex
 @performance 16932kb 228ms
 @category
 @note
 */
public class three11049 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n;
	static int[][] array;
	static int[][] dp;

	public void solution() throws IOException {
		n = Integer.parseInt(reader.readLine());
		array = new int[n][2];

		for (int i = 0; i < n; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			array[i][0] = Integer.parseInt(tokenizer.nextToken());
			array[i][1] = Integer.parseInt(tokenizer.nextToken());
		}

		dp = new int[n][n];
		for (int size = 1; size < n; size++) {
			for (int start = 0; start < n - size; start++) {
				int end = start + size;

				dp[start][end] = Integer.MAX_VALUE;

				for (int k = start; k < end; k++) {
					// start ~ k 까지 곱한 행렬 A
					// k + 1부터 end까지 곱한 행렬 B
					// A 행렬 * B 행렬 연산
					int calculation = dp[start][k] + dp[k + 1][end] + array[start][0] * array[k][1] * array[end][1];
					dp[start][end] = Math.min(dp[start][end], calculation);
				}
			}
		}
		builder.append(dp[0][n - 1]);

		writer.write(builder.toString());
		writer.flush();
	}

	public static void main(String[] args) throws IOException {
		new three11049().solution();

	}
}
