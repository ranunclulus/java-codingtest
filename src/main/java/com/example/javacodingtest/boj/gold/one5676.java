package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.02.10
 @link https://www.acmicpc.net/problem/5676
 @timecomplex
 @performance 103236kb 764ms
 @category
 @note
 */
public class one5676 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n, k;
	static int[] tree, arr;

	public void solution() throws IOException {
		String input = "";
		while ((input = reader.readLine()) != null) { // EOF 처리
			tokenizer = new StringTokenizer(input);

			n = Integer.parseInt(tokenizer.nextToken());
			k = Integer.parseInt(tokenizer.nextToken());

			arr = new int[n + 1];

			tokenizer = new StringTokenizer(reader.readLine());
			for (int i = 1; i <= n; i++) {
				int temp = Integer.parseInt(tokenizer.nextToken());

				// 오버플로우 방지
				arr[i] = (temp == 0) ? 0 : (temp > 0) ? 1 : -1;
			}

			tree = new int[n * 4];
			init(1, n, 1);

			while (k--> 0) {
				tokenizer = new StringTokenizer(reader.readLine());

				String command = tokenizer.nextToken();
				int i = Integer.parseInt(tokenizer.nextToken());

				if (command.equals("C")) {
					int v = Integer.parseInt(tokenizer.nextToken());
					v = Integer.compare(v, 0); // 오버플로우 방지

					update(1, n, 1, i, v);
				} else if (command.equals("P")) {
					int j = Integer.parseInt(tokenizer.nextToken());
					long res = mul(1, n, 1, i, j);

					builder.append((res == 0) ? 0 : (res > 0) ? "+" : "-");
				}
			}
			builder.append("\n");
		}

		writer.write(builder.toString());
		writer.flush();
	}

	public static int init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = arr[start];
		}

		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1);
	}

	public static int mul(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 1;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return mul(start, mid, node * 2, left, right) * mul(mid + 1, end, node * 2 + 1, left, right);
	}

	public static int update(int start, int end, int node, int idx, int val) {
		if (idx < start || idx > end) {
			return tree[node];
		}

		if (start == end) {
			return tree[node] = val;
		}

		int mid = (start + end) / 2;
		return tree[node] = update(start, mid, node * 2, idx, val) * update(mid + 1, end, node * 2 + 1, idx, val);
	}

	public static void main(String[] args) throws IOException {
		new one5676().solution();
	}
}
