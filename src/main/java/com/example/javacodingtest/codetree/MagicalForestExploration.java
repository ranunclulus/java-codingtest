package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.06
 @link https://www.codetree.ai/ko/frequent-problems/problems/magical-forest-exploration/description
 @timecomplex
 @performance
 @category
 @note
 */
public class MagicalForestExploration {

	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	StringTokenizer tokenizer;
	StringBuilder builder = new StringBuilder();

	class Golem {
		int id;
		int col;
		int row;
		int exitDirection;

		Golem(int id, int col, int exitDirection) {
			this.id = id;
			this.col = col;
			this.row = 0;
			this.exitDirection = exitDirection;
		}
	}

	static int R, C, K, answer;
	static int[][] map;
	static boolean[][] isExit;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서
	static Golem[] golems;

	public void solution() throws IOException {
		init();
		for(Golem golem : golems) {
			down(golem);
		}
		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
	}

	private void down(Golem golem) {
		if (canMove(golem.row + 1, golem.col)) { // 아래로 내려갈 수 있는 경우
			golem.row++;
			down(golem);
		} else if (canMove(golem.row + 1, golem.col - 1)) { // 왼쪽 아래로 갈 수 있는 경우
			golem.row++;
			golem.col--;
			golem.exitDirection = (golem.exitDirection + 3) % 4;
			down(golem);
		} else if (canMove(golem.row + 1, golem.col + 1)) { // 오른쪽 아래로 갈 수 있는 경우
			golem.row++;
			golem.col++;
			golem.exitDirection = (golem.exitDirection + 1) % 4;
			down(golem);
		} else { // 이제 더는 이동할 수 없음
			if(!inRange(golem.row - 1, golem.col - 1) || !inRange(golem.row + 1, golem.col + 1)) { // 골렘이 밖에 있는 경우 맵 초기화
				initMap();
			} else {
				map[golem.row][golem.col] = golem.id;
				for(int[] delta : deltas) {
					map[golem.row + delta[0]][golem.col + delta[1]] = golem.id;
				}
				isExit[golem.row + deltas[golem.exitDirection][0]][golem.col + deltas[golem.exitDirection][1]] = true;
				answer += (getMaxRow(golem) - 2);
			}
		}
	}

	private boolean inRange(int row, int col) {
		return (3 <= row && row < R + 3 && 0 <= col && col < C);
	}

	private int getMaxRow(Golem golem) {
		int maxRow = golem.row;

		Deque<int[]> toVisit = new ArrayDeque<>();
		boolean[][] visited = new boolean[R + 3][C];

		toVisit.add(new int[] {golem.row, golem.col});
		visited[golem.row][golem.col] = true;

		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (!inRange(nextRow, nextCol)) continue;
				if (visited[nextRow][nextCol]) continue;
				if (map[nextRow][nextCol] == map[now[0]][now[1]] || (map[nextRow][nextCol] != 0 && isExit[now[0]][now[1]])) {
					toVisit.add(new int[] {nextRow, nextCol});
					visited[nextRow][nextCol] = true;
					maxRow = Math.max(maxRow, nextRow);
				}
			}
		}
		return maxRow;
	}

	private void initMap() {
		for (int i = 0; i < R + 3; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = 0;
				isExit[i][j] = false;
			}
		}
	}

	private boolean canMove(int row, int col) {
		if ((row - 1) < 0 || (row + 1) >= R + 3 || (col - 1) < 0 || (col + 1) >= C) return false;
		if (map[row - 1][col - 1] != 0) return false;
		if (map[row - 1][col] != 0) return false;
		if (map[row - 1][col + 1] != 0) return false;
		if (map[row][col - 1] != 0) return false;
		if (map[row][col] != 0) return false;
		if (map[row][col + 1] != 0) return false;
		if (map[row + 1][col] != 0) return false;
		return true;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		R = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		map = new int[R + 3][C];
		isExit = new boolean[R + 3][C];

		golems = new Golem[K];
		for (int i = 0; i < K; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			golems[i] = new Golem(i + 1, Integer.parseInt(tokenizer.nextToken()) - 1, Integer.parseInt(tokenizer.nextToken()));
		}
	}

	public static void main(String[] args) throws IOException {
		new MagicalForestExploration().solution();
	}
}

