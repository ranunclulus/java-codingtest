package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;
/*
 @author ranuinclulus
 @since 2026.01.04
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/microbial-research/description
 @timecomplex
 @performance
 @category
 @note
 */
public class MicrobialResearch_3 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, Q;
	static int[][] inputs;
	static int[][] map;
	static int[][] newMap;
	static int[] groupCount;
	static boolean[][] visited;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static PriorityQueue<Group> moveSequence;

	class Group implements Comparable<Group> {
		int q;
		int row;
		int col;
		int size;

		Group(int q, int size, int row, int col) {
			this.q = q;
			this.size = size;
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Group o) {
			if (this.size == o.size) {
				return Integer.compare(this.q, o.q);
			}
			return -Integer.compare(this.size, o.size);
		}
	}

	public void insertMicrobe(int q) {
		int[] input = inputs[q];
		for (int i = input[0]; i <= input[2]; i++) {
			for (int j = input[1]; j <= input[3]; j++) {
				map[i][j] = q;
			}
		}


	}

	public void countingGroup() {
		groupCount = new int[Q + 1];
		visited = new boolean[N][N];
		for (int q = 1; q <= Q; q++) {

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					if (map[i][j] != q) continue;
					if (visited[i][j]) continue;

					Deque<int[]> toVisit = new ArrayDeque<>();
					visited[i][j] = true;
					toVisit.add(new int[] {i, j});

					while(!toVisit.isEmpty()) {
						int[] now = toVisit.poll();

						for(int[] delta : deltas) {
							int nextRow = now[0] + delta[0];
							int nextCol = now[1] + delta[1];

							if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
							if (visited[nextRow][nextCol]) continue;
							if (map[nextRow][nextCol] != q) continue;

							toVisit.add(new int[] {nextRow, nextCol});
							visited[nextRow][nextCol] = true;
						}
					}

					groupCount[q]++;
				}
			}
		}
	}

	public void deleteMicrobeGroup() {
		for (int q = 1; q <= Q; q++) {

			if (groupCount[q] < 2) continue;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == q) map[i][j] = 0;
				}
			}
		}
	}

	public void makeMoveSequence() {
		moveSequence = new PriorityQueue<>();
		for (int q = 1; q <= Q; q++) {
			int size = 0;
			int row = N + 1;
			int col = N + 1;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != q) continue;
					row = Math.min(row, i);
					col = Math.min(col, j);
					size++;
				}
			}

			if (size == 0) continue;

			moveSequence.add(new Group(q, size, row, col));
		}
	}

	public void moveMicrobe() {
		newMap = new int[N][N];

		while(!moveSequence.isEmpty()) {
			Group target = moveSequence.poll();

			int startRow = N + 1;
			int startCol = N + 1;

			boolean possible = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(canMove(i, j, target)) { // 이동이 가능하다면
						startRow = i;
						startCol = j;
						possible = true;
						break;
					}
					// 이동이 불가능하다면 계속 탐색
				}
				if (possible) break;
			}
			if (possible) { // 이동 가능한 곳 찾음
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if ((target.row + i) >= N || (target.col + j) >= N || (startRow + i) >= N || (startCol + j) >= N) continue;
						if (map[target.row + i][target.col + j] == target.q) {
							newMap[startRow + i][startCol + j] = target.q;
						}
					}
				}
			}

		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = newMap[i][j];
			}
		}


	}

	public boolean canMove(int row, int col, Group target) {
		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((row + i) >= N || (col + j) >= N || (target.row + i) >= N || (target.col + j) >= N) continue;
				if (map[target.row + i][target.col + j] == target.q && newMap[row + i][col + j] == 0) count++;
				if (map[target.row + i][target.col + j] == target.q && newMap[row + i][col + j] != 0) return false;
			}
		}
		return (count == target.size);
	}

	public void recordResearch() {
		boolean[][] isRecord = new boolean[Q + 1][Q + 1];
		int[] size = new int[Q + 1];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;
				size[map[i][j]]++;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				for(int[] delta : deltas) {
					int nextRow = i + delta[0];
					int nextCol = j + delta[1];
					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;

					if (map[i][j] == map[nextRow][nextCol]) continue;

					isRecord[map[i][j]][map[nextRow][nextCol]] = true;
					isRecord[map[nextRow][nextCol]][map[i][j]] = true;
				}
			}
		}

		int score = 0;
		for (int i = 1; i <= Q; i++) {
			for (int j = 1; j <= Q; j++) {
				if (isRecord[i][j]) score += (size[i] * size[j]);
			}
		}
		score /= 2;
		builder.append(score).append('\n');
	}


	public void solution() throws IOException {
		init();
		for (int research = 1; research <= Q; research++) {
			insertMicrobe(research);

			countingGroup();

			deleteMicrobeGroup();
			makeMoveSequence();
			moveMicrobe();
			recordResearch();
		}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		Q = Integer.parseInt(tokenizer.nextToken());

		inputs = new int[Q + 1][4];
		for (int i = 1; i <= Q; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			inputs[i][0] = Integer.parseInt(tokenizer.nextToken());
			inputs[i][1] = Integer.parseInt(tokenizer.nextToken());
			inputs[i][2] = Integer.parseInt(tokenizer.nextToken()) - 1;
			inputs[i][3] = Integer.parseInt(tokenizer.nextToken()) - 1;
		}
		map = new int[N][N];
	}

	public static void main(String[] args) throws IOException {
		new MicrobialResearch_3().solution();
	}

}
