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
 @author ranunclulus
 @since 2025.08.25
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/microbial-research/description
 @timecomplex
 @performance
 @category
 @note
 */
public class MicrobalResearch_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static final int MAX_CONTAINER = 20;
	static final int MAX_MICRO = 55;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int containerSize, researchCount;
	static int[][] microBoard = new int[MAX_CONTAINER][MAX_CONTAINER];
	static int[][] newMicroBoard;
	static int[] microCount;
	static int[] microSize = new int[MAX_MICRO + 1];
	static boolean[][] visited;
	static Pair[] startPair = new Pair[MAX_MICRO + 1];
	static Pair[] endPair = new Pair[MAX_MICRO + 1];

	class Pair {
		int row;
		int col;

		Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	class Order implements Comparable<Order> {
		int size;
		int microId;

		Order(int size, int microId) {
			this.size = size;
			this.microId = microId;


		}

		@Override
		public int compareTo(Order o) {
			if (this.size == o.size) return Integer.compare(this.microId, o.microId);
			return -Integer.compare(this.size, o.size);
		}
	}

	public void intsertMicroOrganisms(int startRow, int startCol, int endRow, int endCol, int microNo) {
		for(int r = startRow; r < endRow; r++) {
			for(int c = startCol; c < endCol; c++) {
				microBoard[r][c] = microNo;
			}
		}

		microCount = new int[MAX_MICRO + 1];
		visited = new boolean[MAX_CONTAINER][MAX_CONTAINER];
		for(int r = 0; r < containerSize; r++) {
			for(int c = 0; c < containerSize; c++) {
				if (microBoard[r][c] == 0) continue;
				if (visited[r][c]) continue;

				countOrganismArea(r, c, visited);
			}
		}

		for(int microId = 1; microId <= microNo; microId++) {
			if (microCount[microId] > 1) removeMicro(microId);
		}
	}

	public void removeMicro(int microId) {
		for (int r = 0; r < containerSize; r++) {
			for (int c = 0; c < containerSize; c++) {
				if (microBoard[r][c] == microId) microBoard[r][c] = 0;
			}
		}
	}

	public void countOrganismArea(int row, int col, boolean[][] visited) {
		microCount[microBoard[row][col]]++;

		Deque<int[]> toVisit = new ArrayDeque<>();
		toVisit.add(new int[] {row, col});
		visited[row][col] = true;

		while(!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (nextRow < 0 || nextRow >= containerSize || nextCol < 0 || nextCol >= containerSize) continue;
				if (visited[nextRow][nextCol]) continue;
				if (microBoard[nextRow][nextCol] != microBoard[row][col]) continue;

				toVisit.add(new int[] {nextRow, nextCol});
				visited[nextRow][nextCol] = true;
			}
		}
	}

	public void relocateMicroOrganisms(int microNo) {
		newMicroBoard = new int[MAX_CONTAINER][MAX_CONTAINER];
		microSize = new int[MAX_MICRO + 1];

		for(int microId = 1; microId <= microNo; microId++) {
			startPair[microId] = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
			endPair[microId] = new Pair(0, 0);
		}

		for(int r = 0; r < containerSize; r++) {
			for(int c = 0; c < containerSize; c++) {
				if (microBoard[r][c] == 0) continue;
				microSize[microBoard[r][c]]++;
				startPair[microBoard[r][c]].row = Math.min(r, startPair[microBoard[r][c]].row);
				startPair[microBoard[r][c]].col = Math.min(c, startPair[microBoard[r][c]].col);
				endPair[microBoard[r][c]].row = Math.max(r, endPair[microBoard[r][c]].row);
				endPair[microBoard[r][c]].col = Math.max(c, endPair[microBoard[r][c]].col);
			}
		}

		PriorityQueue<Order> microPQ = new PriorityQueue<>();
		for(int microId = 1; microId <= microNo; microId++) {
			microPQ.add(new Order(microSize[microId], microId));
		}

		while (!microPQ.isEmpty()) {
			Order now = microPQ.poll();

			Pair boundingStart = startPair[now.microId];
			Pair boundingEnd = endPair[now.microId];

			int rowSize = boundingEnd.row - boundingStart.row + 1;
			int colSize = boundingEnd.col - boundingStart.col + 1;

			for(int r = 0; r <= containerSize - rowSize; r++) {
				boolean canMoveRow = false;
				for(int c = 0; c <= containerSize - colSize; c++) {
					boolean canMoveCol = true;

					for(int dr = 0; dr < rowSize; dr++) {
						for(int dc = 0; dc < colSize; dc++) {
							int originalRow = boundingStart.row + dr;
							int originalCol = boundingStart.col + dc;

							if (microBoard[originalRow][originalCol] != now.microId) continue;

							int newRow = r + dr;
							int newCol = c + dc;

							if (newMicroBoard[newRow][newCol] != 0) {
								canMoveCol = false;
								break;
							}
						}
						if (!canMoveCol) break;
					}

					if (canMoveCol) {
						for(int dr = 0; dr < rowSize; dr++) {
							for(int dc = 0; dc < colSize; dc++) {
								int originalRow = boundingStart.row + dr;
								int originalCol = boundingStart.col + dc;

								if (microBoard[originalRow][originalCol] != now.microId) continue;

								int newRow = r + dr;
								int newCol = c + dc;

								newMicroBoard[newRow][newCol] = now.microId;
							}
						}

						canMoveRow = true;
						break;
					}
				}
				if (canMoveRow) break;
			}
		}

		for(int r = 0; r < MAX_CONTAINER; r++) {
			for(int c = 0; c < MAX_CONTAINER; c++) {
				microBoard[r][c] = newMicroBoard[r][c];
			}
		}
	}

	public void calculation() {
		boolean[][] isAdjacent = new boolean[MAX_MICRO + 1][MAX_MICRO + 1];
		int score = 0;

		for(int r = 0; r < containerSize; r++) {
			for(int c = 0; c < containerSize; c++) {
				for(int[] delta : deltas) {
					int nextRow = r + delta[0];
					int nextCol = c + delta[1];

					if (nextRow < 0 || nextRow >= containerSize || nextCol < 0 || nextCol >= containerSize) continue;
					if (microBoard[r][c] == microBoard[nextRow][nextCol]) continue;
					if (isAdjacent[microBoard[r][c]][microBoard[nextRow][nextCol]]) continue;
					if (isAdjacent[microBoard[nextRow][nextCol]][microBoard[r][c]]) continue;

					isAdjacent[microBoard[r][c]][microBoard[nextRow][nextCol]] = true;
					isAdjacent[microBoard[nextRow][nextCol]][microBoard[r][c]] = true;

					score += (microSize[microBoard[r][c]] * microSize[microBoard[nextRow][nextCol]]);
				}
			}
		}
		builder.append(score).append('\n');
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		containerSize = Integer.parseInt(tokenizer.nextToken());
		researchCount = Integer.parseInt(tokenizer.nextToken());

		for(int researchNo = 0; researchNo <researchCount; researchNo++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int startRow = Integer.parseInt(tokenizer.nextToken());
			int startCol = Integer.parseInt(tokenizer.nextToken());
			int endRow = Integer.parseInt(tokenizer.nextToken());
			int endCol = Integer.parseInt(tokenizer.nextToken());

			intsertMicroOrganisms(startRow, startCol, endRow, endCol, researchNo + 1);
			relocateMicroOrganisms(researchNo + 1);
			calculation();
		}

		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void solution() throws IOException {
		init();
	}

	public static void main(String[] args) throws IOException {
		new MicrobalResearch_2().solution();
	}

}
