package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TreeKillAll {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, K, C;
	static int[][] map;
	static int[][] toxic;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	static int deadTreeCount;

	class Node implements Comparable<Node> {
		int count;
		int row;
		int col;

		Node(int count, int row, int col) {
			this.count = count;
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Node o) {
			if (this.count == o.count) {
				if (this.row == o.row) return Integer.compare(this.col, o.col);
				return Integer.compare(this.row, o.row);
			}
			return -Integer.compare(this.count, o.count);
		}

	}

	public void solution() throws IOException {
		init();
		for(int turn = 0; turn < M; turn++) {

			growTree();
			spreadTree();
			Node node = decideToxicPoint();
			if (node != null) spreadToxic(node);
			resetToxic();

		}
		builder.append(deadTreeCount);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void growTree() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] <= 0) continue;

				int count = 0;
				for(int k = 0; k < 4; k++) {
					int nextRow = i + deltas[k][0];
					int nextCol = j + deltas[k][1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
					if (map[nextRow][nextCol] <= 0) continue;

					count++;
				}

				map[i][j] += count;
			}
		}
	}



	public void spreadTree() {
		int[][] newMap = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				newMap[i][j] = map[i][j];
			}
		}

		int[][] devideCount = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] <= 0) continue;

				int count = 0;
				for(int k = 0; k < 4; k++) {
					int nextRow = i + deltas[k][0];
					int nextCol = j + deltas[k][1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
					if (map[nextRow][nextCol] != 0) continue;
					if (toxic[nextRow][nextCol] != 0) continue;

					count++;
				}

				devideCount[i][j] = count;
			}
		}


		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] <= 0) continue;

				for(int k = 0; k < 4; k++) {
					int nextRow = i + deltas[k][0];
					int nextCol = j + deltas[k][1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
					if (map[nextRow][nextCol] != 0) continue;
					if (toxic[nextRow][nextCol] != 0) continue;

					newMap[nextRow][nextCol] += map[i][j] / devideCount[i][j];
				}

			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = newMap[i][j];
			}
		}

	}

	public Node decideToxicPoint() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for(int i = 0; i < N; i++) {
			for(int j = 0;j < N; j++) {
				if (map[i][j] <= 0) continue;

				int count = map[i][j];

				for(int k = 4; k < 8; k++) {
					for(int l = 1; l <= K; l++) {
						int nextRow = i + deltas[k][0] * l;
						int nextCol = j + deltas[k][1] * l;

						if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) break;
						if (map[nextRow][nextCol] <= 0) break;

						count += map[nextRow][nextCol];
					}
				}
				pq.add(new Node(count, i, j));
			}
		}
		return pq.poll();
	}

	public void spreadToxic(Node node) {
		toxic[node.row][node.col] = C + 1;
		map[node.row][node.col] = 0;
		for(int i = 4; i < 8; i++) {
			for(int j = 1; j <= K; j++) {
				int nextRow = node.row + deltas[i][0] * j;
				int nextCol = node.col + deltas[i][1] * j;

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) break;
				toxic[nextRow][nextCol] = C + 1;
				if (map[nextRow][nextCol] <= 0) break;
				map[nextRow][nextCol] = 0;
			}
		}

		deadTreeCount += node.count;

	}

	public void resetToxic() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (toxic[i][j] > 0) toxic[i][j]--;
			}
		}
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		toxic = new int[N][N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
	}


	public static void main(String[] args) throws IOException {
		new TreeKillAll().solution();
	}



}