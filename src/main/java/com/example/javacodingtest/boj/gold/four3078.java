package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranunclulus
 @since 2025.01.31
 @link https://www.acmicpc.net/problem/3078
 @timecomplex
 @performance 37024kb 264ms
 @category
 @note
 */
public class four3078 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n, k;
	static long count;
	static int[] people, nameLength;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(tokenizer.nextToken());
		k = Integer.parseInt(tokenizer.nextToken());

		people = new int[n];
		nameLength = new int[21];

		for (int i = 0; i < n; i++) {
			people[i] = reader.readLine().length();
			if (i <= k) nameLength[people[i]]++;
		}

		count += nameLength[people[0]] - 1;
		nameLength[people[0]]--;

		for (int i = 1; i < n; i++) {
			if (i + k < n) nameLength[people[i + k]]++;
			count += nameLength[people[i]] - 1;
			nameLength[people[i]]--;
		}

		builder.append(count);
		writer.write(builder.toString());
		writer.flush();
	}

	public static void main(String[] args) throws IOException {
		new four3078().solution();
	}
}
