package com.example.javacodingtest.codetree;


import java.io.*;
import java.util.*;

public class TreeTycoon {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int[][] deltas = new int[][] {{0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};
	static int N, M;
	static int[][] map;
	static int[][] moveRules;
	static List<int[]> nutrients;
	static boolean[][] isNutriented;

	public void solution() throws IOException {
		init();

		for(int i = 0; i < M; i++) {
			moveNutrients(i);
			growTree();
			cutTree();
		}

		builder.append(getScore());
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}


	public void moveNutrients(int turn) {
		int d = moveRules[turn][0];
		int p = moveRules[turn][1];

		List<int[]> afterMoving = new ArrayList<int[]>();
		for(int i = 0; i < nutrients.size(); i++) {
			int row = nutrients.get(i)[0];
			int col = nutrients.get(i)[1];

			int nextRow = (row + deltas[d][0] * p + 10 * N) % N;
			int nextCol = (col + deltas[d][1] * p + 10 * N) % N;

			afterMoving.add(new int[] {nextRow, nextCol});
		}

		nutrients = afterMoving;
		for(int i = 0; i < N; i++) {
			Arrays.fill(isNutriented[i], false);
		}

		for(int i = 0; i < nutrients.size(); i++) {
			int row = nutrients.get(i)[0];
			int col = nutrients.get(i)[1];
			isNutriented[row][col] = true;
		}

	}

	public void growTree() {
		for(int i = 0; i < nutrients.size(); i++) {
			int row = nutrients.get(i)[0];
			int col = nutrients.get(i)[1];
			map[row][col]++;
		}

		for(int i = 0; i < nutrients.size(); i++) {
			int row = nutrients.get(i)[0];
			int col = nutrients.get(i)[1];

			int count = 0;
			for(int j = 1; j < 8; j += 2) {
				int nextRow = row + deltas[j][0];
				int nextCol = col + deltas[j][1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (map[nextRow][nextCol] < 1) continue;

				count++;
			}

			map[row][col] += count;

		}
		nutrients.clear();
	}

	public void cutTree() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] < 2) continue;
				if (isNutriented[i][j]) continue;

				map[i][j] -= 2;
				nutrients.add(new int[] {i, j});
			}
		}

		for(int i = 0; i < N; i++) {
			Arrays.fill(isNutriented[i], false);
		}

		for(int i = 0; i < nutrients.size(); i++) {
			int row = nutrients.get(i)[0];
			int col = nutrients.get(i)[1];
			isNutriented[row][col] = true;
		}
	}

	public int getScore() {
		int score = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				score += map[i][j];
			}
		}


		return score;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		moveRules = new int[M][2];
		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int d = Integer.parseInt(tokenizer.nextToken()) - 1;
			int p = Integer.parseInt(tokenizer.nextToken());
			moveRules[i] = new int[] {d, p};
		}

		nutrients = new ArrayList<int[]>();
		nutrients.add(new int[] {N - 1, 0});
		nutrients.add(new int[] {N - 1, 1});
		nutrients.add(new int[] {N - 2, 0});
		nutrients.add(new int[] {N - 2, 1});

		isNutriented = new boolean[N][N];
		isNutriented[N - 1][0] = true;
		isNutriented[N - 1][1] = true;
		isNutriented[N - 2][0] = true;
		isNutriented[N - 2][1] = true;
	}

	public static void main(String[] args) throws IOException {
		new TreeTycoon().solution();
	}
}
