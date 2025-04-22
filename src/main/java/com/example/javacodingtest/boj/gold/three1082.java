package com.example.javacodingtest.boj.gold;

import java.util.*;
import java.io.*;
/*
 @author ranunclulus
 @since 2025.04.22
 @link https://www.acmicpc.net/problem/1082
 @timecomplex
 @performance 14328KB 104MS
 @category
 @note
 */
public class three1082 {

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	StringTokenizer tokenizer;
	StringBuilder builder = new StringBuilder();
	static int N, M;
	static int[] numbers;
	static int[] answer = new int[51];

	public void solution() throws IOException {
		N = Integer.parseInt(reader.readLine());
		numbers = new int[N];
		tokenizer = new StringTokenizer(reader.readLine());

		int minValue = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(tokenizer.nextToken());
			if (numbers[i] < minValue) {
				minValue = numbers[i];
				minIndex = i;
			}
		}
		M = Integer.parseInt(reader.readLine());

		Arrays.fill(answer, -1);
		int index = 0;
		while (index < 51) {
			if (M < minValue) break;
			answer[index++] = minIndex;
			M -= minValue;
		}

		int start = 0;
		for (int i = 0; i < index; i++) {
			for (int j = N - 1; j >= 0; j--) {
				if (M >= (numbers[j] - minValue)) {
					answer[i] = j;
					M -= (numbers[j] - minValue);
					break;
				}
			}

			if (answer[start] == 0) {
				start++;
				M += minValue;
			}
		}
		if (start == index) {
			builder.append(0);
		} else {
			for (int i = start; i < index; i++) {
				builder.append(answer[i]);
			}
		}

		writer.write(builder.toString());
		writer.flush();
	}

	public static void main(String[] args) throws IOException {
		new three1082().solution();
	}
}

