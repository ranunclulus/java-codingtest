package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.02.09
 @link https://www.acmicpc.net/problem/23791
 @timecomplex
 @performance 66708kb 396ms
 @category
 @note
 */
public class three23791_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n, q, i, j, k;
	static int[][] taste, count, position;

	public void solution() throws IOException {
		n = Integer.parseInt(reader.readLine());

		taste = new int[2][n];
		for (int i = 0; i < 2; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < n; j++) {
				taste[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		count = new int[2 * n][2]; // index 번째에 있을 때 한식과 양식의 수
		position = new int[2 * n][2]; // index 번째에 있을 때 한식이랑 양식 중 어디고 몇 번째에 있는지

		int koreanIndex = 0, westernIndex = 0, index = 0;
		int koreanCount = 0, westernCount = 0;

		while (koreanIndex < n || westernIndex < n) {
			// 한식의 순서일 때
			if (westernIndex >= n || (koreanIndex < n && (taste[0][koreanIndex] <= taste[1][westernIndex]))) {
				count[index][0] = ++koreanCount;
				count[index][1] = westernCount;
				position[index][0] = 1;
				position[index][1] = ++koreanIndex;
				index++;
			// 양식의 순서일 때
			} else if (koreanIndex >= n || (westernIndex < n && (taste[0][koreanIndex] > taste[1][westernIndex]))) {
				count[index][0] = koreanCount;
				count[index][1] = ++westernCount;
				position[index][1] = 2;
				position[index][0] = ++westernIndex;
				index++;
			}
		}

		q = Integer.parseInt(reader.readLine());
		while (q --> 0) {
			tokenizer = new StringTokenizer(reader.readLine());
			i = Integer.parseInt(tokenizer.nextToken());
			j = Integer.parseInt(tokenizer.nextToken());
			k = Integer.parseInt(tokenizer.nextToken());

			int answerIndex = binarySearch(i, j, k);
			builder.append(position[answerIndex][0]).append(" ").append(position[answerIndex][1]).append('\n');
		}
		writer.write(builder.toString());
		writer.flush();
	}

	public int binarySearch(int i, int j, int k) {
		int start = 0;
		int end = 2 * n - 1;
		while (start <= end) {
			int middle = (start + end) / 2;
			int temp = Math.min(i, count[middle][0]) + Math.min(j, count[middle][1]);
			if (temp < k) {
				start = middle + 1;
			} else {
				end = middle - 1;
			}
		}
		return start;
	}

	public static void main(String[] args) throws IOException {
		new three23791_2().solution();
	}
}
