package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

/*
 @author ranunclulus
 @since 2025.08.26
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/frog-journey
 @timecomplex
 @performance
 @category
 @note
 */
public class MagicalForestExploration_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int R, C, K, answer;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서
	static int[][] map;
	static boolean[][] isExit;
	static Golem[] golems;

	class Golem {
		int id;
		int centerRow;
		int centerCol;
		int direction;

		Golem(int id, int col, int direction) {
			this.id = id;
			this.centerRow = 0;
			this.centerCol = col;
			this.direction = direction;
		}
	}

	public void tour(Golem golem) {
		while (true) {
			if (canMove(golem.centerRow + 1, golem.centerCol)) { // 남쪽으로 이동 가능
				golem.centerRow++;
			} else if (canMove(golem.centerRow + 1, golem.centerCol - 1)) { // 서쪽으로 이동 가능
				golem.centerRow++;
				golem.centerCol--;
				golem.direction = (golem.direction + 3) % 4;
			} else if (canMove(golem.centerRow + 1, golem.centerCol + 1)) { // 동쪽으로 이동 가능
				golem.centerRow++;
				golem.centerCol++;
				golem.direction = (golem.direction + 1) % 4;
			} else {
				if (!inRange(golem.centerRow + 1, golem.centerCol + 1) || !inRange(golem.centerRow - 1, golem.centerCol - 1)){
					initMap();
					break;
				}
				map[golem.centerRow][golem.centerCol] = golem.id;
				for(int[] delta : deltas) {
					map[golem.centerRow + delta[0]][golem.centerCol + delta[1]] = golem.id;
				}
				isExit[golem.centerRow + deltas[golem.direction][0]][golem.centerCol + deltas[golem.direction][1]] = true;
				answer += getMaxRow(golem);
				break;
			}
		}
	}


	public int getMaxRow(Golem golem) {
		Deque<int[]> toVisit = new ArrayDeque<>();
		boolean[][] visited = new boolean[R + 3][C];
		toVisit.add(new int[] {golem.centerRow, golem.centerCol});
		visited[golem.centerRow][golem.centerCol] = true;
		int maxRow = golem.centerRow;

		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();

			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (!inRange(nextRow, nextCol)) continue;
				if (visited[nextRow][nextCol]) continue;

				if ((map[now[0]][now[1]] == map[nextRow][nextCol]) ||
					((map[nextRow][nextCol] != 0) && isExit[now[0]][now[1]])) {
					toVisit.add(new int[] {nextRow, nextCol});
					visited[nextRow][nextCol] = true;
					maxRow = Math.max(maxRow, nextRow);
				}
			}
		}
		return maxRow - 2;
	}

	public void initMap() {
		for(int i = 0; i < R + 3; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] = 0;
				isExit[i][j] = false;
			}
		}
	}

	public boolean canMove(int row, int col) {
		if (row - 1 < 0 || row + 1 >= R + 3 || col - 1 < 0 || col + 1 >= C) return false;
		if (map[row - 1][col - 1] != 0) return false;
		if (map[row - 1][col] != 0) return false;
		if (map[row - 1][col + 1] != 0) return false;
		if (map[row][col - 1] != 0) return false;
		if (map[row][col] != 0) return false;
		if (map[row][col + 1] != 0) return false;
		if (map[row + 1][col] != 0) return false;
		return true;
	}

	public void mark(Golem golem) {
		map[golem.centerRow][golem.centerCol] = golem.id;
		for(int[] delta : deltas) {
			int nextRow = golem.centerRow + delta[0];
			int nextCol = golem.centerCol + delta[1];
			if (!inRange(nextRow, nextCol)) continue;
			map[nextRow][nextCol] = golem.id;
		}
	}

	public boolean inRange(int row, int col) {
		return (row >= 3 && row < R + 3 && col >= 0 && col < C);
	}


	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new int[R + 3][C];
		isExit = new boolean[R + 3][C];
		golems = new Golem[K];
		for(int i = 0; i < K; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int startCol = Integer.parseInt(tokenizer.nextToken()) - 1;
			int direction = Integer.parseInt(tokenizer.nextToken());
			golems[i] = new Golem(i + 1, startCol, direction);
		}

		for(Golem golem : golems) {
			tour(golem);
		}

		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		new MagicalForestExploration_2().solution();
	}

}
