package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.02.05
 @link https://www.acmicpc.net/problem/2263
 @timecomplex
 @performance 42032kb 1460ms
 @category
 @note
 */
public class one2263 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n, index;
	static int[] inOrder, postOrder, preOrder;

	public void solution() throws IOException {
		n = Integer.parseInt(reader.readLine());

		inOrder = new int[n];
		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < n; i++) {
			inOrder[i] = Integer.parseInt(tokenizer.nextToken());
		}

		postOrder = new int[n];
		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < n; i++) {
			postOrder[i] = Integer.parseInt(tokenizer.nextToken());
		}

		preOrder = new int[n];

		getPreOrder(0, n - 1, 0, n - 1);

		for(int value : preOrder) {
			builder.append(value).append(' ');
		}
		writer.write(builder.toString());
		writer.flush();
	}

	private void getPreOrder(int inStart, int inEnd, int postStart, int postEnd) {
		if (inStart > inEnd || postStart > postEnd) return;

		preOrder[index++] = postOrder[postEnd];

		int rootIndex = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inOrder[i] == postOrder[postEnd]) {
				rootIndex = i;
				break;
			}
		}

		getPreOrder(inStart, rootIndex - 1, postStart, postStart + rootIndex - inStart - 1);
		getPreOrder(rootIndex + 1, inEnd, postStart + rootIndex - inStart, postEnd - 1);
	}

	public static void main(String[] args) throws IOException {
		new one2263().solution();
	}
}
