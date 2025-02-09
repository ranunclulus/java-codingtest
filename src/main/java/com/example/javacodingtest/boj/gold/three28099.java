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
 @since 2025.02.09
 @link https://www.acmicpc.net/problem/28099
 @timecomplex
 @performance 66708kb 396ms
 @category
 @note
 */
public class three28099 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int testNum, n;
	static int[] arr, segmentTree, numberIndex;
	static boolean flag;

	public void solution() throws IOException {
		testNum = Integer.parseInt(reader.readLine());
		for (int i = 0; i < testNum; i++) {
			n = Integer.parseInt(reader.readLine());
			arr = new int[n + 1];
			segmentTree = new int[n * 4];
			numberIndex = new int[n + 1];
			Arrays.fill(numberIndex, -1);

			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(tokenizer.nextToken());
			}
			arr[n] = 0;

			init(1, 0, n - 1);
			segmentTree[0] = n;

			flag = true;
			for (int j = 0; j < n; j++) {
				int num = arr[j];
				if (numberIndex[num] == -1) {
					numberIndex[num] = j;
				} else {
					int node = checkSection(numberIndex[num] + 1, j - 1, 1, 0, n - 1);
					if (arr[segmentTree[node]] > num) {
						flag = false;
						break;
					}
					numberIndex[num] = j;
				}
			}
			builder.append(flag ? "Yes\n" : "No\n");
		}
		writer.write(builder.toString());
		writer.flush();
	}


	public int init(int node, int start, int end) {
		if (start == end) {
			segmentTree[node] = start;
			return segmentTree[node];
		}
		else {
			int middle = (start + end) / 2;
			int left = init(node * 2, start, middle);
			int right = init(node * 2 + 1, middle + 1, end);
			segmentTree[node] = (arr[left] >= arr[right]) ? left : right;
			return segmentTree[node];
		}
	}

	public int checkSection(int left, int right, int node, int start, int end) {
		if (left > right) return 0;
		if (end < left || right < start) return 0;
		if (left <= start && end <= right) return node;

		int middle = (start + end) / 2;
		int leftValue = checkSection(left, right, node * 2, start, middle);
		int rightValue = checkSection(left, right, node * 2 + 1, middle + 1, end);

		return (arr[segmentTree[leftValue]] < arr[segmentTree[rightValue]] ? rightValue : leftValue);

	}

	public static void main(String[] args) throws IOException {
		new three28099().solution();
	}
}
