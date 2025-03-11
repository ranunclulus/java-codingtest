package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.03.11
 @link https://www.acmicpc.net/problem/18166
 @timecomplex
 @performance
 @category
 @note
 */
public class four18166 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N;
	static int[] parents, counts;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		parents = new int[1000001];
		counts = new int[1000001];

		for(int i = 1; i <= 1000000; i++) {
			parents[i] = i;
			counts[i] = 1;
		}

		while (N --> 0) {
			tokenizer = new StringTokenizer(reader.readLine());

			if (tokenizer.nextToken().equals("I")) {
				int a =  Integer.parseInt(tokenizer.nextToken());
				int b = Integer.parseInt(tokenizer.nextToken());
				union(a, b);
			} else {
				int a = Integer.parseInt(tokenizer.nextToken());
				builder.append(counts[find(a)]).append('\n');
			}
		}
		writer.write(builder.toString());
		writer.flush();
	}

	public void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);

		if (aParent == bParent) return;

		if (aParent < bParent) {
			parents[bParent] = aParent;
			counts[aParent] += counts[bParent];
		}
		else {
			parents[aParent] = bParent;
			counts[bParent] += counts[aParent];
		}
	}

	public int find(int target) {
		if (parents[target] == target) return target;
		else return parents[target] = find(parents[target]);
	}

	public static void main(String[] args) throws IOException {
		new four18166().solution();
	}
}
