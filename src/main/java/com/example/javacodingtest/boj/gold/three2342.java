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
 @since 2025.02.04
 @link https://www.acmicpc.net/problem/2342
 @timecomplex
 @performance 48428kb 656ms
 @category
 @note
 */
public class three2342 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int[] moving;
	static int[][][] dp;

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

	public int dance(int left, int right, int cnt) {

		if(cnt == moving.length) return 0;

		if(dp[left][right][cnt] != -1) return dp[left][right][cnt];

		dp[left][right][cnt] = Math.min(
			dance(moving[cnt], right, cnt+1) + energy(left, moving[cnt]),
			dance(left, moving[cnt], cnt+1) + energy(right, moving[cnt]));

		return dp[left][right][cnt];
	}


	public int energy(int current, int destination) {
		int diff = Math.abs(current - destination);
		if (current == 0) return 2;
		if (diff == 0) return 1;
		if (diff == 1 || diff == 3) return 3;
		else return 4;
	}

	public static void main(String[] args) throws IOException {
		new three2342().solution();
	}
}
