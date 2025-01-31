package com.example.javacodingtest.boj.platinum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranunclulus
 @since 2025.01.30
 @link https://www.acmicpc.net/problem/22991
 @timecomplex
 @performance
 @category
 @note
 */
public class five22991 {
	class Node implements Comparable<Node>{
		int index;
		int people;
		int time;

		public Node(int people, int time) {
			this.people = people;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			if(this.time == o.time) return -Integer.compare(this.people, o.people);
			return Integer.compare(this.time, o.time);
		}
	}
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n, m, answer;
	static Node[] buses, dispatches;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(tokenizer.nextToken());
		m = Integer.parseInt(tokenizer.nextToken());

		dispatches = new Node[n];
		for (int i = 0; i < n; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			dispatches[i] =( new Node(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
		}
		Arrays.sort(dispatches);

		buses = new Node[m];
		for (int i = 0; i < m; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			buses[i] = (new Node(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
		}
		Arrays.sort(buses);

		for (int i = 0; i < m; i++) {
			List<Node> candidate = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if (dispatches[j] == null) continue;
				if (dispatches[j].people <= buses[i].people && dispatches[j].time >= buses[i].time) {
					dispatches[j].index = j;
					candidate.add(dispatches[j]);
				}
			}

			if (candidate.size() == 0) continue;

			int partialMinIndex = 0;
			int partialMin = Integer.MAX_VALUE;
			for (int j = 0; j < candidate.size(); j++) {
				if (candidate.get(j).time - buses[i].time > partialMin) {
					partialMin = candidate.get(j).time - buses[i].time;
					partialMinIndex = candidate.get(j).index;
				}
			}

			answer++;
			dispatches[partialMinIndex] = null;
		}
		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
	}

	public static void main(String[] args) throws IOException {
		new five22991().solution();
	}
}