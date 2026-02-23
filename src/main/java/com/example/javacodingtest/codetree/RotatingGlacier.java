package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

public class RotatingGlacier {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, Q, SIZE;
	static int[][] map, copyMap;
	static int[] turnLevels;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int totalIce, maxIce;

	public void solution() throws IOException {
		init();
		for(int i = 0; i < Q; i++) {
			if (turnLevels[i] != 0) turnning((int) Math.pow(2, turnLevels[i]));
			melting();
		}
		getBiggestGroup();

		builder.append(totalIce).append("\n").append(maxIce);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void turnning(int turnSize) {
		copyMap = new int[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		for(int i = 0; i < SIZE; i += turnSize) {
			for(int j = 0; j < SIZE; j += turnSize) {
				turnSqure(i, j, turnSize / 2);
			}
		}
	}

	public void turnSqure(int row, int col, int turnSize) {

		for(int i = row; i < row + turnSize; i++) {
			for(int j = col; j < col + turnSize; j++) {
				map[i][j] = copyMap[i + turnSize][j];
			}
		}

		for(int i = row; i < row + turnSize; i++) {
			for(int j = col + turnSize; j < col + 2 * turnSize; j++) {
				map[i][j] = copyMap[i][j - turnSize];
			}
		}

		for(int i = row + turnSize; i < row + 2 * turnSize; i++) {
			for(int j = col + turnSize; j < col + 2 * turnSize; j++) {
				map[i][j] = copyMap[i - turnSize][j];
			}
		}

		for(int i = row + turnSize; i < row + 2 * turnSize; i++) {
			for(int j = col; j < col + turnSize; j++) {
				map[i][j] = copyMap[i][j + turnSize];
			}
		}
	}

	public void melting() {
		totalIce = 0;
		boolean[][] isMelted = new boolean[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if (map[i][j] == 0) continue;
				int iceCount = 0;
				for(int[] delta : deltas) {
					int nextRow = i + delta[0];
					int nextCol = j + delta[1];

					if (nextRow < 0 || nextRow >= SIZE || nextCol < 0 || nextCol >= SIZE) continue;
					if (map[nextRow][nextCol] == 0) continue;

					iceCount++;
				}

				if (iceCount >= 3) continue;
				isMelted[i][j] = true;
			}
		}


		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if (isMelted[i][j]) map[i][j]--;
			}
		}

		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				totalIce += map[i][j];
			}
		}
	}

	public void getBiggestGroup() {
		maxIce = 0;
		boolean[][] visited = new boolean[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if (visited[i][j]) continue;
				if (map[i][j] == 0) continue;

				Deque<int[]> toVisit = new ArrayDeque<>();
				toVisit.add(new int[] {i, j});
				visited[i][j] = true;
				int size = 0;

				while(!toVisit.isEmpty()) {
					int[] now = toVisit.poll();
					size++;

					for(int[] delta : deltas) {
						int nextRow = now[0] + delta[0];
						int nextCol = now[1] + delta[1];

						if (nextRow < 0 || nextRow >= SIZE || nextCol < 0 || nextCol >= SIZE) continue;
						if (map[nextRow][nextCol] == 0) continue;
						if (visited[nextRow][nextCol]) continue;

						toVisit.add(new int[] {nextRow, nextCol});
						visited[nextRow][nextCol] = true;
					}
				}

				maxIce = Math.max(maxIce, size);

			}
		}
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		Q = Integer.parseInt(tokenizer.nextToken());
		SIZE = (int) Math.pow(2, N);

		map = new int[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < SIZE; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		turnLevels = new int[Q];
		tokenizer = new StringTokenizer(reader.readLine());
		for(int i = 0; i < Q; i++) {
			turnLevels[i] = Integer.parseInt(tokenizer.nextToken());
		}
	}


	public static void main(String[] args) throws IOException {
		new RotatingGlacier().solution();
	}

}
