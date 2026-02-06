package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MazeTowerDefense {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M;
	static int[][] map;
	static List<int[]> sequence;
	static int[][] deltas = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int[][] movements;
	static int score;

	public void solution() throws IOException {
		init();
		for(int i = 0; i < M; i++) {
			attack(i);
			while(removeMonsters()) {
				replaceMap();
			}
			arrangeMap();
		}
		builder.append(score);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void attack(int turn) {
		int d = movements[turn][0];
		int p = movements[turn][1];
		int row = N / 2;
		int col = N / 2;

		for(int i = 1; i <= p; i++) {
			int nextRow = row + deltas[d][0] * i;
			int nextCol = col + deltas[d][1] * i;

			if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;

			score += map[nextRow][nextCol];
			map[nextRow][nextCol] = 0;
		}

		replaceMap();
	}

	public void replaceMap() {
		List<Integer> numList = new ArrayList<Integer>();
		for(int i = 0; i < sequence.size(); i++) {
			int[] pos = sequence.get(i);

			if (map[pos[0]][pos[1]] == 0) continue;
			numList.add(map[pos[0]][pos[1]]);
		}

		for(int i = 0; i < N; i++) {
			Arrays.fill(map[i], 0);
		}

		int monsterCount = numList.size();
		int monsterIndex = 0;

		for(int i = 0; i < sequence.size(); i++) {
			int[] pos = sequence.get(i);

			map[pos[0]][pos[1]] = numList.get(monsterIndex);
			monsterIndex++;

			if (monsterIndex == monsterCount) break;
		}


	}

	public boolean removeMonsters() {
		boolean isRemoved = false;

		int num = 0;
		int count = 0;

		for(int i = 0; i < sequence.size(); i++) {
			int[] pos = sequence.get(i);

			int nowNum = map[pos[0]][pos[1]];
			if (num == nowNum) {
				count++;
			} else {
				if (count >= 4) {
					isRemoved = true;
					for(int j = 1; j <= count; j++) {
						int[] removePos = sequence.get(i - j);
						score += map[removePos[0]][removePos[1]];
						map[removePos[0]][removePos[1]] = 0;
					}
				}
				num = nowNum;
				count = 1;
			}

		}
		return isRemoved;
	}

	public void arrangeMap() {
		List<Integer> numList = new ArrayList<Integer>();
		int num = map[sequence.get(0)[0]][sequence.get(0)[1]];
		int count = 0;

		for(int i = 0; i < sequence.size(); i++) {
			int[] pos = sequence.get(i);

			int nowNum = map[pos[0]][pos[1]];
			if (num == nowNum) {
				count++;
			} else {
				numList.add(count);
				numList.add(num);
				num = nowNum;
				count = 1;
			}
			if (num == 0) break;

		}

		for(int i = 0; i < N; i++) {
			Arrays.fill(map[i], 0);
		}

		int monsterCount = numList.size();
		int monsterIndex = 0;

		for(int i = 0; i < sequence.size(); i++) {
			int[] pos = sequence.get(i);

			map[pos[0]][pos[1]] = numList.get(monsterIndex);
			monsterIndex++;

			if (monsterIndex == monsterCount) break;
		}

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

		movements = new int[M][2];
		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int d = Integer.parseInt(tokenizer.nextToken());
			int p = Integer.parseInt(tokenizer.nextToken());
			movements[i] = new int[] {d, p};
		}

		makeSequence(N / 2, N / 2);
	}

	public void makeSequence(int row, int col) {
		sequence = new ArrayList<>();
		int direction = 2;
		int gap = 1;
		int count = 1;

		while(true) {
			for(int i = 0; i < 2; i++) {
				direction = (direction - i + 4) % 4;
				for(int j = 0; j < gap; j++) {
					row += deltas[direction][0];
					col += deltas[direction][1];

					sequence.add(new int[] {row, col});
					count++;
					if (count == N * N) break;
				}
				if (count == N * N) break;
			}
			gap++;
			direction = (direction + 3) % 4;
			if (count == N * N) break;
		}

	}

	public static void main(String[] args) throws IOException {
		new MazeTowerDefense().solution();
	}

}