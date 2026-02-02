package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

public class Pacman {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int M, T;
	static int[][] deadMap;
	static List[][] monstersMap;
	static List[][] eggsMap;
	static int[][] deltas = new int[][] {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
	static int pacmanRow, pacmanCol;
	static List<int[]> pacmanMove;


	public void solution() throws IOException {
		init();
		for(int time = 1; time <= T; time++) {
			copyMonsters();
			moveMonsters();
			movePacman();
			bornMonsters();
		}

		builder.append(getAnswer());
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void copyMonsters() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Iterator<Integer> iterator = monstersMap[i][j].iterator();
				while(iterator.hasNext()) {
					int direction = iterator.next();
					eggsMap[i][j].add(direction);
				}
			}
		}
	}

	public void moveMonsters() {
		List[][] copyMap = new List[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copyMap[i][j] = new ArrayList<Integer>();
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {

				Iterator<Integer> iterator = monstersMap[i][j].iterator();
				while(iterator.hasNext()){
					int direction = iterator.next();
					boolean canMove = false;

					for (int k = 0; k < 8; k++) {
						int newDirection = (direction + k) % 8;
						int nextRow = i + deltas[newDirection][0];
						int nextCol = j + deltas[newDirection][1];

						if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) continue;
						if (nextRow == pacmanRow && nextCol == pacmanCol) continue;
						if (deadMap[nextRow][nextCol] != 0) continue;

						copyMap[nextRow][nextCol].add(newDirection);
						canMove = true;
						break;
					}

					if (!canMove) copyMap[i][j].add(direction);
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				monstersMap[i][j].clear();
				for (int k = 0; k < copyMap[i][j].size(); k++) {
					monstersMap[i][j].add(copyMap[i][j].get(k));
				}
			}
		}
	}

	public void movePacman() {

		int decisionIndex = -1;
		int maxEatCount = Integer.MIN_VALUE;


		for(int i = 0; i < pacmanMove.size(); i++) {

			int[] moving = pacmanMove.get(i);
			int row = pacmanRow;
			int col = pacmanCol;
			boolean canMove = true;
			int eatCount = 0;
			boolean[][] visited = new boolean[4][4];

			for(int move : moving) {
				row += deltas[move][0];
				col += deltas[move][1];

				if (row < 0 || row >= 4 || col < 0 || col >= 4) {
					canMove = false;
					break;
				}
				if (visited[row][col]) continue;

				int eat = monstersMap[row][col].size();
				eatCount += eat;
				visited[row][col] = true;
			}

			if (!canMove) continue;

			if (eatCount > maxEatCount) {
				maxEatCount = eatCount;
				decisionIndex = i;
			}

		}

		int[] moving = pacmanMove.get(decisionIndex);
		int row = pacmanRow;
		int col = pacmanCol;
		for(int move : moving) {
			row += deltas[move][0];
			col += deltas[move][1];

			if (monstersMap[row][col].size() != 0) {
				deadMap[row][col] = 3;
			}
			monstersMap[row][col].clear();

		}

		pacmanRow = row;
		pacmanCol = col;

	}

	public void bornMonsters() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Iterator<Integer> iterator = eggsMap[i][j].iterator();
				while(iterator.hasNext()) {
					int direction = iterator.next();
					monstersMap[i][j].add(direction);
				}
				eggsMap[i][j].clear();
			}
		}

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if (deadMap[i][j] > 0) deadMap[i][j]--;
			}
		}
	}

	public int getAnswer() {
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				answer += monstersMap[i][j].size();
			}
		}
		return answer;
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		M = Integer.parseInt(tokenizer.nextToken());
		T = Integer.parseInt(tokenizer.nextToken());

		tokenizer = new StringTokenizer(reader.readLine());
		pacmanRow = Integer.parseInt(tokenizer.nextToken()) - 1;
		pacmanCol = Integer.parseInt(tokenizer.nextToken()) - 1;

		deadMap = new int[4][4];
		monstersMap = new List[4][4];
		eggsMap = new List[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				monstersMap[i][j] = new ArrayList<Integer>();
				eggsMap[i][j] = new ArrayList<Integer>();
			}
		}

		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int row = Integer.parseInt(tokenizer.nextToken()) - 1;
			int col = Integer.parseInt(tokenizer.nextToken()) - 1;
			int direction = Integer.parseInt(tokenizer.nextToken()) - 1;
			monstersMap[row][col].add(direction);
		}

		pacmanMove = new ArrayList<int[]>();
		for(int f = 0; f < 8; f += 2) {
			for(int s = 0; s < 8; s += 2) {
				for(int t = 0; t < 8; t += 2) {
					pacmanMove.add(new int[] {f, s, t});
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new Pacman().solution();
	}

}

