package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 @author ranuinclulus
 @since 2026.01.17
 @link https://www.codetree.ai/ko/frequent-problems/problems/royal-knight-duel/submissions?page=1&page_size=20&introductionSetId=&bookmarkId=
 @timecomplex
 @performance 
 @category
 @note
 */
public class RoyalKnightDuel_2 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int L, N, Q;
	static int[][] map;
	static Knight[] knights;
	static int[][] queries;
	static int[][] knightMap;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean[] slippedList;


	class Knight {
		int row;
		int col;
		int height;
		int width;
		int k;
		int num;
		boolean isAlive;
		int demage;

		Knight(int row, int col, int height, int width, int k, int num) {
			this.row = row;
			this.col = col;
			this.height = height;
			this.width = width;
			this.k = k;
			this.num = num;
			this.isAlive = true;
			this.demage = 0;
		}

	}


	public void solution() throws IOException {
		init();
		for(int i = 0; i < Q; i++) {

			slippedList = new boolean[N];
			boolean canMove = isMoveKnight(queries[i][0], queries[i][1]);
			if (canMove) {
				moveKnight(queries[i][1]);
				attackKnight(queries[i][0]);
			}

		}
		builder.append(getAnswer());
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}


	public boolean isMoveKnight(int num, int direction) {
		Knight knight = knights[num - 1];
		if (!knight.isAlive) return false; //체스판에서 사라진 기사에게 명령을 내리면 아무런 반응이 없음


		for(int i = knight.row; i < knight.row + knight.height; i++) {
			for(int j = knight.col; j < knight.col + knight.width; j++) {
				int nextRow = i + deltas[direction][0];
				int nextCol = j + deltas[direction][1];

				if (nextRow < 0 || nextRow >= L || nextCol < 0 || nextCol >= L) {
					return false;
				}


				if (map[nextRow][nextCol] == 2) {
					return false;
				}

				if (knightMap[nextRow][nextCol] != 0 && knightMap[nextRow][nextCol] != num) {
					if (!isMoveKnight(knightMap[nextRow][nextCol], direction)) return false;
				}

			}
		}

		slippedList[knight.num - 1] = true;

		return true;
	}

	public void moveKnight(int direction) {

		// 1. 전부 제거
		for (int n = 0; n < N; n++) {
			if (!slippedList[n]) continue;
			Knight knight = knights[n];

			for (int i = knight.row; i < knight.row + knight.height; i++) {
				for (int j = knight.col; j < knight.col + knight.width; j++) {
					knightMap[i][j] = 0;
				}
			}
		}

		// 2. 좌표 이동
		for (int n = 0; n < N; n++) {
			if (!slippedList[n]) continue;
			Knight knight = knights[n];

			knight.row += deltas[direction][0];
			knight.col += deltas[direction][1];
		}

		// 3. 다시 그리기
		for (int n = 0; n < N; n++) {
			if (!slippedList[n]) continue;
			Knight knight = knights[n];

			for (int i = knight.row; i < knight.row + knight.height; i++) {
				for (int j = knight.col; j < knight.col + knight.width; j++) {
					knightMap[i][j] = knight.num;
				}
			}
		}
	}

	public void attackKnight(int num) {
		for(int i = 0; i < N; i++) {
			if (!slippedList[i]) continue;
			Knight knight = knights[i];
			if (knight.num == num) continue; // 명령을 받은 기사는 피해를 입지 않음

			int demageCount = 0;
			for(int j = knight.row; j < knight.row + knight.height; j++) {
				for(int k = knight.col; k < knight.col + knight.width; k++) {
					if (map[j][k] == 1) demageCount++;
				}
			}

			knight.k -= demageCount;        // 체력 감소
			knight.demage += demageCount;  // 데미지는 무조건 누적

			if (knight.k <= 0) {
				knight.isAlive = false;
				for(int j = knight.row; j < knight.row + knight.height; j++) {
					for(int k = knight.col; k < knight.col + knight.width; k++) {
						knightMap[j][k] = 0;
					}
				}
			}
		}
	}

	public int getAnswer() {
		int score = 0;

		for(int i = 0; i < N; i++) {
			Knight knight = knights[i];
			if (!knight.isAlive) continue;
			score += knight.demage;
		}

		return score;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		L = Integer.parseInt(tokenizer.nextToken());
		N = Integer.parseInt(tokenizer.nextToken());
		Q = Integer.parseInt(tokenizer.nextToken());

		map = new int[L][L];
		for(int i = 0; i < L; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < L; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		knights = new Knight[N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int row = Integer.parseInt(tokenizer.nextToken()) - 1;
			int col = Integer.parseInt(tokenizer.nextToken()) - 1;
			int height = Integer.parseInt(tokenizer.nextToken());
			int width = Integer.parseInt(tokenizer.nextToken());
			int k = Integer.parseInt(tokenizer.nextToken());
			knights[i] = new Knight(row, col, height, width, k, i + 1);
		}
		queries = new int[Q][2];
		for(int i = 0; i < Q; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			queries[i] = new int[] {Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())};
		}

		knightMap = new int[L][L];
		for(Knight knight : knights) {
			for(int i = knight.row; i < knight.row + knight.height; i++) {
				for(int j = knight.col; j < knight.col + knight.width; j++) {
					knightMap[i][j] = knight.num;
				}
			}
		}
	}


	public static void main(String[] args) throws IOException {
		new RoyalKnightDuel_2().solution();
	}

}
