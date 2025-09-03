package com.example.javacodingtest.boj.gold;

import java.util.*;
import java.io.*;

/*
 @author ranunclulus
 @since 2025.09.03
 @link https://www.acmicpc.net/problem/21611
 @timecomplex
 @performance
 @category
 @note
 */
public class one21611 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int[][] deltas = new int[][] {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M;
	static int[][] map;
	static int[][] blizards;
	static Point[] points;
	static int[] count;

	class Point {
		int row;
		int col;
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public void solution() throws IOException {
		init();

		for(int[] blizard : blizards) {
			destroyMarbles(blizard);
			moveMarbles();
			bombChain();
			transMarbles();
		}

		builder.append(1 * count[1] + 2 * count[2] + 3 * count[3]);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void destroyMarbles(int[] attack) {
		int row = (N + 1) / 2;
		int col = (N + 1) / 2;
		for(int i = 0; i < attack[1]; i++) {
			row += deltas[attack[0]][0];
			col += deltas[attack[0]][1];
			map[row][col] = 0;
		}
	}

	public void moveMarbles() {
		while (true) {
			int moveCount = 0;

			for(int i = 2; i < N * N; i++) {
				Point now = points[i];
				Point prev = points[i - 1];

				if (map[prev.row][prev.col] != 0) continue; // 이전 칸이 비어 있지 않으면 패스
				if (map[now.row][now.col] == 0) continue;

				map[prev.row][prev.col] = map[now.row][now.col];
				map[now.row][now.col] = 0;
				moveCount++;
			}

			if (moveCount == 0) break;
		}
	}

	public void bombChain() {
		while(true) {
			int bombCount = 0;

			for(int i = 1; i < N * N - 4; i++) {
				int nowValue = map[points[i].row][points[i].col];
				if (nowValue == 0) continue;
				int endIndex = i;
				int sameCount = 1;
				for(int j = i + 1; j < N * N; j++) {
					if (map[points[j].row][points[j].col] != nowValue) break;
					endIndex = j;
					sameCount++;
				}

				if (sameCount < 4) continue;

				for(int j = i; j <= endIndex; j++) {
					map[points[j].row][points[j].col] = 0;
				}
				count[nowValue] += sameCount;
				bombCount++;
			}
			moveMarbles();

			if (bombCount == 0) break;
		}
	}

	public void transMarbles() {
		List<Integer> marbleNumbers = new ArrayList<>();

		int index = 1;
		boolean finish = false;
		while (index < N * N) {
			int value = map[points[index].row][points[index].col];
			if (value == 0) break;
			int count = 1;

			for(int i = index + 1; i < N * N; i++) {
				int nextValue = map[points[i].row][points[i].col];
				if (value != nextValue) {
					index = i;
					marbleNumbers.add(count);
					marbleNumbers.add(value);
					break;
				} else if (nextValue == 0) {
					finish = true;
					break;
				} else {
					count++;
				}
			}

			if(finish) break;
		}

		for (int i = 1; i < N * N && i - 1 < marbleNumbers.size(); i++) {
			map[points[i].row][points[i].col] = marbleNumbers.get(i - 1);
		}
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new int[N + 1][N + 1];
		blizards = new int[M][2];
		count = new int[4];

		for(int i = 1; i <= N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			blizards[i][0] = Integer.parseInt(tokenizer.nextToken());
			blizards[i][1] = Integer.parseInt(tokenizer.nextToken());
		}

		points = new Point[N * N];
		int row = (N + 1) / 2;
		int col = (N + 1) / 2;
		points[0] = new Point(row, col);
		int limit = 1;
		int seq = 1;
		while (true) {
			for (int i = 0; i < limit; i++) {
				col--;
				points[seq] = new Point(row, col);
				seq++;
				if (seq == N * N) break;
			}
			if (seq == N * N) break;
			for(int i = 0; i < limit; i++) {
				row++;
				points[seq] = new Point(row, col);
				seq++;
			}
			if (seq == N * N) break;
			limit++;
			for (int i = 0; i < limit; i++) {
				col++;
				points[seq] = new Point(row, col);
				seq++;
			}
			if (seq == N * N) break;
			for(int i = 0; i < limit; i++) {
				row--;
				points[seq] = new Point(row, col);
				seq++;
			}
			if (seq == N * N) break;
			limit++;
		}

	}

	public static void main(String[] args) throws IOException {
		new one21611().solution();
	}
}