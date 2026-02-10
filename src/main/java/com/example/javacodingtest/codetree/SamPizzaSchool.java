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

public class SamPizzaSchool {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, K;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int turn;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public void solution() throws IOException {
		init();

		while((max - min) > K) {
			turn++;
			add();
			roll();
			System.out.println("AFTER ROLL");
			for(int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			press();
			System.out.println("AFTER PRESS");
			for(int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println();
			folding();
			System.out.println("AFTER FOLDING");
			for(int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println();
			press();
			System.out.println("AFTER PRESS");
			for(int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println();
			resetMinMax();
		}

		builder.append(turn);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void add() {
		for(int i = 0; i < N; i++) {
			if (map[0][i] == min) map[0][i]++;
		}
		min++;
	}

	public void roll() {
		int rowSize = 1;
		int colSize = 1;
		while(true) {
			int[][] temp = new int[rowSize][colSize];

			for(int i = 0; i < rowSize; i++) {
				for(int j = 0; j < colSize; j++) {
					temp[i][j] = map[i][j];
				}
			}

			List<Integer> numList = new ArrayList<Integer>();
			for(int i = colSize; i < N; i++) {
				if (map[0][i] != 0) numList.add(map[0][i]);
			}

			for(int i = 0; i < N; i++) {
				Arrays.fill(map[i], 0);
			}

			for(int i = 0; i < numList.size(); i++) {
				map[0][i] = numList.get(i);
			}

			for(int i = 0; i < colSize; i++) {
				for(int j = 0; j < rowSize; j++) {
					map[i + 1][j] = temp[j][colSize - i - 1];
				}
			}

			if (rowSize > colSize) colSize++;
			else rowSize++;

			if (rowSize > numList.size() - colSize) break;
		}
	}

	public void press() {
		int[][] newMap = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				newMap[i][j] = map[i][j];
			}
		}

		boolean[][] visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;


				visited[i][j] = true;
				for(int[] delta : deltas) {
					int nextRow = i + delta[0];
					int nextCol = j + delta[1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
					if (visited[nextRow][nextCol]) continue;
					if (map[nextRow][nextCol] == 0) continue;

					int d = Math.abs(map[i][j] - map[nextRow][nextCol]) / 5;

					if (map[i][j] > map[nextRow][nextCol]) {
						newMap[i][j] -= d;
						newMap[nextRow][nextCol] += d;
					} else {
						newMap[i][j] += d;
						newMap[nextRow][nextCol] -= d;
					}
				}

			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = newMap[i][j];
			}
		}

		List<Integer> numList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[j][i] == 0) continue;
				numList.add(map[j][i]);
			}
		}

		for(int i = 0; i < N; i++) {
			Arrays.fill(map[i], 0);
		}

		for(int i = 0; i < numList.size(); i++) {
			map[0][i] = numList.get(i);
		}
	}

	public void folding() {
		int[] first = new int[N / 2];
		int[] second = new int[N / 2];

		for(int i = 0; i < N; i++) {
			if (i < N / 2) first[i] = map[0][i];
			else second[i - N / 2] = map[0][i];
		}

		Arrays.fill(map[0], 0);

		for(int i = 0; i < N / 2; i++) {
			map[1][i] = first[N / 2 - i - 1];
			map[0][i] = second[i];
		}

		int[][] firstSquare = new int[2][N / 4];
		int[][] secondSquare = new int[2][N / 4];

		for(int i = 0; i < N / 2; i++) {
			if (i < N / 4) {
				firstSquare[0][i] = map[0][i];
				firstSquare[1][i] = map[1][i];
			}
			else {
				secondSquare[0][i - N / 4] = map[0][i];
				secondSquare[1][i - N / 4] = map[1][i];
			}
		}

		Arrays.fill(map[0], 0);
		Arrays.fill(map[1], 0);

		for(int i = 0; i < N / 4; i++) {
			map[0][i] = secondSquare[0][i];
			map[1][i] = secondSquare[1][i];
			map[2][i] = firstSquare[1][N / 4 - i - 1];
			map[3][i] = firstSquare[0][N / 4 - i - 1];
		}
	}


	public void resetMinMax() {
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			if (map[0][i] == 0) continue;
			min = Math.min(min, map[0][i]);
			max = Math.max(max, map[0][i]);
		}
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];

		tokenizer = new StringTokenizer(reader.readLine());
		for(int i = 0; i < N; i++) {
			map[0][i] = Integer.parseInt(tokenizer.nextToken());
			min = Math.min(min, map[0][i]);
			max = Math.max(max, map[0][i]);
		}
	}


	public static void main(String[] args) throws IOException {
		new SamPizzaSchool().solution();
	}

}
