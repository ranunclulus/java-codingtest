package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

/*
 @author ranuinclulus
 @since 2025.04.05
 @link https://www.codetree.ai/ko/frequent-problems/problems/medusa-and-warriors/submissions?page=1&page_size=20&introductionSetId=&bookmarkId=
 @timecomplex
 @performance
 @category
 @note
 */
public class MedusaAndWorriers {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	StringTokenizer tokenizer;
	StringBuilder builder = new StringBuilder();


	public int N, M;
	public int[][] map;
	public Point medusa, park;
	public Point[] worriers;
	public List<Point> root;
	public int[][] deltas = new int[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	public int[][] sight;
	public int moveCount, stoneCount, attackCount;
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
		root = findRootToPark(); // 메두사가 공원으로 이동하는 경로 구하기

		if (!canMove) {
			builder.append(-1);
		} else {
			for(int i = root.size() - 1; i >= 0; i--) {
				Point moved = root.get(i);
				removeWorrier(moved);

				sight = new int[N][N]; // 메두사가 보는 시야
				stoneCount = -1; // 돌로 변하는 전사의 최대 숫자
				int[][] worriersPosition = getWorriersPosition();
				for (int direction : new int[] {0, 4, 6, 2}) {
					makeStone(moved, direction, worriersPosition);
				}

				moveCount = 0;
				attackCount = 0;
				moveWorriers(moved);
				builder.append(moveCount).append(" ").append(stoneCount).append(" ").append(attackCount).append(" ").append('\n');
			}
			builder.append(0);
		}

		writer.write(builder.toString());
		writer.flush();
	}

	private void moveWorriers(Point moved) {
		for(int[] directions : new int[][] {{0, 4, 6, 2}, {6, 2, 0, 4}}) {
			for(int i = 0; i < M; i++) {
				if (worriers[i] == null) continue;
				if (sight[worriers[i].row][worriers[i].col] == 1) continue; // 돌

				int distance = Math.abs(moved.row - worriers[i].row) + Math.abs(moved.col - worriers[i].col);
				for(int direction : directions) {
					int nextRow = worriers[i].row + deltas[direction][0];
					int nextCol = worriers[i].col + deltas[direction][1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
					if (sight[nextRow][nextCol] == 1) continue;
					if ((Math.abs(moved.row - nextRow) + Math.abs(moved.col - nextCol)) >= distance) continue;

					moveCount++;
					if (nextRow == moved.row && nextCol == moved.col) {
						attackCount++;
						worriers[i] = null;
					} else {
						worriers[i].row = nextRow;
						worriers[i].col = nextCol;
					}
					break;
				}
			}
		}
	}

	private int[][] getWorriersPosition() {
		int[][] positions = new int[N][N];

		for(int i = 0; i < M; i++) {
			if (worriers[i] == null) continue;
			positions[worriers[i].row][worriers[i].col]++;
		}
		return positions;
	}

	private void makeStone(Point moved, int direction, int[][] worriersPosition) {
		int[][] tempSight = new int[N][N];
		int tempStoneCount = 0;

		int nextRow = moved.row + deltas[direction][0];
		int nextCol = moved.col + deltas[direction][1];

		while (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
			tempSight[nextRow][nextCol] = 1;
			if (worriersPosition[nextRow][nextCol] > 0) { // 전사가 있을 경우
				tempStoneCount += worriersPosition[nextRow][nextCol];
				nextRow += deltas[direction][0];
				nextCol += deltas[direction][1];
				makeLine(tempSight, nextRow, nextCol, direction);
				break;
			}
			// 전사가 없을 경우 그 라인 그대로 진행
			nextRow += deltas[direction][0];
			nextCol += deltas[direction][1];
		}

		// 라인을 채웠으면 이제 대각선 방향
		for(int nextDirection : new int[] {((direction + 7) % 8), ((direction + 1) % 8)}) {
			int diagonalRow = moved.row + deltas[nextDirection][0];
			int diagonalCol = moved.col + deltas[nextDirection][1];

			while (diagonalRow >= 0 && diagonalRow < N && diagonalCol >= 0 && diagonalCol < N) {
				if (tempSight[diagonalRow][diagonalCol] == 0 && worriersPosition[diagonalRow][diagonalCol] > 0) { // 대각선에 전사가 있으면
					tempSight[diagonalRow][diagonalCol] = 1;
					tempStoneCount += worriersPosition[diagonalRow][diagonalCol];
					makeSafe(tempSight, diagonalRow, diagonalCol, direction, nextDirection);
					break;
				}

				int nextDiagonalRow = diagonalRow;
				int nextDiagonalCol = diagonalCol;

				while (nextDiagonalRow >= 0 && nextDiagonalRow < N && nextDiagonalCol >= 0 && nextDiagonalCol < N) {
					if (tempSight[nextDiagonalRow][nextDiagonalCol] == 0) {
						tempSight[nextDiagonalRow][nextDiagonalCol] = 1;
						if (worriersPosition[nextDiagonalRow][nextDiagonalCol] > 0) {
							tempStoneCount += worriersPosition[nextDiagonalRow][nextDiagonalCol];
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

	private void makeSafe(int[][] tempSight, int row, int col, int direction, int nextDirection) {
		int nextRow = row + deltas[direction][0];
		int nextCol = col + deltas[direction][1];
		makeLine(tempSight, nextRow, nextCol, direction);

		nextRow = row + deltas[nextDirection][0];
		nextCol = col + deltas[nextDirection][1];
		while (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
			makeLine(tempSight, nextRow, nextCol, direction);
			nextRow += deltas[nextDirection][0];
			nextCol += deltas[nextDirection][1];
		}
	}

	private void makeLine(int[][] tempSight, int row, int col, int direction) {
		while (row >= 0 && row < N && col >= 0 && col < N) {
			tempSight[row][col] = 2; // 시선에서 제외되는 곳은 2로 표시
			row += deltas[direction][0];
			col += deltas[direction][1];
		}
	}

	private void removeWorrier(Point moved) {
		for(int i = 0; i < M; i++) {
			if (worriers[i] == null) continue;
			if (worriers[i].row == moved.row && worriers[i].col == moved.col) {
				worriers[i] = null;
			}
		}
	}

	private List<Point> findRootToPark() {
		List<Point> root = new ArrayList<>();

		Deque<Point> toVisit = new ArrayDeque<>();
		Point[][] previous = new Point[N][N];

		toVisit.offer(new Point(medusa.row, medusa.col));
		previous[medusa.row][medusa.col] = new Point(medusa.row, medusa.col);

		while (!toVisit.isEmpty()) {
			Point now = toVisit.poll();

			if (now.row == park.row && now.col == park.col) {
				now = previous[park.row][park.col];
				if (!(now.row == medusa.row && now.col == medusa.col)) root.add(now);

				while (!(previous[now.row][now.col].row == medusa.row && previous[now.row][now.col].col == medusa.col)) {
					now = previous[now.row][now.col];
					if (!(now.row == medusa.row && now.col == medusa.col)) root.add(now);
				}
				canMove = true;
				return root;
			}

			for (int direction : new int[] {0, 4, 6, 2}) {
				int nextRow = now.row + deltas[direction][0];
				int nextCol = now.col + deltas[direction][1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (previous[nextRow][nextCol] != null) continue;
				if (map[nextRow][nextCol] == 1) continue;

				toVisit.offer(new Point(nextRow, nextCol));
				previous[nextRow][nextCol] = new Point(now.row, now.col);
			}
		}
		return root;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		worriers = new Point[M];

		tokenizer = new StringTokenizer(reader.readLine());
		medusa = new Point (Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
		park = new Point (Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));

		tokenizer = new StringTokenizer(reader.readLine());
		for(int i = 0; i < M; i++) {
			worriers[i] = new Point (Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
		}

		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new MedusaAndWorriers().solution();
	}
}


/*
4 4
1 3 3 3
3 1 0 3 1 0 2 2
0 0 0 0
0 0 0 0
0 0 1 1
1 0 0 0

4 2 2
0 2 0
1 1 1
0 0 0
0 0 0
0
 */