package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranunclulus
 @since 2025.01.29
 @link https://www.acmicpc.net/problem/2461
 @timecomplex
 @performance 92624kb, 2740ms
 @category
 @note
 */
public class three2461 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokenizer;
	static int n, m;
	static int minValue = Integer.MAX_VALUE;
	static int[][] abilities;
	static int[] pointers;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(tokenizer.nextToken());
		m = Integer.parseInt(tokenizer.nextToken());

		abilities = new int[n][m];
		for (int i = 0; i < n; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < m; j++) {
				abilities[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
			Arrays.sort(abilities[i]);
		}

		pointers = new int[n];
		while (true) {
			int partialMin = abilities[0][pointers[0]];
			int partialMax = abilities[0][pointers[0]];
			int minIndex = 0;

			for (int i = 1; i < n; i++) {
				if (partialMin > abilities[i][pointers[i]]) {
					partialMin = abilities[i][pointers[i]];
					minIndex = i;
				}
				if (partialMax < abilities[i][pointers[i]]) {
					partialMax = abilities[i][pointers[i]];
				}
			}
			if ((partialMax - partialMin) < minValue) {
				minValue = (partialMax - partialMin);
			}
			if (++pointers[minIndex] >= m) break;
		}
		System.out.println(minValue);
	}


	public static void main(String[] args) throws IOException {
		new three2461().solution();
	}
}
