package com.example.javacodingtest.codetree;


import java.util.*;
import java.io.*;


public class CubeRoundingAgain {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M;
	static int[][] map;
	static int[][] deltas = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static int direction = 3;
	static Dice dice;
	static int score;

	class Dice {
		int row;
		int col;
		int up;
		int down;
		int front;
		int back;
		int right;
		int left;

		Dice(int row, int col, int up, int down, int front, int back, int right, int left) {
			this.row = row;
			this.col = col;
			this.up = up;
			this.down = down;
			this.front = front;
			this.back = back;
			this.right = right;
			this.left = left;
		}

		public void turn(int direction) {
			if (direction == 0) { // 위
				int up = this.up;
				int down = this.down;
				int front = this.front;
				int back = this.back;

				this.up = front;
				this.down = back;
				this.front = down;
				this.back = up;

			} else if (direction == 1) { // 왼
				int up = this.up;
				int down = this.down;
				int left = this.left;
				int right = this.right;

				this.up = right;
				this.down = left;
				this.left = up;
				this.right = down;

			} else if (direction == 2) { // 아
				int up = this.up;
				int down = this.down;
				int front = this.front;
				int back = this.back;

				this.up = back;
				this.down = front;
				this.front = up;
				this.back = down;

			} else { // 오
				int up = this.up;
				int down = this.down;
				int left = this.left;
				int right = this.right;

				this.up = left;
				this.down = right;
				this.left = down;
				this.right = up;

			}
		}
	}

	public void solution() throws IOException {
		init();
		for (int i = 0; i < M; i++) {
			moveDice();
			getScore();
		}

		builder.append(score);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void moveDice() {
		int nextRow = dice.row + deltas[direction][0];
		int nextCol = dice.col + deltas[direction][1];

		if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
			direction = (direction + 2) % 4;
			nextRow = dice.row + deltas[direction][0];
			nextCol = dice.col + deltas[direction][1];
		}

		dice.row = nextRow;
		dice.col = nextCol;
		dice.turn(direction);
		int downNum = dice.down;
		int mapNum = map[dice.row][dice.col];

		if (mapNum < downNum) { // 시계
			direction = (direction + 3) % 4;

		} else if (mapNum > downNum) { // 반시계
			direction = (direction + 1) % 4;
		}

	}

	public void getScore() {
		int num = map[dice.row][dice.col];

		Deque<int[]> toVisit = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		toVisit.add(new int[] {dice.row, dice.col});
		visited[dice.row][dice.col] = true;

		int space = 0;
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			space++;

			for (int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N)
					continue;
				if (visited[nextRow][nextCol])
					continue;
				if (map[now[0]][now[1]] != map[nextRow][nextCol])
					continue;

				toVisit.add(new int[] {nextRow, nextCol});
				visited[nextRow][nextCol] = true;
			}
		}

		score += (num * space);
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		dice = new Dice(0, 0, 1, 6, 2, 5, 3, 4);

	}

	public static void main(String[] args) throws IOException {
		new CubeRoundingAgain().solution();
	}
}