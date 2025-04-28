package com.example.javacodingtest.boj.gold;

import java.util.*;
import java.io.*;
/*
 @author ranunclulus
 @since 2025.04.28
 @link https://www.acmicpc.net/problem/1655
 @timecomplex
 @performance 33888KB 420MS
 @category
 @note
 */
public class two1655 {

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	StringTokenizer tokenizer;
	StringBuilder builder = new StringBuilder();
	static int N;
	static PriorityQueue<Integer> minHeap, maxHeap;

	public void solution() throws IOException {
		N = Integer.parseInt(reader.readLine());
		minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(reader.readLine());

			if (minHeap.size() == maxHeap.size()) {
				maxHeap.offer(value);
			} else {
				minHeap.offer(value);
			}

			if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				if (maxHeap.peek() > minHeap.peek()) {
					int temp = maxHeap.poll();
					maxHeap.offer(minHeap.poll());
					minHeap.offer(temp);
				}
			}

			builder.append(maxHeap.peek()).append('\n');
		}

		writer.write(builder.toString());
		writer.flush();
	}

	public static void two1655(String[] args) throws IOException {
		new Main().solution();
	}
}

