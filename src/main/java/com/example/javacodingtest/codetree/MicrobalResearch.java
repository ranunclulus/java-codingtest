package com.example.javacodingtest.codetree;


import java.io.*;
import java.util.*;

/*
 @author ranunclulus
 @since 2025.08.21
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/microbial-research/description
 @timecomplex
 @performance
 @category
 @note
 */
public class MicrobalResearch {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, Q;
	static int[][] microBoard;
	static int[][] newMicroBoard;
	static int startRow, startCol, endRow, endCol;
	static boolean[][] visited;
	static Deque<int[]> toVisit;
	static MicroOrganism[] microOrganisms;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	class MicroOrganism implements Comparable<MicroOrganism>{

		int startRow;
		int startCol;
		int endRow;
		int endCol;
		int type;
		int size;
		boolean isAlived;

		public MicroOrganism(int type, int startRow, int startCol, int endRow, int endCol) {
			this.type = type;
			this.startRow = startRow;
			this.startCol = startCol;
			this.endRow = endRow;
			this.endCol = endCol;
			this.size = (endRow - startRow) * (endCol - startCol);
			this.isAlived = true;
		}

		@Override
		public int compareTo(MicroOrganism o) {
			if (this.size <= o.size) return Integer.compare(this.type, o.type);
			return -Integer.compare(this.size, o.size);
		}

		public void resize() {
			int size = 0;

			for(int i = startRow; i < N; i++) {
				for(int j = startCol; j < N; j++) {
					if (microBoard[i][j] == this.type) size++;
				}
			}

			this.size = size;
		}
	}

	public void insertOrganism(int type, int startRow, int startCol, int endRow, int endCol) {
		for(int i = startRow; i < endRow; i++) {
			for(int j = startCol; j < endCol; j++) {
				microBoard[i][j] = type;
			}
		}
		microOrganisms[type] = new MicroOrganism(type, startRow, startCol, endRow, endCol);

		for(int i = 1; i < type; i++) {
			int microOrganismCount = countMicroOrganism(i);
			if (microOrganismCount > 1) {
				microOrganisms[i].isAlived = false;
				for(int j = 0; j < N; j++) {
					for(int k = 0; k < N; k++) {
						if (microBoard[j][k] == i) microBoard[j][k] = 0;
					}
				}
			}
		}

		for(int i = 1; i < type; i++) {
			microOrganisms[i].resize();
		}
	}

	public int countMicroOrganism(int type) {
		int count = 0;
		visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (microBoard[i][j] != type) continue;
				if (visited[i][j]) continue;

				count++;
				toVisit = new ArrayDeque<>();
				visited[i][j] = true;
				toVisit.add(new int[] {i, j});

				while (!toVisit.isEmpty()) {
					int[] now = toVisit.poll();
					for(int[] delta : deltas) {
						int nextRow = now[0] + delta[0];
						int nextCol = now[1] + delta[1];

						if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
						if (visited[nextRow][nextCol]) continue;
						if (microBoard[nextRow][nextCol] != type) continue;

						toVisit.add(new int[] {nextRow, nextCol});
						visited[nextRow][nextCol] = true;
					}
				}
			}
		}
		return count;
	}

	public void moveOrganism() {
		newMicroBoard = new int[N][N];

		PriorityQueue<MicroOrganism> pq = new PriorityQueue<>();
		for(int i = 0; i <= N; i++) {
			if (microOrganisms[i] == null) continue;
			if (microOrganisms[i].isAlived) pq.offer(microOrganisms[i]);
		}

		while(!pq.isEmpty()) {
			MicroOrganism now = pq.poll();

			boolean isMoved = false;
			for(int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (canMove(i, j, now)) {
						move(i, j, now);
						isMoved = true;
						break;
					}
				}
				if (isMoved) break;
			}

			if (!isMoved) {
				now.isAlived = false;
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				microBoard[i][j] = newMicroBoard[i][j];
			}
		}
	}



	public void move(int row, int col, MicroOrganism microOrganism) {

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int newRow = row + i;
				int newCol = col + j;
				int originalRow = microOrganism.startRow + i;
				int originalCol = microOrganism.startCol + j;

				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) continue;
				if (originalRow < 0 || originalRow >= N || originalCol < 0 || originalCol >= N) continue;
				if (microBoard[originalRow][originalCol] != microOrganism.type) continue;

				newMicroBoard[newRow][newCol] = microOrganism.type;
			}
		}

		microOrganism.startCol = col;
		microOrganism.startRow = row;
	}

	public boolean canMove(int row, int col, MicroOrganism microOrganism) {
		int count = 0;
		for(int i = row; i < N; i++) {
			for(int j = col; j < N; j++) {
				int originalRow = microOrganism.startRow + (i - row);
				int originalCol = microOrganism.startCol + (j - col);

				if (originalRow < 0 || originalRow >= N || originalCol < 0 || originalCol >= N) continue;
				if (microBoard[originalRow][originalCol] != microOrganism.type) continue;
				count++;
				if (newMicroBoard[i][j] != 0) return false;
			}
		}
		return (count == microOrganism.size);
	}

	public void countScore() {
		int score = 0;
		boolean[][] scoreBoard = new boolean[N + 1][N + 1];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (microBoard[i][j] == 0) continue;
				for(int[] delta : deltas) {
					int nextRow = i + delta[0];
					int nextCol = j + delta[1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
					if (microBoard[nextRow][nextCol] == 0) continue;
					if (microBoard[i][j] == microBoard[nextRow][nextCol]) continue;

					int typeOne = microBoard[i][j];
					int typeTwo = microBoard[nextRow][nextCol];

					if (scoreBoard[typeOne][typeTwo] || scoreBoard[typeTwo][typeOne]) continue;

					score += microOrganisms[typeOne].size * microOrganisms[typeTwo].size;

					scoreBoard[typeOne][typeTwo] = true;
					scoreBoard[typeTwo][typeOne] = true;
				}
			}
		}
		builder.append(score).append('\n');
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		Q = Integer.parseInt(tokenizer.nextToken());

		microBoard = new int[N][N];
		microOrganisms = new MicroOrganism[N + 1];
		for(int i = 1; i <= Q; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			startRow = Integer.parseInt(tokenizer.nextToken());
			startCol = Integer.parseInt(tokenizer.nextToken());
			endRow = Integer.parseInt(tokenizer.nextToken());
			endCol = Integer.parseInt(tokenizer.nextToken());

			insertOrganism(i, startRow, startCol, endRow, endCol);
			moveOrganism();
			countScore();

		}

		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void solution() throws IOException {
		init();
	}

	public static void main(String[] args) throws IOException {
		new MicrobalResearch().solution();
	}
}

