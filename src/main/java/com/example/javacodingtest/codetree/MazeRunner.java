package com.example.javacodingtest.codetree;


import java.util.*;
import java.io.*;

/*
 @author ranunclulus
 @since 2025.04.09
 @link https://www.codetree.ai/ko/frequent-problems/problems/maze-runner/description
 @timecomplex
 @performance
 @category
 @note
 */
public class MazeRunner {
	class Runner {
		int row;
		int col;
		int distance;
		boolean escaped;

		Runner(int row, int col) {
			this.row = row;
			this.col = col;
			this.distance = 0;
			this.escaped = false;
		}

	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, M, K, answer;
	static int startRow, startCol, length;
	static int[][] map;
	static Runner[] runners;
	static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[] exit;

	public void solution() throws IOException {
		init();
		while (K --> 0) {
			for(Runner runner : runners) {
				moveRunner(runner);
			}

			boolean allEscaped = true;
			for(Runner runner : runners) {
				if (!runner.escaped) {
					allEscaped = false;
					break;
				}
			}

			if (allEscaped) break;

			findMinimumSquare();
			spinSquare();
			spinRunnerAndExit();
		}

		answer = 0;
		for(Runner runner : runners) {
			answer += runner.distance;
		}
		builder.append(answer).append('\n').append(exit[0] + 1).append(' ').append(exit[1] + 1);
		writer.write(builder.toString());
		writer.flush();
	}

	private void moveRunner(Runner runner) {
		if (runner.escaped) return;

		for(int[] delta : deltas) {
			int nextRow = runner.row + delta[0];
			int nextCol = runner.col + delta[1];

			if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
			if (map[nextRow][nextCol] !=- 0) continue;

			int originalDistance = Math.abs(exit[0] - runner.row) + Math.abs(exit[1] - runner.col);
			int newDistance = Math.abs(exit[0] - nextRow) + Math.abs(exit[1] - nextCol);

			if (newDistance >= originalDistance) continue;

			runner.row = nextRow;
			runner.col = nextCol;
			runner.distance++;

			if (runner.row == exit[0] && runner.col == exit[1]) {
				runner.escaped = true;
			}
			break;
		}
	}

	public void findMinimumSquare() {
		for (int size = 2; size <= N; size++) {
			for (int row = 0; row <= N - size + 1; row++) {
				for (int col = 0; col <= N - size + 1; col++) {
					int endRow = row + size - 1;
					int endCol = col + size - 1;

					if (exit[0] < row || exit[0] > endRow || exit[1] < col || exit[1] > endCol) continue;

					boolean runnerIn = false;
					for(Runner runner : runners) {
						if (runner.escaped) continue;

						if (runner.row >= row && runner.row <= endRow && runner.col >= col && runner.col <= endCol) {
							runnerIn = true;
						}
					}

					if (runnerIn) {
						startRow = row;
						startCol = col;
						length = size;
						return;
					}
				}
			}
		}
	}

	public void spinSquare() {
		int[][] temp = new int[N][N];

		for (int i = startRow; i < startRow + length; i++) {
			for (int j = startCol; j < startCol + length; j++) {
				if (map[i][j] > 0) map[i][j]--;
			}
		}

		for (int i = startRow; i < startRow + length; i++) {
			for (int j = startCol; j < startCol + length; j++) {
				int dRow = i - startRow;
				int dCol = j - startCol;

				int addRow = dCol;
				int addCol = length - dRow - 1;

				temp[startRow + addRow][startCol + addCol] = map[i][j];
			}
		}

		for (int i = startRow; i < startRow + length; i++) {
			for (int j = startCol; j < startCol + length; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}

	public void spinRunnerAndExit() {
		for(Runner runner : runners) {
			if (runner.escaped) continue;

			if (runner.row >= startRow && runner.row < startRow + length && runner.col >= startCol && runner.col < startCol + length) {
				int dRow = runner.row - startRow;
				int dCol = runner.col - startCol;

				int addRow = dCol;
				int addCol = length - dRow - 1;

				runner.row = startRow + addRow;
				runner.col = startCol + addCol;
			}
		}

		if (exit[0] >= startRow && exit[0] < startRow + length && exit[1] >= startCol && exit[1] < startCol + length) {
			int dRow = exit[0] - startRow;
			int dCol = exit[1] - startCol;

			int addRow = dCol;
			int addCol = length - dRow - 1;

			exit[0] = startRow + addRow;
			exit[1] = startCol + addCol;
		}
	}

	public void init() throws IOException {
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

		runners = new Runner[M];
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			runners[i] = new Runner(Integer.parseInt(tokenizer.nextToken()) - 1, Integer.parseInt(tokenizer.nextToken()) - 1);
		}

		exit = new int[2];
		tokenizer = new StringTokenizer(reader.readLine());
		exit[0] = Integer.parseInt(tokenizer.nextToken()) - 1;
		exit[1] = Integer.parseInt(tokenizer.nextToken()) - 1;
	}

	public static void main(String[] args) throws IOException {
		new MazeRunner().solution();
	}
}
