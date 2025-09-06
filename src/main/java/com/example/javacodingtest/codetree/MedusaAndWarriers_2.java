package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

public class MedusaAndWarriers_2{
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, M;
	static int[][] map;
	static Point medusa, park;
	static List<Point> root;
	static Point[] warriers;
	static int[][] deltas = new int[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static int[][] sight;
	static int[][] warriersPosition;
	static int moveCount, stoneCount, attackCount;
	public boolean canMove = false;

	class Point {

		int row;
		int col;
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public void solution() throws IOException {
		init();
		findFastPathToPark();

		if (root.size() > 0) {
			for(int i = root.size() - 1; i > 0; i--) {
				removeWarriers(root.get(i));
				getWarriersPosition();
				for(int dir : new int[] {0, 4, 6, 2}) {
					makeStone(dir);
				}
				moveCount = 0;
				attackCount = 0;
				moveWarriers();
				builder.append(moveCount).append(' ').append(stoneCount).append(' ').append(attackCount).append('\n');
			}
			builder.append(0);
		} else {
			builder.append(-1);
		}

		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	// 공원까지의 최단 루트 찾기
	public void findFastPathToPark() {
		root = new ArrayList<>();

		Point[][] prev = new Point[N][N];
		Deque<Point> toVisit = new ArrayDeque<>();

		prev[medusa.row][medusa.col] = new Point(-1, -1);
		toVisit.add(new Point(medusa.row, medusa.col));

		while(!toVisit.isEmpty()) {
			Point now = toVisit.poll();

			if (now.row == park.row && now.col == park.col) {
				root.add(new Point(now.row, now.col));
				while (true) {
					now = prev[now.row][now.col];
					if (now.row == medusa.row && now.col == medusa.col) break;
					root.add(new Point(now.row, now.col));
				}
				break;
			}

			for(int dir : new int[] {0, 4, 6, 2}) {
				int nextRow = now.row + deltas[dir][0];
				int nextCol = now.col + deltas[dir][1];

				if (!inRange(nextRow, nextCol)) continue;
				if (map[nextRow][nextCol] == 1) continue;
				if (prev[nextRow][nextCol] != null) continue;

				toVisit.add(new Point(nextRow, nextCol));
				prev[nextRow][nextCol] = new Point(now.row, now.col);
			}
		}

	}

	// 메두사 이동시키고 전사 죽이기
	public void removeWarriers(Point nextMove) {
		medusa.row = nextMove.row;
		medusa.col = nextMove.col;

		for(int i = 0; i < M; i++) {
			if (warriers[i] == null) continue;
			if (warriers[i].row == medusa.row && warriers[i].col == medusa.col) {
				warriers[i] = null;
			}
		}
	}

	// 각 칸마다 전사들이 얼마나 위치하고 있는지 반환
	public void getWarriersPosition() {
		sight = new int[N][N];
		warriersPosition = new int[N][N];
		stoneCount = -1;

		for(int i = 0; i < M; i++) {
			if (warriers[i] == null) continue;
			warriersPosition[warriers[i].row][warriers[i].col]++;
		}
	}

	// 메두사에 시선에 따라 돌로 만들기
	public void makeStone(int direction) {
		int[][] tempSight = new int[N][N];
		int tempStoneCount = 0;

		int nextRow = medusa.row + deltas[direction][0];
		int nextCol = medusa.col + deltas[direction][1];

		while (inRange(nextRow, nextCol)) {
			tempSight[nextRow][nextCol] = 1;
			if (warriersPosition[nextRow][nextCol] > 0) {
				tempStoneCount += warriersPosition[nextRow][nextCol];
				nextRow += deltas[direction][0];
				nextCol += deltas[direction][1];
				makeLine(tempSight, nextRow, nextCol, direction);
				break;
			}
			nextRow += deltas[direction][0];
			nextCol += deltas[direction][1];
		}

		for(int nextDirection : new int[] {(direction + 7) % 8, (direction + 1) % 8}) {
			int diagonalRow = medusa.row + deltas[nextDirection][0];
			int diagonalCol = medusa.col + deltas[nextDirection][1];

			while (inRange(diagonalRow, diagonalCol)) {

				if (tempSight[diagonalRow][diagonalCol] == 0 && warriersPosition[diagonalRow][diagonalCol] > 0) {
					tempSight[diagonalRow][diagonalCol] = 1;
					tempStoneCount += warriersPosition[diagonalRow][diagonalCol];
					makeSafe(tempSight, diagonalRow, diagonalCol, direction, nextDirection);
					break;
				}
				int nextDiagonalRow = diagonalRow;
				int nextDiagonalCol = diagonalCol;
				while (inRange(nextDiagonalRow, nextDiagonalCol)) {
					if (tempSight[nextDiagonalRow][nextDiagonalCol] == 0) {
						tempSight[nextDiagonalRow][nextDiagonalCol] = 1;
						if (warriersPosition[nextDiagonalRow][nextDiagonalCol] > 0) {
							tempStoneCount += warriersPosition[nextDiagonalRow][nextDiagonalCol];
							makeSafe(tempSight, nextDiagonalRow, nextDiagonalCol, direction, nextDirection);
							break;
						}
					} else break;
					nextDiagonalRow += deltas[direction][0];
					nextDiagonalCol += deltas[direction][1];
				}
				diagonalRow += deltas[nextDirection][0];
				diagonalCol += deltas[nextDirection][1];
			}
		}

		if (tempStoneCount > stoneCount) {
			stoneCount = tempStoneCount;
			sight = tempSight;
		}
	}

	// 한 시선을 safe로 만드는 것
	public void makeSafe(int[][] tempSight, int row, int col, int direction, int nextDirection) {
		int nextRow = row + deltas[direction][0];
		int nextCol = col + deltas[direction][1];
		makeLine(tempSight, nextRow, nextCol, direction);

		nextRow = row + deltas[nextDirection][0];
		nextCol = col + deltas[nextDirection][1];
		while (inRange(nextRow, nextCol)) {
			makeLine(tempSight, nextRow, nextCol, direction);
			nextRow += deltas[nextDirection][0];
			nextCol += deltas[nextDirection][1];
		}
	}


	// 한 줄을 safe로 만드는 것
	public void makeLine(int[][] tempSight, int row, int col, int direction) {
		while (inRange(row, col)) {
			tempSight[row][col] = 2;
			row += deltas[direction][0];
			col += deltas[direction][1];
		}
	}

	// 전사들 이동
	public void moveWarriers() {
		for (int[] directions : new int[][] {{0, 4, 6, 2}, {6, 2, 0, 4}}) {
			for(int i = 0; i < M; i++) {
				if (warriers[i] == null) continue;
				if (sight[warriers[i].row][warriers[i].col] == 1) continue;

				for(int direction : directions) {
					int nextRow = warriers[i].row + deltas[direction][0];
					int nextCol = warriers[i].col + deltas[direction][1];

					if (!inRange(nextRow, nextCol)) continue;
					if (sight[nextRow][nextCol] == 1) continue;

					int originalDistance = Math.abs(medusa.row - warriers[i].row) + Math.abs(medusa.col - warriers[i].col);
					int nextDistance = Math.abs(medusa.row - nextRow) + Math.abs(medusa.col - nextCol);

					if (nextDistance >= originalDistance) continue;

					moveCount++;

					if (nextRow == medusa.row && nextCol == medusa.col) {
						attackCount++;
						warriers[i] = null;
					} else {
						warriers[i].row = nextRow;
						warriers[i].col = nextCol;
					}
					break;
				}
			}
		}
	}

	public boolean inRange(int row, int col) {
		return ((row >= 0) && (row < N) && (col >= 0) && (col < N));
	}

	// 입력값 초기화
	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		tokenizer = new StringTokenizer(reader.readLine());
		medusa = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
		park = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));

		tokenizer = new StringTokenizer(reader.readLine());
		warriers = new Point[M];
		for(int i = 0; i < M; i++) {
			warriers[i] = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
		}

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new MedusaAndWarriers_2().solution();
	}

}
