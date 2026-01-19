package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
 @author ranunclulus
 @since 2026.01.19
 @link https://www.codetree.ai/ko/frequent-problems/problems/maze-runner/description
 @timecomplex
 @performance
 @category
 @note
 */
public class MazeRunner_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, K;
	static int[][] map;
	static Runner[] runners;
	static int exitRow, exitCol;
	static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int totalDistance;

	class Runner {
		int row;
		int col;
		boolean isEscaped;

		Runner(int row, int col) {
			this.row = row;
			this.col = col;
			this.isEscaped = false;
		}

		@Override
		public String toString() {
			return "Runner [row=" + row + ", col=" + col + ", isEscaped=" + isEscaped + "]";
		}

	}

	class RunnerMoveNode implements Comparable<RunnerMoveNode>{
		int row;
		int col;
		int direction;
		int distance;
		int directionPriority;

		RunnerMoveNode(int row, int col, int direction, int distance) {
			this.row = row;
			this.col = col;
			this.direction= direction;
			this.distance = distance;


			if (direction == 0 || direction == 1) this.directionPriority = 1;
			else this.directionPriority = 2;
		}

		@Override
		public int compareTo(RunnerMoveNode o) {
			if (this.distance == o.distance) {
				return Integer.compare(this.directionPriority, o.directionPriority);
			}
			return Integer.compare(this.distance, o.distance);
		}
	}

	class SqureNode implements Comparable<SqureNode> {
		int startRow;
		int startCol;
		int endRow;
		int endCol;
		int size;

		SqureNode(int startRow, int startCol, int endRow, int endCol) {
			this.startRow = startRow;
			this.startCol = startCol;
			this.endRow = endRow;
			this.endCol = endCol;
			this.size = (endRow - startRow + 1) * (endCol - startCol + 1);
		}

		@Override
		public int compareTo(SqureNode o) {
			if (this.size == o.size) {
				if (this.startRow == o.startRow) {
					return Integer.compare(this.startCol, o.startCol);
				}
				return Integer.compare(this.startRow, o.startRow);
			}
			return Integer.compare(this.size, o.size);
		}

		@Override
		public String toString() {
			return "SqureNode [startRow=" + startRow + ", startCol=" + startCol + ", endRow=" + endRow + ", endCol="
				+ endCol + ", size=" + size + "]";
		}
	}

	public void solution() throws IOException {
		init();

		for(int time = 0; time < K; time++) {
			print();
			moveRunner();
			if (allEscaped()) break;
			System.out.println("=====AFTER MOVE=====");
			for(int i = 0; i < M; i++) {
				System.out.println(runners[i]);
			}
			SqureNode squre = makeSqure();
			System.out.println("=====SQURE====");
			System.out.println(squre);
			rotateSqure(squre);
			if (allEscaped()) break;
		}
		builder.append(totalDistance)
			.append('\n')
			.append(exitRow + 1)
			.append(' ')
			.append(exitCol + 1);

		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public boolean allEscaped() {
		for (int i = 0; i < M; i++) {
			if (!runners[i].isEscaped) return false;
		}
		return true;
	}

	public void moveRunner() {
		for(int i = 0; i < M; i++) {
			Runner runner = runners[i];
			if (runner.isEscaped) continue;

			PriorityQueue<RunnerMoveNode> candidate = new PriorityQueue<>();

			int distance = Math.abs(exitRow - runner.row) + Math.abs(exitCol - runner.col);
			for(int j = 0; j < 4; j++) {
				int nextRow = runner.row + deltas[j][0];
				int nextCol = runner.col + deltas[j][1];
				int newDistance = Math.abs(exitRow - nextRow) + Math.abs(exitCol - nextCol);

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (map[nextRow][nextCol] > 0) continue;
				if (newDistance >= distance) continue;

				candidate.add(new RunnerMoveNode(nextRow, nextCol, j, newDistance));
			}

			if (candidate.size() == 0) continue;

			RunnerMoveNode moveNode = candidate.poll();
			runner.row = moveNode.row;
			runner.col = moveNode.col;
			totalDistance++;

			if (runner.row == exitRow && runner.col == exitCol) runner.isEscaped = true;
		}
	}

	public SqureNode makeSqure() {
		PriorityQueue<SqureNode> pq = new PriorityQueue<>();


		for(int startRow = 0; startRow < N; startRow ++) {
			for(int startCol = 0; startCol < N; startCol++) {
				for(int endRow = startRow + 1; endRow < N; endRow++) {
					for(int endCol = startCol + 1; endCol < N; endCol++) {

						if (startRow <= exitRow && exitRow <= endRow && startCol <= exitCol && exitCol <= endCol) {
							int runnerCount = 0;
							for(int i = 0; i < M; i++) {
								Runner runner = runners[i];
								if (runner.isEscaped) continue;

								if (startRow <= runner.row && runner.row <= endRow && startCol <= runner.col && runner.col <= endCol) {
									runnerCount++;
								}
							}

							if (endRow - startRow != endCol - startCol) continue;
							if (runnerCount != 0) {
								pq.add(new SqureNode(startRow, startCol, endRow, endCol));
							}
						}
					}
				}
			}
		}

		return pq.poll();
	}

	public void rotateSqure(SqureNode squre) {
		for(int i = squre.startRow; i <= squre.endRow; i++) {
			for(int j = squre.startCol; j <= squre.endCol; j++) {
				if (map[i][j] > 0) map[i][j]--;
			}
		}

		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}

		for(int i = squre.startRow; i <= squre.endRow; i++) {
			for(int j = squre.startCol; j <= squre.endCol; j++) {
				int dRow = i - squre.startRow;
				int dCol = j - squre.startCol;

				int addRow = 0;
				int addCol = 0;

				temp[squre.startRow + dCol][squre.endCol - dRow] = map[i][j];

			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = temp[i][j];
				if (map[i][j] == -1) {
					exitRow = i;
					exitCol = j;
				}
			}
		}

		boolean[] isMoved = new boolean[M];
		for(int i = squre.startRow; i <= squre.endRow; i++) {
			for(int j = squre.startCol; j <= squre.endCol; j++) {

				for(int k = 0; k < M; k++) {
					if (isMoved[k]) continue;
					Runner runner = runners[k];
					if (runner.row == i && runner.col == j) {
						int dRow = i - squre.startRow;
						int dCol = j - squre.startCol;

						runner.row = squre.startRow + dCol;
						runner.col = squre.endCol - dRow;

						isMoved[k] = true;
					}
				}
			}
		}

	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		runners = new Runner[M];
		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			runners[i] = new Runner(Integer.parseInt(tokenizer.nextToken()) - 1, Integer.parseInt(tokenizer.nextToken()) - 1);
		}

		tokenizer = new StringTokenizer(reader.readLine());
		exitRow = Integer.parseInt(tokenizer.nextToken()) - 1;
		exitCol = Integer.parseInt(tokenizer.nextToken()) - 1;
		map[exitRow][exitCol] = -1;
	}

	public void print() {
		System.out.println("**********************************************************");
		System.out.println("=====RUNNER=====");
		for(int i = 0; i < M; i++) {
			System.out.println(runners[i]);
		}
		System.out.println("=====MAP=====");
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("=====TOTAL DISTANCE=====");
		System.out.println(totalDistance);


		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}


	public static void main(String[] args) throws IOException {
		new MazeRunner_2().solution();
	}

}