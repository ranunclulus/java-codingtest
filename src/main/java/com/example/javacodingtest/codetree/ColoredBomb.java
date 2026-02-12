package com.example.javacodingtest.codetree;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ColoredBomb {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M;
	static int[][] map;
	static int score;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	class Bomb implements Comparable<Bomb>{
		int count;
		int redCount;
		int row;
		int col;


		Bomb(int count, int redCount, int row, int col) {
			this.count = count;
			this.redCount = redCount;
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Bomb o) {
			if (this.count == o.count) {
				if (this.redCount == o.redCount) {
					if (this.row == o.row) {
						return Integer.compare(this.col, o.col);
					}
					return -Integer.compare(this.row, o.row);
				}
				return Integer.compare(this.redCount, o.redCount);
			}
			return -Integer.compare(this.count, o.count);
		}


	}

	public void solution() throws IOException {
		init();

		while(true) {
			Bomb bomb = selectBomb();
			if (bomb == null) break;
			killColor(bomb);
			gravity();
			turnLeft();
			gravity();
		}

		builder.append(score);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public Bomb selectBomb() {
		PriorityQueue<Bomb> pq = new PriorityQueue<>();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] <= 0) continue;
				int count = 0;
				int redCount = 0;
				int row = i;
				int col = j;
				int color = map[i][j];
				Deque<int[]> toVisit = new ArrayDeque<>();
				boolean[][] visited = new boolean[N][N];
				if (score == 361 && i == 3 && j == 3) {
					int stop = 4;
				}
				toVisit.add(new int[] {i, j});
				visited[i][j] = true;

				while(!toVisit.isEmpty()) {
					int[] now = toVisit.poll();
					count++;

					for(int[] delta : deltas) {
						int nextRow = now[0] + delta[0];
						int nextCol = now[1] + delta[1];

						if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
						if (map[nextRow][nextCol] == -1) continue;
						if (map[nextRow][nextCol] == -2) continue;
						if (visited[nextRow][nextCol]) continue;
						if (map[nextRow][nextCol] != 0 && map[nextRow][nextCol] != color) continue;
						if (map[nextRow][nextCol] == 0) redCount++;

						toVisit.add(new int[] {nextRow, nextCol});
						visited[nextRow][nextCol] = true;

						if (map[nextRow][nextCol] != 0 && nextRow >= row) {
							row = nextRow;
							col = nextCol;
							if (map[nextRow][nextCol] != 0 && nextCol < col) col = nextCol;
						}


					}
				}
				if (count <= 1) continue;
				pq.add(new Bomb(count, redCount, row, col));
			}
		}

		return pq.poll();
	}

	public void killColor(Bomb bomb) {
		Deque<int[]> toVisit = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		toVisit.add(new int[] {bomb.row, bomb.col});
		visited[bomb.row][bomb.col] = true;
		int color = map[bomb.row][bomb.col];

		while(!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			map[now[0]][now[1]] = -2;

			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (map[nextRow][nextCol] == -1) continue;
				if (visited[nextRow][nextCol]) continue;
				if (map[nextRow][nextCol] != 0 && map[nextRow][nextCol] != color) continue;

				toVisit.add(new int[] {nextRow, nextCol});
				visited[nextRow][nextCol] = true;

			}
		}
		score += (bomb.count)* (bomb.count);
	}

	public void gravity() {
		int[][] newMap = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(newMap[i], -2);
			for(int j = 0; j < N; j++) {
				if (map[i][j] == -1) newMap[i][j] = map[i][j];
			}
		}

		for(int col = 0; col < N; col++) {
			List<Integer> numList = new ArrayList<>();
			for(int row = 0; row < N; row++) {
				if (map[row][col] >= 0) numList.add(map[row][col]);

				if (map[row][col] == -1) {
					int index = numList.size() - 1;
					for(int r = row - 1; r >= 0; r--) {
						if (index == -1) break;
						newMap[r][col] = numList.get(index--);
					}
					numList.clear();
				}

				if (row == N - 1) {
					int index = numList.size() - 1;
					for(int r = N - 1; r >= 0; r--) {
						if (index == -1) break;
						newMap[r][col] = numList.get(index--);
					}
					numList.clear();
				}
			}
		}

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = newMap[i][j];
			}
		}
	}

	public void turnLeft() {
		int[][] newMap = new int[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				newMap[N - 1 - j][i] = map[i][j];
			}
		}

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = newMap[i][j];
			}
		}
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
	}


	public static void main(String[] args) throws IOException {
		new ColoredBomb().solution();
	}

}
