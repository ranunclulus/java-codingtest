package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranunclulus
 @since 2025.09.02
 @link https://www.acmicpc.net/problem/15683
 @timecomplex
 @performance
 @category
 @note
 */
public class three15683_2{
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, M;
	static int[][] map;
	static boolean[][] isWatched;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static List<int[]>[] directions;
	static List<CCTV> cctvList;
	static int[] cctvDirections;
	static int answer = Integer.MAX_VALUE;

	class CCTV {

		int row;
		int col;
		int num;

		CCTV(int row, int col, int num) {
			this.row = row;
			this.col = col;
			this.num = num;
		}
	}

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][M];
		cctvList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());

				if (map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new CCTV(i, j, map[i][j]));
				}
			}
		}

		cctvDirections = new int[cctvList.size()];
		initDirection();
		getCCTVDirections(0);

		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void getCCTVDirections(int depth) {
		if (depth == cctvList.size()) {
			getMinSpace();
			return;
		}

		int now = cctvList.get(depth).num - 1;
		for(int i = 0; i < directions[now].size(); i++) {
			cctvDirections[depth] = i;
			getCCTVDirections(depth + 1);
		}

	}

	public void getMinSpace() {
		isWatched = new boolean[N][M];

		for(int i = 0; i < cctvList.size(); i++) {
			CCTV cctv = cctvList.get(i);
			int[] cctvDirectionList = directions[cctv.num - 1].get(cctvDirections[i]);

			if (i == 1) {
				int asd = 1;
			}
			isWatched[cctv.row][cctv.col] = true;
			for(int dir : cctvDirectionList) {
				int nextRow = cctv.row + deltas[dir][0];
				int nextCol = cctv.col + deltas[dir][1];

				while (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
					isWatched[nextRow][nextCol] = true;
					if (map[nextRow][nextCol] == 6) break;
					nextRow += deltas[dir][0];
					nextCol += deltas[dir][1];
				}
			}
		}


		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if (!isWatched[i][j] && map[i][j] != 6) count++;
			}
		}

		answer = Math.min(answer, count);
	}

	public void initDirection() {
		directions = new List[5];
		directions[0] = new ArrayList<>();
		directions[0].add(new int[] {0});
		directions[0].add(new int[] {1});
		directions[0].add(new int[] {2});
		directions[0].add(new int[] {3});

		directions[1] = new ArrayList<>();
		directions[1].add(new int[] {0, 2});
		directions[1].add(new int[] {1, 3});

		directions[2] = new ArrayList<>();
		directions[2].add(new int[] {0, 1});
		directions[2].add(new int[] {1, 2});
		directions[2].add(new int[] {2, 3});
		directions[2].add(new int[] {3, 0});

		directions[3] = new ArrayList<>();
		directions[3].add(new int[] {0, 1, 2});
		directions[3].add(new int[] {1, 2, 3});
		directions[3].add(new int[] {2, 3, 0});
		directions[3].add(new int[] {3, 0, 1});

		directions[4] = new ArrayList<>();
		directions[4].add(new int[] {0, 1, 2, 3});
	}

	public static void main(String[] args) throws IOException {
		new three15683_2().solution();
	}
}
