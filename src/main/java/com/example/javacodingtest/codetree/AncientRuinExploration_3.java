package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2026.01.10
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/ancient-ruin-exploration/description
 @timecomplex
 @performance
 @category
 @note
 */
public class AncientRuinExploration_3 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int K, M, SCORE, MAP_INDEX;
	static int[][] map;
	static int[] walls;
	static PriorityQueue<Spin> candidate;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	class Spin implements Comparable<Spin>{
		int angle;
		int row;
		int col;
		int value;

		Spin(int row, int col, int angle, int value) {
			this.row = row;
			this.col = col;
			this.angle = angle;
			this.value = value;
		}

		@Override
		public int compareTo(Spin o) {
			if (this.value == o.value) {
				if (this.angle == o.angle) {
					if (this.col == o.col) return Integer.compare(this.row, o.row);
					return Integer.compare(this.col, o.col);
				}
				return Integer.compare(this.angle, o.angle);
			}
			return -Integer.compare(this.value, o.value);
		}
	}

	public void solution() throws IOException {
		init();

		while(K --> 0) {
			SCORE = 0;
			Spin target = decideSpin();
			if (target.value == 0) break;
			explore(spinMap(target.row, target.col, target.angle));
			fillMap();
			
			while(calculateValue(map) != 0) {
				explore(map);
				fillMap();
			}
			builder.append(SCORE).append(" ");
		}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public Spin decideSpin() {
		candidate = new PriorityQueue<>();
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				for (int k = 90; k <= 270; k += 90) {
					// row:i, col:j, angle: k
					int[][] temp = spinMap(i, j, k);

					int value = calculateValue(temp);
					candidate.add(new Spin(i, j, k, value));
				}
			}
		}

		return candidate.poll();
	}

	public int[][] spinMap(int row, int col, int angle) {
		int[][] temp = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				temp[i][j] = map[i][j];
			}
		}

		if (angle == 90) {
			temp[row - 1][col - 1] = map[row + 1][col - 1];
			temp[row - 1][col] = map[row][col - 1];
			temp[row - 1][col + 1] = map[row - 1][col - 1];
			temp[row][col + 1] = map[row - 1][col];
			temp[row + 1][col + 1] = map[row - 1][col + 1];
			temp[row + 1][col] = map[row][col + 1];
			temp[row + 1][col - 1] = map[row + 1][col + 1];
			temp[row][col - 1] = map[row + 1][col];
		} else if (angle == 180) {
			temp[row - 1][col - 1] = map[row + 1][col + 1];
			temp[row - 1][col] = map[row + 1][col];
			temp[row - 1][col + 1] = map[row + 1][col - 1];
			temp[row][col + 1] = map[row][col - 1];
			temp[row + 1][col + 1] = map[row - 1][col - 1];
			temp[row + 1][col] = map[row - 1][col];
			temp[row + 1][col - 1] = map[row - 1][col + 1];
			temp[row][col - 1] = map[row][col + 1];
		} else {
			temp[row - 1][col - 1] = map[row - 1][col + 1];
			temp[row - 1][col] = map[row][col + 1];
			temp[row - 1][col + 1] = map[row + 1][col + 1];
			temp[row][col + 1] = map[row + 1][col];
			temp[row + 1][col + 1] = map[row + 1][col - 1];
			temp[row + 1][col] = map[row][col - 1];
			temp[row + 1][col - 1] = map[row - 1][col - 1];
			temp[row][col - 1] = map[row - 1][col];
		}

		return temp;
	}

	public int calculateValue(int[][] temp) {
		int value = 0;
		boolean[][] visited = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Deque<int[]> toVisit = new ArrayDeque<>();
				if (visited[i][j]) continue;

				int number = 0;

				toVisit.add(new int[] {i, j, 1});
				visited[i][j] = true;

				while (!toVisit.isEmpty()) {
					int[] now = toVisit.poll();
					number++;

					for(int[] delta: deltas) {
						int nextRow = now[0] + delta[0];
						int nextCol = now[1] + delta[1];

						if (nextRow < 0 || nextRow >= 5 || nextCol < 0 || nextCol >= 5) continue;
						if (visited[nextRow][nextCol]) continue;
						if (temp[nextRow][nextCol] != temp[i][j]) continue;

						toVisit.add(new int[] {nextRow, nextCol, now[2] + 1});
						visited[nextRow][nextCol] = true;
					}
				}

				if (number >= 3) value += number;
			}
		}
		return value;
	}

	public void explore(int[][] temp) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[i][j] = temp[i][j];
			}
		}

		int value = 0;
		boolean[][] visited = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Deque<int[]> toVisit = new ArrayDeque<>();
				Deque<int[]> memory = new ArrayDeque<>();
				if (visited[i][j]) continue;

				int number = 0;

				toVisit.add(new int[] {i, j, 1});
				memory.add(new int[] {i, j});
				visited[i][j] = true;

				while (!toVisit.isEmpty()) {
					int[] now = toVisit.poll();
					number++;

					for(int[] delta: deltas) {
						int nextRow = now[0] + delta[0];
						int nextCol = now[1] + delta[1];

						if (nextRow < 0 || nextRow >= 5 || nextCol < 0 || nextCol >= 5) continue;
						if (visited[nextRow][nextCol]) continue;
						if (temp[nextRow][nextCol] != temp[i][j]) continue;

						toVisit.add(new int[] {nextRow, nextCol, now[2] + 1});
						visited[nextRow][nextCol] = true;
						memory.add(new int[] {nextRow, nextCol});
					}
				}

				if (number >= 3) {
					value += number;
					while (!memory.isEmpty()) {
						int[] drop = memory.poll();
						map[drop[0]][drop[1]] = 0;
					}
				}
			}
		}
		SCORE += value;
	}
	
	public void fillMap() {
		if (MAP_INDEX == M) return;

		for (int i = 0; i < 5; i++) {
			for (int j = 4; j >= 0; j--) {
				if (map[j][i] == 0) {
					map[j][i] = walls[MAP_INDEX++];
					if (MAP_INDEX == M) return;
				}

			}
		}
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		K = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new int[5][5];
		for (int i = 0; i < 5; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		walls = new int[M];
		tokenizer = new StringTokenizer(reader.readLine());
		for (int i = 0; i < M; i++) {
			walls[i] = Integer.parseInt(tokenizer.nextToken());
		}
	}


	public static void main(String[] args) throws IOException {
		new AncientRuinExploration_3().solution();
	}
}

