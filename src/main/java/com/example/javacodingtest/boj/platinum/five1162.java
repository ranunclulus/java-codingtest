package com.example.javacodingtest.boj.platinum;

import java.util.*;
import java.io.*;

public class five1162{
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, M, K;
	static List<Node>[] roadMap;
	static long minTime = Long.MAX_VALUE;
	static long[][] distance;

	class Node implements Comparable<Node>{
		int road;
		long time;
		int count;

		Node (int road, long time) {
			this.road = road;
			this.time = time;
		}

		Node (int road, long time, int count) {
			this.road = road;
			this.time = time;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.time, o.time);
		}
	}

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		roadMap = new List[N + 1];
		for(int i = 0; i <= N; i++) {
			roadMap[i] = new ArrayList<>();
		}

		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int roadOne = Integer.parseInt(tokenizer.nextToken());
			int roadTwo = Integer.parseInt(tokenizer.nextToken());
			long timeCost = Long.parseLong(tokenizer.nextToken());
			roadMap[roadOne].add(new Node(roadTwo, timeCost));
			roadMap[roadTwo].add(new Node(roadOne, timeCost));
		}

		distance = new long[N + 1][K + 1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(distance[i], Long.MAX_VALUE);
		}
		distance[1][0] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int nowRoad = now.road;
			long nowTime = now.time;
			int nowCount = now.count;
			if (distance[nowRoad][nowCount] < nowTime) continue;
			for(Node node : roadMap[nowRoad]) {
				// 도로 건설 안 할 때
				if (distance[node.road][nowCount] > distance[nowRoad][nowCount] + node.time) {
					distance[node.road][nowCount] = distance[nowRoad][nowCount] + node.time;
					pq.add(new Node(node.road, distance[node.road][nowCount], nowCount));
				}

				// 도로 건설 할 때
				if (nowCount < K) { // K 번 미만만 가능
					if (distance[node.road][nowCount + 1] > distance[nowRoad][nowCount]) {
						distance[node.road][nowCount + 1] = distance[nowRoad][nowCount];
						pq.add(new Node(node.road, distance[node.road][nowCount + 1], nowCount + 1));
					}
				}
			}
		}

		for(int i = 0; i <= K; i++) {
			minTime = Math.min(minTime, distance[N][i]);
		}

		builder.append(minTime);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		new five1162().solution();
	}

}
