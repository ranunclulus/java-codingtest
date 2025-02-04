package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.02.05
 @link https://www.acmicpc.net/problem/2342
 @timecomplex
 @performance 48428kb 656ms
 @category
 @note
 */
public class three2342_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int[][][] dp;
	static int[] moving;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		moving = new int[tokenizer.countTokens() - 1];

		for (int i = 0; i < moving.length; i++) {
			moving[i] = Integer.parseInt(tokenizer.nextToken());
		}

		dp = new int[5][5][moving.length];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		builder.append(dance(0, 0, 0));
		writer.write(builder.toString());
		writer.flush();
	}

	public int dance(int left, int right, int count) {
		if (count == moving.length) return 0;

		if (dp[left][right][count] != -1) return dp[left][right][count];

		dp[left][right][count] = Math.min(
			dance(moving[count], right, count  + 1) + energy(left, moving[count]),
			dance(left, moving[count], count + 1 ) + energy(right, moving[count])
		);

		return dp[left][right][count];
	}

	public int energy(int current, int destination) {
		int diff = Math.abs(current - destination);
		if (current == 0) return 2;
		if (diff == 0) return 1;
		if (diff == 1 || diff == 3) return 3;
		else return 4;
	}

	public static void main(String[] args) throws IOException {
		new three2342_2().solution();
	}

}
