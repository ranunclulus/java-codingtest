package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.11
 @link https://www.codetree.ai/ko/frequent-problems/problems/tail-catch-play/description
 @timecomplex
 @performance
 @category
 @note
 */
public class TailCatchPlay {
	class Point {
		int type;
		int row;
		int col;
		public Point(int type, int row, int col) {
			this.type = type;
			this.row = row;
			this.col = col;
		}
	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, K, score;
	static int[][] map;
	static int[][] deltas = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	static List<Point>[] teams;

	public void solution() throws IOException{
		init();

		for (int turn = 0; turn < K; turn++) {
			for(List<Point> team : teams){
				moveTeam(team);
			}

			int direction = turn % (N * 4) / N;
			int times = turn % (N * 4) % N;
			Point target = throwBall(direction, times);

			if (!(target.type == -1 && target.row == -1 && target.col == -1)) {
				getScore(target);
			}
		}

		builder.append(score);
		writer.write(builder.toString());
		writer.flush();
	}

	public void getScore(Point target) {
		for(int i = 0; i < M; i++) {
			List<Point> team = teams[i];
			for (int j = 0; j < team.size(); j++) {
				Point point = team.get(j);
				if (point.type == 4) break;

				if (point.type == target.type && point.row == target.row && point.col == target.col) {
					score += (j + 1) * (j + 1);
					reverseTeam(team, i);
					return;
				}
			}
		}
	}

	public void reverseTeam(List<Point> team, int teamIndex) {
		int startRow = 0;
		int startCol = 0;
		for(Point point : team) {
			if (point.type == 4) continue;
			if (point.type == 2) continue;

			if (point.type == 3) {
				map[point.row][point.col] = 1;
				startRow = point.row;
				startCol = point.col;
			}
			if (point.type == 1) map[point.row][point.col] = 3;
		}

		teams[teamIndex] = new ArrayList<>();
		makeTeam(startRow, startCol, teamIndex, new boolean[N][N]);
	}

	public Point throwBall(int direction, int times) {
		if (direction == 0) {
			for (int i = 0; i < N; i++) {
				if (map[times][i] >= 1 && map[times][i] <= 3) {
					return new Point(map[times][i], times, i);
				}
			}
		} else if (direction == 1) {
			for (int i = N - 1; i >= 0; i--) {
				if (map[i][times] >= 1 && map[i][times] <= 3) {
					return new Point(map[i][times], i, times);
				}
			}
		} else if (direction == 2) {
			times = (N - 1) - times;
			for (int i = N - 1; i >= 0; i--) {
				if (map[times][i] >= 1 && map[times][i] <= 3) {
					return new Point(map[times][i], times, i);
				}
			}
		} else if (direction == 3) {
			times = (N - 1) - times;
			for (int i = 0; i < N; i++) {
				if (map[i][times] >= 1 && map[i][times] <= 3) {
					return new Point(map[i][times], i, times);
				}
			}
		}

		return new Point(-1, -1, -1);
	}

	public void moveTeam(List<Point> team) {
		List<Point> newTeam = new ArrayList<>();
		int size = team.size();
		for(int i = size - 1; i >= 0; i--) {
			Point original = team.get(i);
			Point next = team.get((i + size - 1) % size);

			newTeam.add(new Point(original.type, next.row, next.col));
		}

		team.clear();
		for (int i = newTeam.size() - 1; i >= 0; i--) {
			team.add(newTeam.get(i));

		}

		for(Point point : team) {
			map[point.row][point.col] = point.type;
		}
	}

	public void init() throws IOException{
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		teams = new List[M];
		for (int i = 0; i < M; i++) {
			teams[i] = new ArrayList<>();
		}

		int teamIndex = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					makeTeam(i, j, teamIndex, new boolean[N][N]);
					teamIndex++;
				}
			}
		}
	}

	public void makeTeam(int row, int col, int teamIndex, boolean[][] visited) {
		if (visited[row][col]) return;

		teams[teamIndex].add(new Point(map[row][col], row, col));
		visited[row][col] = true;

		for(int[] delta : deltas) {
			int nextRow = row + delta[0];
			int nextCol = col + delta[1];

			if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
			if (visited[nextRow][nextCol]) continue;
			if (map[nextRow][nextCol] == map[row][col] || map[nextRow][nextCol] - 1 == map[row][col])
				makeTeam(nextRow, nextCol, teamIndex, visited);
		}
	}

	public static void main(String[] args) throws IOException {
		new com.example.javacodingtest.codetree.TailCatchPlay().solution();
	}
}
