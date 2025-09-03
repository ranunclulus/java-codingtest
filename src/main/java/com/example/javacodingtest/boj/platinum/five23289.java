package com.example.javacodingtest.boj.platinum;

import java.io.*;
import java.util.*;

/*
 @author ranunclulus
 @since 2025.09.03
 @link https://www.acmicpc.net/problem/23289
 @timecomplex
 @performance
 @category
 @note
 */
public class five23289 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int R, C, K, W, choco;
	static int[][] map, add;             // 오른쪽 왼쪽 위 아래
	static int[][] deltas = new int[][] {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static int[][][] spreadDeltas = new int[][][] {{{0, 0}, {0, 0}, {0, 0}},
		{{-1, 1}, {0, 1}, {1, 1}},
		{{-1, -1}, {0, -1}, {1, -1}},
		{{-1, -1}, {-1, 0}, {-1, 1}},
		{{1, -1}, {1, 0}, {1, 1}}};
	static boolean[][][][] isWall;
	static List<Point> heaters;
	static List<Point> targets;

	class Point {
		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + ", type=" + type
				+ "]";
		}

		int row;
		int col;
		int type;

		Point(int row, int col, int type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}
	}

	public void solution() throws IOException{
		init();

		while (choco <= 100 && !clear()) {
			heating();
			arrange();
			choco++;
		}

		builder.append(choco);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}


	public boolean clear() {
		for(Point target : targets) {
			if (map[target.row][target.col] < K) return false;
		}
		return true;
	}


	public void arrange() {
		int[][] temp = new int[R][C];

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if (map[i][j] == 0) continue;

				for(int d = 1; d <= 4; d++) {
					int nextRow = i + deltas[d][0];
					int nextCol = j + deltas[d][1];

					if (!inRange(nextRow, nextCol)) continue;
					if (isWall[i][j][nextRow][nextCol]) continue;

					if (map[i][j] > map[nextRow][nextCol]) {
						int diff = Math.abs(map[i][j] - map[nextRow][nextCol]) / 4;
						temp[i][j] -= diff;
						temp[nextRow][nextCol] += diff;
					}
				}
			}
		}

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] += temp[i][j];

				if (i == 0 || i == R - 1 || j == 0 || j == C - 1) {
					if (map[i][j] > 0) map[i][j] -=1;
				}
			}
		}
	}


	public void heating() {
		add = new int[R][C];
		for(Point heater : heaters) {
			heaterWork(heater);
		}

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] += add[i][j];
			}
		}
	}


	public void heaterWork(Point heater) {
		boolean[][] visited = new boolean[R][C];
		Deque<Point> toVisit = new ArrayDeque<>();

		int power = 5;
		int nextRow = heater.row + deltas[heater.type][0];
		int nextCol = heater.col + deltas[heater.type][1];
		int direction = heater.type;

		add[nextRow][nextCol] += power;
		visited[nextRow][nextCol] = true;
		toVisit.add(new Point(nextRow, nextCol, 2));

		while(!toVisit.isEmpty()) {
			Point now = toVisit.poll();
			if (now.type > 5) continue;

			for(int i = 0; i < 3; i++) {
				nextRow = now.row + spreadDeltas[direction][i][0];
				nextCol = now.col + spreadDeltas[direction][i][1];

				if (!inRange(nextRow, nextCol)) continue;
				if (visited[nextRow][nextCol]) continue;
				if (isWall(now.row, now.col, nextRow, nextCol, direction)) continue;

				add[nextRow][nextCol] += (power - now.type + 1);
				toVisit.add(new Point(nextRow, nextCol, now.type + 1));
				visited[nextRow][nextCol] = true;
			}
		}
	}


	public boolean isWall(int row, int col, int nextRow, int nextCol, int direction) {
		if (row == nextRow || col == nextCol) {
			if (isWall[row][col][nextRow][nextCol]) return true;
		} else if (direction == 1 || direction == 2) {
			if (isWall[row][col][nextRow][col] || isWall[nextRow][col][nextRow][nextCol]) return true;
		} else {
			if (isWall[row][col][row][nextCol] || isWall[row][nextCol][nextRow][nextCol]) return true;
		}
		return false;
	}


	public boolean inRange(int row, int col) {
		return ((row >= 0) && (row < R) && (col >= 0) && (col < C));
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new int[R][C];
		heaters = new ArrayList<>();
		targets = new ArrayList<>();
		for(int i = 0; i < R; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < C; j++) {
				int value = Integer.parseInt(tokenizer.nextToken());
				if (value == 0) continue;
				else if (value == 5) {
					targets.add(new Point(i, j, -1));
				} else {
					heaters.add(new Point(i, j, value));
				}
			}
		}

		W = Integer.parseInt(reader.readLine());
		isWall = new boolean[R][C][R][C];
		for(int i = 0; i < W; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int row = Integer.parseInt(tokenizer.nextToken()) - 1;
			int col = Integer.parseInt(tokenizer.nextToken()) - 1;
			int type = Integer.parseInt(tokenizer.nextToken());

			if (type == 0) {
				isWall[row][col][row - 1][col] = true;
				isWall[row - 1][col][row][col] = true;
			} else if (type == 1) {
				isWall[row][col][row][col + 1] = true;
				isWall[row][col + 1][row][col] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new five23289().solution();
	}


}