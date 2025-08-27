package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranunclulus
 @since 2025.08.27
 @link https://www.acmicpc.net/problem/4574
 @timecomplex
 @performance 19216KB 216MS
 @category
 @note
 */
public class one4574 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int[][] map;
	static int[][] deltas = new int[][] {{1, 0}, {0, 1}};
	static int dominoCount, testNumber;
	static boolean[][] rowCheck, colCheck, squareCheck, dominoUsed;
	static boolean find;
	static Domino domino = new Domino();

	static class Domino {
		int a;
		int aRow;
		int aCol;
		int b;
		int bRow;
		int bCol;
	}

	public void match(int num, String position, int type) {
		int row = -1;
		int col = Integer.parseInt(position.substring(1, 2)) - 1;

		if (position.charAt(0) == 'A') {
			row = 0;
		} else if (position.charAt(0) == 'B') {
			row = 1;
		} else if (position.charAt(0) == 'C') {
			row = 2;
		} else if (position.charAt(0) == 'D') {
			row = 3;
		} else if (position.charAt(0) == 'E') {
			row = 4;
		} else if (position.charAt(0) == 'F') {
			row = 5;
		} else if (position.charAt(0) == 'G') {
			row = 6;
		} else if (position.charAt(0) == 'H') {
			row = 7;
		} else if (position.charAt(0) == 'I') {
			row = 8;
		}

		if (type == 0) {
			domino.aRow = row;
			domino.aCol = col;
		} else if (type == 1) {
			domino.bRow = row;
			domino.bCol = col;
		}

		map[row][col] = num;

		checking(row, col, num);
	}

	public void solve() {

		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				int num = map[i][j];
				if (num == 0) continue;
				checking(i, j, num);
			}
		}

		find = false;
		dfs(0);
	}

	public void dfs(int depth) {
		if (find) return;
		if (depth == 81) {
			find = true;
			builder.append("Puzzle ").append(testNumber).append('\n');
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					builder.append(map[i][j]);
				}
				builder.append('\n');
			}
			return;
		}

		int row = depth / 9;
		int col = depth % 9;

		if (map[row][col] != 0) {
			dfs(depth + 1);
			return;
		}


		for(int[] delta : deltas) {
			int nextRow = row + delta[0];
			int nextCol = col + delta[1];

			if (nextRow >= 9 || nextCol >= 9) continue;
			if (map[nextRow][nextCol] != 0) continue;

			for(int a = 0; a < 9; a++) {
				for(int b = 0; b < 9; b++) {
					if (a == b) continue;
					if (dominoUsed[a][b] || dominoUsed[b][a]) continue;
					if (!canPlace(row, col, a)) continue;
					if (!canPlace(nextRow, nextCol, b)) continue;

					map[row][col] = a + 1;
					map[nextRow][nextCol] = b + 1;
					dominoUsed[a][b] = dominoUsed[b][a] = true;
					setCheck(row, col, a, true);
					setCheck(nextRow, nextCol, b, true);
					dfs(depth + 1);
					map[row][col] = 0;
					map[nextRow][nextCol] = 0;
					dominoUsed[a][b] = dominoUsed[b][a] = false;
					setCheck(row, col, a, false);
					setCheck(nextRow, nextCol, b, false);
				}
			}
		}
	}

	public void setCheck(int row, int col, int num, boolean check) {
		rowCheck[row][num] = check;
		colCheck[col][num] = check;
		squareCheck[(row / 3) + 3 * (col / 3)][num] = check;
	}

	public boolean canPlace(int row, int col, int num) {
		return (!rowCheck[row][num] && !colCheck[col][num] && !squareCheck[(row / 3) + 3 * (col / 3)][num]);
	}

	public void checking(int row, int col, int num) {
		num = num - 1;
		rowCheck[row][num] = true;
		colCheck[col][num] = true;
		squareCheck[(row / 3) + 3 * (col / 3)][num] = true;
	}

	public void solution() throws IOException {
		testNumber = 0;
		while(true) {
			dominoCount = Integer.parseInt(reader.readLine());
			if (dominoCount == 0) break;

			testNumber++;
			map = new int[9][9];
			rowCheck = new boolean[9][9];
			colCheck = new boolean[9][9];
			squareCheck = new boolean[9][9];
			dominoUsed = new boolean[9][9];

			for(int i = 0; i < dominoCount; i++) {
				tokenizer = new StringTokenizer(reader.readLine());

				for(int j = 0; j < 2; j++) {
					int num = Integer.parseInt(tokenizer.nextToken());

					if (j == 0) domino.a = num;
					else if (j == 1) domino.b = num;
					match(num, tokenizer.nextToken(), j);
				}

				dominoUsed[domino.a - 1][domino.b  - 1] = true;
				dominoUsed[domino.b  - 1][domino.a - 1] = true;
			}

			tokenizer = new StringTokenizer(reader.readLine());
			for(int num = 1; num <= 9; num++) {
				match(num, tokenizer.nextToken(), -1);
			}

			solve();
		}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		new one4574().solution();
	}
}

