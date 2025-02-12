package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.02.12
 @link https://www.acmicpc.net/problem/23791
 @timecomplex
 @performance 113104kb 956ms
 @category
 @note
 */
public class three23791 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n, q, i, j, k;
	static int[][] taste, count, position;

	public void solution() throws IOException {
		n = Integer.parseInt(reader.readLine());
		taste = new int[2][n];
		count = new int[2 * n][2];
		position = new int[2 * n][2];

		for(int i = 0; i < 2; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < n; j++) {
				taste[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		int koreanIndex = 0, westernIndex = 0, index = 0;
		int koreanCount = 0, westernCount = 0;
		while (koreanIndex < n || westernIndex < n) {
			if (westernIndex >= n || (koreanIndex < n && (taste[0][koreanIndex] <= taste[1][westernIndex]))) {
				count[index][0] = ++koreanCount;
				count[index][1] = westernCount;
				position[index][0] = 1;
				position[index++][1] = ++koreanIndex;
			} else if (koreanIndex >= n || (westernIndex < n && (taste[1][westernCount] < taste[0][koreanIndex]))) {
				count[index][0] = koreanCount;
				count[index][1] = ++westernCount;
				position[index][0] = 2;
				position[index++][1] = ++westernIndex;
			}
		}

		q = Integer.parseInt(reader.readLine());
		for(int i=0; i<q; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int answer = binarySearch(Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken()));
			builder.append(position[answer][0]).append(" ").append(position[answer][1]).append("\n");
		}
		writer.write(builder.toString());
		writer.flush();
	}

	public static int binarySearch(int i, int j, int k) {
		int start = 0, end = 2 * n - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			int temp = Math.min(i, count[mid][0]) + Math.min(j, count[mid][1]);
			if (temp < k) start = mid + 1;
			else end = mid - 1;
		}
		return start;
	}

	public static void main(String[] args) throws IOException {
		new three23791().solution();
	}

}
