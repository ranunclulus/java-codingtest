package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.08.21
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/mint-choco-milk
 @timecomplex
 @performance
 @category
 @note
 */
public class MintChocoMilk {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, T;
	static int[][] F;
	static int[][] B;
	static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	class Point implements Comparable<Point> {
		int row, col;
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Point o) {
			int foodCountA = getFoodCount(F[this.row][this.col]);
			int foodCountB = getFoodCount(F[o.row][o.col]);
			if (foodCountA != foodCountB)
				return Integer.compare(foodCountA, foodCountB);
			int beliefA = B[this.row][this.col];
			int beliefB = B[o.row][o.col];
			if (beliefA != beliefB)
				return -Integer.compare(beliefA, beliefB);
			if (this.row != o.row)
				return Integer.compare(this.row, o.row);
			return Integer.compare(this.col, o.col);
		}

	}

	static boolean inRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;

	}

	public Point getHeadAndAdd(boolean[][] visited, int row, int col) {
		Deque<Point> toVisit = new ArrayDeque<>();
		toVisit.add(new Point(row, col));
		visited[row][col] = true;
		int count = 1;
		int hRow = row, hCol = col;
		while (!toVisit.isEmpty()) {
			Point current = toVisit.poll();
			for (int[] delta : deltas) {
				int nextRow = current.row + delta[0];
				int nextCol = current.col + delta[1];
				if (!inRange(nextRow, nextCol))
					continue;

				if (visited[nextRow][nextCol])
					continue;

				if (F[current.row][current.col] != F[nextRow][nextCol])
					continue;

				toVisit.add(new Point(nextRow, nextCol));
				visited[nextRow][nextCol] = true;
				count++;

				if (B[nextRow][nextCol] > B[hRow][hCol]) {
					hRow = nextRow;
					hCol = nextCol;
				} else if (B[nextRow][nextCol] == B[hRow][hCol]) {
					if (nextRow < hRow) {
						hRow = nextRow;
						hCol = nextCol;
					} else if (nextRow == hRow && nextCol < hCol) {
						hRow = nextRow;
						hCol = nextCol;
					}
				}
			}
		}

		B[hRow][hCol] += count;
		return new Point(hRow, hCol);
	}

	public int getFoodCount(int food) {
		if (food == 1 || food == 2 || food == 4)
			return 1;
		if (food == 3 || food == 5 || food == 6)
			return 2;
		return 3;
	}

	public List<Point> step2() {
		boolean[][] visited = new boolean[N][N];
		List<Point> heads = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					heads.add(getHeadAndAdd(visited, i, j));
				}
			}
		}
		return heads;
	}

	public void step3(List<Point> heads) {
		Collections.sort(heads);
		boolean[][] isDefend = new boolean[N][N];
		for (Point head : heads) {
			int row = head.row;
			int col = head.col;
			if (isDefend[row][col])
				continue;
			int X = B[row][col] - 1;
			int dirIdx = B[row][col] % 4;
			B[row][col] = 1;
			int food = F[row][col];
			int currentRow = row;
			int currentCol = col;
			while (true) {
				currentRow += deltas[dirIdx][0];
				currentCol += deltas[dirIdx][1];
				if (!inRange(currentRow, currentCol) || X <= 0)
					break;
				if (food == F[currentRow][currentCol])
					continue;
				isDefend[currentRow][currentCol] = true;
				if (X > B[currentRow][currentCol]) {
					F[currentRow][currentCol] = food;
					X -= (B[currentRow][currentCol] + 1);
					B[currentRow][currentCol] += 1;
				} else {
					F[currentRow][currentCol] |= food;
					B[currentRow][currentCol] += X;
					break;
				}
			}
		}
	}

	public void printAll() {
		long[] S = new long[8];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				S[F[i][j]] += B[i][j];
			}
		}
		builder.append(S[7]).append(' ')
			.append(S[6]).append(' ')
			.append(S[5]).append(' ')
			.append(S[3]).append(' ')
			.append(S[1]).append(' ')
			.append(S[2]).append(' ')
			.append(S[4]).append('\n');
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		T = Integer.parseInt(tokenizer.nextToken());
		F = new int[N][N];
		B = new int[N][N];
		for (int i = 0; i < N; i++) {
			String input = reader.readLine();
			for (int j = 0; j < N; j++) {
				char ch = input.charAt(j);
				if (ch == 'T')
					F[i][j] = 4;
				else if (ch == 'C')
					F[i][j] = 2;
				else
					F[i][j] = 1;
			}
		}
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < N; j++) {
				B[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		for (int i = 0; i < T; i++) {
			List<Point> heads = step2();
			step3(heads);
			printAll();
		}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void solution() throws IOException {
		init();
	}

	public static void main(String[] args) throws IOException {
		new MintChocoMilk().solution();
	}
}

