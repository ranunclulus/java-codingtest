package com.example.javacodingtest.boj.gold;

import java.util.*;
import java.io.*;

/*
 @author ranunclulus
 @since 2025.08.26
 @link https://www.acmicpc.net/problem/16947
 @timecomplex
 @performance 142340KB 680MS
 @category
 @note
 */
public class three16947_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N;
	static List<Integer>[] adjList;
	static boolean[] isCycled;

	public void solution() throws IOException {
		N = Integer.parseInt(reader.readLine());
		adjList = new List[N];
		for(int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokenizer.nextToken()) - 1;
			int b = Integer.parseInt(tokenizer.nextToken()) - 1;
			adjList[a].add(b);
			adjList[b].add(a);
		}

		for(int i = 0; i < N; i++) {
			isCycled = new boolean[N];
			if (findCycle(i, i, i)) break;
		}

		for(int i = 0; i < N; i++) {
			countDistance(i);
		}

		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public boolean findCycle(int prev, int node, int start) {
		isCycled[node] = true;

		for(int i = 0; i < adjList[node].size(); i++) {
			int nextNode = adjList[node].get(i);

			if (!isCycled[nextNode]) {
				if (findCycle(node, nextNode, start)) return true;
			}
			if (nextNode != prev && nextNode == start) return true;
		}

		isCycled[node] = false;
		return false;
	}

	public void countDistance(int node) {
		Deque<int[]> toVisit = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		toVisit.add(new int[] {node, 0});
		visited[node] = true;

		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			if (isCycled[now[0]]) {
				builder.append(now[1]).append(' ');
				return;
			}

			for(int next : adjList[now[0]]) {
				if (visited[next]) continue;
				toVisit.add(new int[] {next, now[1] + 1});
				visited[next] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new three16947_2().solution();
	}

}

