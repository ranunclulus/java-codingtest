package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.03.31
 @link https://www.acmicpc.net/problem/1914
 @timecomplex
 @performance 47564KB 496MS
 @category
 @note
 */
public class five1914 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n;

	public void solution() throws IOException {
		n = Integer.parseInt(reader.readLine());
		builder.append(BigInteger.TWO.pow(n).subtract(BigInteger.ONE)).append('\n');

		if (n <= 20) hanoi(n, 1, 2, 3);

		writer.write(builder.toString());
		writer.flush();
	}

	private void hanoi(int n, int start, int mid, int end) {
		if (n == 1) {
			builder.append(start).append(' ').append(end).append('\n');
			return;
		}

		hanoi(n - 1, start, end, mid);
		builder.append(start).append(' ').append(end).append('\n');
		hanoi(n - 1, mid, start, end);
	}

	public static void main(String[] args) throws IOException {
		new five1914().solution();
	}
}
