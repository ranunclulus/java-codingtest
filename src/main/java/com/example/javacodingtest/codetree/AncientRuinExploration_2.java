package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

public class AncientRuinExploration_2{
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int K, M, value, expectedValue;
	static int[][] map, turnedMap;
	static Deque<Integer> wallNumbers;


	public void solution() throws IOException {
		init();
		while(K --> 0) {
			value = 0;
			exploration();
			getRuins();
			if (value == 0) break;
			builder.append(value).append(' ');
		}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	// 탐사 진행
	public void exploration() {
		expectedValue = Integer.MIN_VALUE;
		turnedMap = new int[5][5];
		for(int spin = 0; spin < 3; spin++) {
			for(int col = 1; col < 4; col++) {
				for(int row = 1; row < 4; row++) {
					spinInThisPoint(row, col, spin);
				}
			}
		}
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				map[i][j] = turnedMap[i][j];
			}
		}
	}

	// 이 지점에서 스핀
	public void spinInThisPoint(int row, int col, int spin) {
		int[][] temp = new int[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				temp[i][j] = map[i][j];
			}
		}

		int[] numbers = new int[8];
		numbers[0] = map[row - 1][col - 1];
		numbers[1] = map[row - 1][col];
		numbers[2] = map[row - 1][col + 1];
		numbers[3] = map[row][col + 1];
		numbers[4] = map[row + 1][col + 1];
		numbers[5] = map[row + 1][col];
		numbers[6] = map[row + 1][col - 1];
		numbers[7] = map[row][col - 1];

		if (spin == 0) { //90도
			temp[row - 1][col + 1] = numbers[0];
			temp[row][col + 1] = numbers[1];
			temp[row + 1][col + 1] = numbers[2];
			temp[row + 1][col] = numbers[3];
			temp[row + 1][col - 1] = numbers[4];
			temp[row][col - 1] = numbers[5];
			temp[row - 1][col - 1] = numbers[6];
			temp[row - 1][col] = numbers[7];
		} else if (spin == 1) { //180도
			temp[row + 1][col + 1] = numbers[0];
			temp[row + 1][col] = numbers[1];
			temp[row + 1][col - 1] = numbers[2];
			temp[row][col - 1] = numbers[3];
			temp[row - 1][col - 1] = numbers[4];
			temp[row - 1][col] = numbers[5];
			temp[row - 1][col + 1] = numbers[6];
			temp[row][col + 1] = numbers[7];
		} else if (spin == 2) { //270도
			temp[row + 1][col - 1] = numbers[0];
			temp[row][col - 1] = numbers[1];
			temp[row - 1][col - 1] = numbers[2];
			temp[row - 1][col] = numbers[3];
			temp[row - 1][col + 1] = numbers[4];
			temp[row][col + 1] = numbers[5];
			temp[row + 1][col + 1] = numbers[6];
			temp[row + 1][col] = numbers[7];
		}

		int tempValue = calculateExpectedValue(temp);
		if (tempValue > expectedValue) {
			expectedValue = tempValue;
			turnedMap = temp;
		}
	}

	public int calculateExpectedValue(int[][] temp) {
		int sum = 0;

		boolean[][] visited = new boolean[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				Deque<int[]> toVisit = new ArrayDeque<>();
				toVisit.add(new int[] {i, j});
				visited[i][j] = true;
				int ruinNumber = temp[i][j];
				int ruinCount = 1;

				while(!toVisit.isEmpty()) {
					int[] now = toVisit.poll();
					for(int[] delta : deltas) {
						int nextRow = now[0] + delta[0];
						int nextCol = now[1] + delta[1];

						if (!inRange(nextRow, nextCol)) continue;
						if (visited[nextRow][nextCol]) continue;
						if (temp[nextRow][nextCol] != ruinNumber) continue;

						toVisit.add(new int[] {nextRow, nextCol});
						visited[nextRow][nextCol] = true;
						ruinCount++;
					}
				}
				if (ruinCount >= 3) {
					sum += ruinCount;
				}
			}
		}
		return sum;
	}

	public void getRuins() {
		while(true) {
			int sum = 0;

			boolean[][] visited = new boolean[5][5];
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					if (map[i][j] == 0) continue;
					Deque<int[]> toVisit = new ArrayDeque<>();
					List<int[]> positions = new ArrayList<>();

					toVisit.add(new int[] {i, j});
					positions.add(new int[] {i, j});
					visited[i][j] = true;
					int ruinNumber = map[i][j];
					int ruinCount = 1;

					while(!toVisit.isEmpty()) {
						int[] now = toVisit.poll();
						for(int[] delta : deltas) {
							int nextRow = now[0] + delta[0];
							int nextCol = now[1] + delta[1];

							if (!inRange(nextRow, nextCol)) continue;
							if (visited[nextRow][nextCol]) continue;
							if (map[nextRow][nextCol] != ruinNumber) continue;

							toVisit.add(new int[] {nextRow, nextCol});
							positions.add(new int[] {nextRow, nextCol});
							visited[nextRow][nextCol] = true;
							ruinCount++;
						}
					}
					if (ruinCount >= 3) {
						sum += ruinCount;
						for(int[] position : positions) {
							map[position[0]][position[1]] = 0;
						}
					}
				}
			}

			if (sum == 0) return;
			value += sum;

			arrageRuins();
		}
	}


	public void arrageRuins() {
		for(int j = 0; j < 5; j++) {
			for(int i = 4; i >= 0; i--) {
				if (map[i][j] != 0) continue;
				map[i][j] = wallNumbers.poll();
			}
		}
	}

	public boolean inRange(int row, int col) {
		return ((row >= 0) && (row < 5) && (col >= 0) && (col < 5));
	}



	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		K = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new int[5][5];
		for(int i = 0; i < 5; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		wallNumbers = new ArrayDeque<>();
		tokenizer = new StringTokenizer(reader.readLine());
		for(int i = 0; i < M; i++) {
			wallNumbers.offer(Integer.parseInt(tokenizer.nextToken()));
		}
	}

	public static void main(String[] args) throws IOException {
		new AncientRuinExploration_2().solution();
	}
}

