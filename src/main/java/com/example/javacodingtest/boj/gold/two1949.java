package com.example.javacodingtest.boj.gold;

import java.util.*;
import java.io.*;

/*
 @author ranunclulus
 @since 2025.04.12
 @link https://www.acmicpc.net/problem/1949
 @timecomplex
 @performance 23540KB 224MS
 @category
 @note
 */
public class two1949 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N;
	static int[] population;
	static int[][] dp;
	static List<List<Integer>> adjList;

	public void solution() throws IOException {
		init();
		int maxPopulation = Math.max(calculation(0, 0, -1), calculation(0, 1, -1));
		builder.append(maxPopulation);
		writer.write(builder.toString());
		writer.flush();
	}

	public int calculation(int townIndex, int isBest, int parent) {
		if (dp[townIndex][isBest] != 0) return dp[townIndex][isBest];

		if (isBest == 1) {
			dp[townIndex][1] += population[townIndex];
			for(int adj : adjList.get(townIndex)) {
				if (adj == parent) continue;
				dp[townIndex][1] += calculation(adj, 0, townIndex);
			}
		} else {
			for(int adj : adjList.get(townIndex)) {
				if (adj == parent) continue;
				dp[townIndex][0] += Math.max(calculation(adj, 0, townIndex), calculation(adj, 1, townIndex));
			}
		}
		return dp[townIndex][isBest];
	}



	private void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		population = new int[N];
		dp = new int[N][2];
		adjList = new ArrayList<>();

		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(tokenizer.nextToken());
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokenizer.nextToken()) - 1;
			int b = Integer.parseInt(tokenizer.nextToken()) - 1;

			adjList.get(a).add(b);
			adjList.get(b).add(a);
		}
	}

	public static void main(String[] args) throws IOException {
		new two1949().solution();
	}
}
