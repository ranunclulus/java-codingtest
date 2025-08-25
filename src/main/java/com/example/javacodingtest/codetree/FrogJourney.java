package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

/*
 @author ranunclulus
 @since 2025.08.25
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/frog-journey/submissions?page=1&page_size=20
 @timecomplex
 @performance
 @category
 @note
 */
public class FrogJourney {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static final int MAX_LAKE_SIZE = 50;
	static final int MAX_JOURNEY_COUNT = 1000;
	static final int MAX_JUMP_POWER = 5;
	static int lakeSize, journeyCount;
	static char[][] lakeMap = new char[MAX_LAKE_SIZE + 1][MAX_LAKE_SIZE + 1];
	static ArrayList<Edge>[] graph = new ArrayList[MAX_LAKE_SIZE * MAX_LAKE_SIZE * MAX_JUMP_POWER];
	static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	class Edge {
		int nextEdge;
		int timeCost;

		Edge (int nextEdge, int timeCost) {
			this.nextEdge = nextEdge;
			this.timeCost = timeCost;
		}
	}

	class Node implements Comparable<Node>{
		int edge;
		int time;

		Node(int edge, int time) {
			this.edge = edge;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}

	public int getEdge(int row, int col, int jump) {
		return ((row - 1) * lakeSize + (col - 1)) * MAX_JUMP_POWER + (jump - 1);
	}


	public void makeGraph() {
		for(int i = 0; i < lakeSize * lakeSize * MAX_JUMP_POWER; i++) {
			graph[i] = new ArrayList<>();
		}

		for(int i = 1; i <= lakeSize; i++) {
			for(int j = 1; j <= lakeSize; j++) {
				if (lakeMap[i][j] != '.') continue;
				for(int k = 1; k <= MAX_JUMP_POWER; k++) {
					int nowEdge = getEdge(i, j, k);

					for (int[] delta : deltas) {
						int nextRow = i;
						int nextCol = j;
						boolean canJump = true;
						for(int t = 0; t < k; t++) {
							nextRow += delta[0];
							nextCol += delta[1];

							if (nextRow < 1 || nextRow > lakeSize || nextCol < 1  || nextCol > lakeSize || lakeMap[nextRow][nextCol] == '#') {
								canJump = false;
								break;
							}
						}
						canJump = (canJump && (lakeMap[nextRow][nextCol] == '.'));
						if (canJump) {
							int nextEdge = getEdge(nextRow, nextCol, k);
							graph[nowEdge].add(new Edge(nextEdge, 1));
						}
					}

					if (k < MAX_JUMP_POWER) {
						int nextEdge = getEdge(i, j, k + 1);
						graph[nowEdge].add(new Edge(nextEdge, (k + 1) * (k + 1)));
					}

					for(int t = 1; t < k; t++) {
						int nextEdge = getEdge(i, j, t);
						graph[nowEdge].add(new Edge(nextEdge, 1));
					}
				}
			}
		}
	}

	public void journey(int startRow, int startCol, int endRow, int endCol) {
		int[] distance = new int[lakeSize * lakeSize * MAX_JUMP_POWER];
		Arrays.fill(distance, Integer.MAX_VALUE);

		PriorityQueue<Node> frogPQ = new PriorityQueue<>();
		int startEdge = getEdge(startRow, startCol, 1);
		distance[startEdge] = 0;
		frogPQ.add(new Node(startEdge, 0));

		int minTime = Integer.MAX_VALUE;
		while (!frogPQ.isEmpty()) {
			Node now = frogPQ.poll();
			if (distance[now.edge] < now.time) continue;

			int tempEdge = now.edge;
			int nowJump = (tempEdge % MAX_JUMP_POWER) + 1;
			tempEdge /= MAX_JUMP_POWER;
			int nowCol = (tempEdge % lakeSize) + 1;
			tempEdge /= lakeSize;
			int nowRow = (tempEdge % lakeSize) + 1;

			if (nowRow == endRow && nowCol == endCol) {
				minTime = now.time;
				break;
			}

			for(Edge edge : graph[now.edge]) {
				if (distance[edge.nextEdge] > now.time + edge.timeCost) {
					distance[edge.nextEdge] = now.time + edge.timeCost;
					frogPQ.add(new Node(edge.nextEdge, distance[edge.nextEdge]));
				}
			}
		}
		builder.append(minTime == Integer.MAX_VALUE ? -1 : minTime).append('\n');
	}

	public void solution() throws IOException {
		lakeSize = Integer.parseInt(reader.readLine());
		for(int i = 1; i <= lakeSize; i++) {
			String input = reader.readLine();
			for(int j = 1; j <= lakeSize; j++) {
				lakeMap[i][j] = input.charAt(j - 1);
			}
		}
		makeGraph();

		journeyCount = Integer.parseInt(reader.readLine());
		for(int i = 0; i < journeyCount; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int startRow = Integer.parseInt(tokenizer.nextToken());
			int startCol = Integer.parseInt(tokenizer.nextToken());
			int endRow = Integer.parseInt(tokenizer.nextToken());
			int endCol = Integer.parseInt(tokenizer.nextToken());
			journey(startRow, startCol, endRow, endCol);
		}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}


	public static void main(String[] args) throws IOException {
		new FrogJourney().solution();
	}

}
