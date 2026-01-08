package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2026.01.08
 @link https://www.codetree.ai/ko/frequent-problems/problems/magical-forest-exploration/description
 @timecomplex
 @performance
 @category
 @note
 */
public class MagicalForestExploration_3 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int R, C, K, answer;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {0, 0}};
	static int[][] map;
	static boolean[][] isExit;
	static Golem[] golems;

	class Golem {
		int num;
		int row;
		int col;
		int exit;

		Golem (int num, int row, int col, int exit) {
			this.num = num;
			this.row = row;
			this.col = col;
			this.exit = exit;
		}

	}

	public void solution() throws IOException {
		init();
		for (int i = 0; i < K; i++) {
			Golem golem = golems[i];

			while (true) {
				// 0: 남쪽, 1: 서쪽회전, 2: 동쪽회전
				boolean isMoved = false;
				for(int j = 0; j <= 2; j++) {
					if (canMove(golem, j)) {
						moveGolem(golem, j);
						isMoved = true;
						break;
					}
				}
				if (!isMoved) break;
			}

			if (golem.row <= 3) makeClear();
			else {
				markExit(golem);
				int score = calculateScore(golem.row, golem.col);
				answer += score;
			}
		}
		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public boolean canMove(Golem golem, int d) {
		int row = golem.row;
		int col = golem.col;
		int nextRow = row;
		int nextCol = col;

		if (d == 0) {
			nextRow = row + 1;
			nextCol = col - 1;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row + 2;
			nextCol = col;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row + 1;
			nextCol = col + 1;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			return true;
		} else if (d == 1) {
			nextRow = row - 1;
			nextCol = col - 1;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row;
			nextCol = col - 2;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row + 1;
			nextCol = col - 2;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row + 1;
			nextCol = col - 1;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row + 2;
			nextCol = col - 1;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			return true;
		} else {
			nextRow = row - 1;
			nextCol = col + 1;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row;
			nextCol = col + 2;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row + 1;
			nextCol = col + 2;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row + 1;
			nextCol = col + 1;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			nextRow = row + 2;
			nextCol = col + 1;
			if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || map[nextRow][nextCol] >= 1)
				return false;

			return true;
		}
	}

	public void moveGolem(Golem golem, int d) {
		for(int[] delta : deltas) {
			int nextRow = golem.row + delta[0];
			int nextCol = golem.col + delta[1];

			if (map[nextRow][nextCol] >= 1)
				map[nextRow][nextCol] = 0;
		}

		if (d == 0) {
			golem.row++;
		} else if (d == 1) {
			golem.row++;
			golem.col--;
			if (golem.exit == 0) { //북
				golem.exit = 3;
			} else if (golem.exit == 1) { //동
				golem.exit = 0;
			} else if (golem.exit == 2) { //남
				golem.exit = 1;
			} else if (golem.exit == 3) { //서
				golem.exit = 2;
			}
		} else {
			golem.row++;
			golem.col++;
			if (golem.exit == 0) { //북
				golem.exit = 1;
			} else if (golem.exit == 1) { //동
				golem.exit = 2;
			} else if (golem.exit == 2) { //남
				golem.exit = 3;
			} else if (golem.exit == 3) { //서
				golem.exit = 0;
			}
		}

		for(int[] delta : deltas) {
			int nextRow = golem.row + delta[0];
			int nextCol = golem.col + delta[1];

			if (map[nextRow][nextCol] == 0)
				map[nextRow][nextCol] = golem.num;
		}
	}

	public void markExit(Golem golem) {
		int nextRow = golem.row + deltas[golem.exit][0];
		int nextCol = golem.col + deltas[golem.exit][1];
		isExit[nextRow][nextCol] = true;
	}

	public int calculateScore(int row, int col) {
		int score = 0;

		Deque<int[]> toVisit = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];

		toVisit.add(new int[] {row, col});
		visited[row][col] = true;

		while(!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			score = Math.max(score, now[0] - 2);
			for (int i = 0; i < 4; i++) {
				int nextRow = now[0] + deltas[i][0];
				int nextCol = now[1] + deltas[i][1];

				if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) continue;
				if (visited[nextRow][nextCol]) continue;
				if ((map[now[0]][now[1]] != map[nextRow][nextCol]) && (!isExit[now[0]][now[1]])) continue;
				if (map[nextRow][nextCol] == 0) continue;
				
				toVisit.add(new int[] {nextRow, nextCol});
				visited[nextRow][nextCol] = true;
				
			}
		}
		return score;
	}

	public void makeClear() {
		map = new int[R][C];
		isExit = new boolean[R][C];
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = -1;
			}
		}
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		R = Integer.parseInt(tokenizer.nextToken()) + 3;
		C = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new int[R][C];
		isExit = new boolean[R][C];

		golems = new Golem[K];
		for (int i = 0; i < K; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			golems[i] = new Golem(i + 1, 1, Integer.parseInt(tokenizer.nextToken()) - 1, Integer.parseInt(tokenizer.nextToken()));
		}

		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = -1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new MagicalForestExploration_3().solution();
	}
}

