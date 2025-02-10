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
 @since 2025.02.11
 @link https://www.acmicpc.net/problem/15732
 @timecomplex
 @performance 20676kb 196ms
 @category
 @note
 */
public class three15732 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n, k, minIndex;
	static long d;
	static int[][] rules;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(tokenizer.nextToken());
		k = Integer.parseInt(tokenizer.nextToken());
		d = Long.parseLong(tokenizer.nextToken());

		rules = new int[k][3];
		for (int i = 0; i < k; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < 3; j++) {
				rules[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		findLastBox();
		builder.append(minIndex);
		writer.write(builder.toString());
		writer.flush();
	}

	public void findLastBox() {
		int left = 0;
		int right = n;
		minIndex = Integer.MAX_VALUE;

		while (left <= right) {
			int mid = (left + right) / 2;

			long acornCount = countAcorn(mid);

			if (acornCount >= d) {
				right = mid - 1;
				minIndex = Math.min(minIndex, mid);
				continue;
			}
			left = mid + 1;
		}
	}

	public long countAcorn(int current) {
		long count = 0;
		for(int[] rule : rules) {
			if (current < rule[0]) continue;

			int end = Math.min(current, rule[1]);
			count += (end - rule[0]) / rule[2] + 1;
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		new three15732().solution();
	}
}
