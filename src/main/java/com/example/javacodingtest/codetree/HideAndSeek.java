package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2026.02.01
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/hide-and-seek/description
 @timecomplex
 @performance
 @category
 @note
 */
public class HideAndSeek {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, H, K;
	static List<int[]> catcherMove;
	static int catcherIndex;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] map;
	static Runner[] runners;
	static int score;

	class Runner {
		int row;
		int col;
		int direction;
		boolean isAlive;

		Runner(int row, int col, int direction) {
			this.row = row;
			this.col = col;
			this.direction = direction;
			this.isAlive = true;

		}
	}

	public void solution() throws IOException {
		init();
		for(int i = 0; i < K; i++) {
			moveRunners();
			moveCatcher(i);
		}

		builder.append(score);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}


	public void moveRunners() {
		boolean[] canMove = new boolean[M];
		for(int i = 0; i < M; i++) {
			Runner runner = runners[i];
			if (!runner.isAlive) continue;
			int[] catcher = catcherMove.get(catcherIndex);
			int distance = Math.abs(catcher[0] - runner.row) + Math.abs(catcher[1] - runner.col);
			if (distance <= 3) canMove[i] = true;
		}

		for(int i = 0; i < M; i++) {
			if (!canMove[i]) continue;
			Runner runner = runners[i];
			if (!runner.isAlive) continue;
			int[] catcher = catcherMove.get(catcherIndex);

			int nextRow = runner.row + deltas[runner.direction][0];
			int nextCol = runner.col + deltas[runner.direction][1];

			if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
				runner.direction = (runner.direction + 2) % 4;
				nextRow = runner.row + deltas[runner.direction][0];
				nextCol = runner.col + deltas[runner.direction][1];
			}

			if (nextRow == catcher[0] && nextCol == catcher[1]) continue;

			runner.row = nextRow;
			runner.col = nextCol;
		}
	}

	public void moveCatcher(int turn) {
		catcherIndex = (catcherIndex + 1) % (N * N * 2 - 2);
		int[] now = catcherMove.get(catcherIndex);
		int[] next = catcherMove.get((catcherIndex + 1) % (N * N * 2 - 2));
		int direction = -1;

		for(int i = 0; i < 4; i++) {
			int nextRow = now[0] + deltas[i][0];
			int nextCol = now[1] + deltas[i][1];

			if (nextRow == next[0] && nextCol == next[1]) {
				direction = i;
				break;
			}
		}



		int catchNum = 0;
		int row = now[0];
		int col = now[1];
		for(int i = 0; i <= 2; i++) {

			if (map[row][col] == 1) {
				row += deltas[direction][0];
				col += deltas[direction][1];
				if (row < 0 || row >= N || col < 0 || col >= N) break;
				continue;
			}
			for(int j = 0; j < M; j++) {
				Runner runner = runners[j];
				if (!runner.isAlive) continue;
				if (row == runner.row && col == runner.col) {
					runner.isAlive = false;
					catchNum++;
				}
			}

			row += deltas[direction][0];
			col += deltas[direction][1];

			if (row < 0 || row >= N || col < 0 || col >= N) break;
		}

		score += ((turn + 1) * catchNum);

	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		H = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());


		map = new int[N][N];
		catcherMove = new ArrayList<int[]>();
		makeCatcherMove((N - 1) / 2, (N - 1) / 2);

		runners = new Runner[M];
		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int row = Integer.parseInt(tokenizer.nextToken()) - 1;
			int col = Integer.parseInt(tokenizer.nextToken()) - 1;
			int direction = Integer.parseInt(tokenizer.nextToken());

			runners[i] = new Runner(row, col, direction);

		}

		for(int i = 0; i < H; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int row = Integer.parseInt(tokenizer.nextToken()) - 1;
			int col = Integer.parseInt(tokenizer.nextToken()) - 1;

			map[row][col] = 1;

		}

	}

	public void makeCatcherMove(int row, int col) {
		catcherMove.add(new int[] {row, col});

		boolean finish = false;
		int gap = 1;
		int num = 0;
		while (!finish) {

			int move = 0;
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < gap; j++) {


					int nextRow = row + deltas[i][0];
					int nextCol = col + deltas[i][1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
						finish = true;
						break;
					}

					row = nextRow;
					col = nextCol;

					catcherMove.add(new int[] {row, col});

					if (num == (N * N)) {
						finish = true;
						break;
					}
				}
				move++;

				if (move == 2) {
					gap++;
					move = 0;
				}

				if (finish) break;
			}
		}
		int index = catcherMove.size() - 2;

		while (index != 0) {
			int[] move = new int[] {catcherMove.get(index)[0], catcherMove.get(index)[1]};
			catcherMove.add(move);
			index--;
		}

	}

	public static void main(String[] args) throws IOException {
		new HideAndSeek().solution();
	}
}