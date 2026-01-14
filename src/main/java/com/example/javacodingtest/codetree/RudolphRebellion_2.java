package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2026.01.14
 @link https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/rudolph-rebellion/description
 @timecomplex
 @performance
 @category
 @note
 */
public class RudolphRebellion_2 {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, M, P, C, D;
	static int rudolphRow, rudolphCol;
	static Santa[] santas;
	static int[][] map;
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	static RudolphMoveNode rudolphMoveNode;

	class Santa {
		int num;
		int row;
		int col;
		int score;
		boolean isAlive;
		int locked;

		Santa(int num, int row, int col) {
			this.num = num;
			this.row = row;
			this.col = col;
			this.score = 0;
			this.isAlive = true;
			this.locked = 0;
			// K 번째 턴에서 기절하면 1
			// K 번째 턴 마무리 2
			// K + 1 번째 턴 시작 2
			// K + 1 번째 턴 마무리 3
		}

	}

	class RudolphMoveNode implements Comparable<RudolphMoveNode>{
		int distance;
		int santaRow;
		int santaCol;
		int nextRow;
		int nextCol;
		int direction;
		int moveDistance;

		RudolphMoveNode(int distance, int santaRow, int santaCol, int nextRow, int nextCol, int direction, int moveDistance) {
			this.distance = distance;
			this.santaRow = santaRow;
			this.santaCol = santaCol;
			this.nextRow = nextRow;
			this.nextCol = nextCol;
			this.direction = direction;
			this.moveDistance = moveDistance;
		}

		@Override
		public int compareTo(RudolphMoveNode o) {
			if (this.distance == o.distance) {
				if (this.santaRow == o.santaRow) {
					if (this.santaCol == o.santaCol) return Integer.compare(this.moveDistance, o.moveDistance);
					return -Integer.compare(this.santaCol, o.santaCol);
				}
				return -Integer.compare(this.santaRow, o.santaRow);
			}
			return Integer.compare(this.distance, o.distance);
		}
	}

	public void solution() throws IOException {
		init();
		while(M --> 0) {
			wakeUp();
			rudolphMove();
			santasMove();
			if (allDie()) break;
			gainPoint();
		}

		for (int i = 0; i < P; i++) {
			builder.append(santas[i].score).append(' ');
		}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void gainPoint() {
		for (int i = 0; i < P; i++) {
			if (santas[i].isAlive) santas[i].score++;
			if (santas[i].locked != 0) {
				santas[i].locked++;
			}
		}
	}

	public void wakeUp() {
		for (int i = 0; i < P; i++) {
			if (santas[i].locked == 3) santas[i].locked = 0;
		}
	}

	public boolean allDie() {
		for (int i = 0; i < P; i++) {
			if (santas[i].isAlive) return false;
		}
		return true;
	}

	public void rudolphMove() {
		PriorityQueue<RudolphMoveNode> closeSanta = new PriorityQueue<>();

		for (int i = 0; i < P; i++) {
			Santa santa = santas[i];

			if (!santa.isAlive) continue;

			for (int j = 0; j < 8; j++) {
				int nextRow = rudolphRow + deltas[j][0];
				int nextCol = rudolphCol + deltas[j][1];
				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				int distance = (int) (Math.pow((rudolphRow - santa.row), 2) + Math.pow((rudolphCol - santa.col), 2));
				int moveDistance = (int) (Math.pow((nextRow - santa.row), 2) + Math.pow((nextCol - santa.col), 2));

				closeSanta.add(new RudolphMoveNode(distance, santa.row, santa.col, nextRow, nextCol, j, moveDistance));
			}
		}

		rudolphMoveNode = closeSanta.poll();
		
		if (map[rudolphMoveNode.nextRow][rudolphMoveNode.nextCol] != 0) {
			Santa santa = santas[map[rudolphMoveNode.nextRow][rudolphMoveNode.nextCol] - 1];
			santa.score += C;
			santa.locked = 1;
			
			int nextSantaRow = santa.row + deltas[rudolphMoveNode.direction][0] * C;
			int nextSantaCol = santa.col + deltas[rudolphMoveNode.direction][1] * C;
			
			if (nextSantaRow < 0 || nextSantaRow >= N || nextSantaCol < 0 || nextSantaCol >= N) {
				santa.isAlive = false;
				map[santa.row][santa.col] = 0;
			} else {
				map[santa.row][santa.col] = 0;
				rebellion(santa, nextSantaRow, nextSantaCol, rudolphMoveNode.direction);
				santa.row = nextSantaRow;
				santa.col = nextSantaCol;
				map[santa.row][santa.col] = santa.num;
			}
		}
		
		map[rudolphRow][rudolphCol] = 0;
		rudolphRow = rudolphMoveNode.nextRow;
		rudolphCol = rudolphMoveNode.nextCol;
		map[rudolphRow][rudolphCol] = -1;

	}

	public void rebellion(Santa santa, int nextSantaRow, int nextSantaCol, int direction) {
		if (map[nextSantaRow][nextSantaCol] <= 0) return;
		Santa previous = santas[map[nextSantaRow][nextSantaCol] - 1];
		
		int previousRow = previous.row + deltas[direction][0];
		int previousCol = previous.col + deltas[direction][1];
		
		if (previousRow < 0 || previousRow >= N || previousCol < 0 || previousCol >= N) {
			previous.isAlive = false;
			map[previous.row][previous.col] = 0;

		} else {
			map[previous.row][previous.col] = 0;
			if (map[previousRow][previousCol] > 0 )rebellion(previous, previousRow, previousCol, direction);
			previous.row = previousRow;
			previous.col = previousCol;
			map[previous.row][previous.col] = previous.num;
		}
	}

	public void santasMove() {
		for (int i = 0; i < P; i++) {
			Santa santa = santas[i];

			if (!santa.isAlive) continue;
			if (santa.locked != 0) continue;

			int nextSantaRow = -1;
			int nextSantaCol = -1;
			int moveDirection = -1;
			int distance = (rudolphRow - santa.row) * (rudolphRow - santa.row) + (rudolphCol - santa.col) * (rudolphCol - santa.col);
			for (int j = 0; j < 4; j++) {
				int nextRow = santa.row + deltas[j][0];
				int nextCol = santa.col + deltas[j][1];
				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (map[nextRow][nextCol] > 0) continue;
				int newDistance = (rudolphRow - nextRow) * (rudolphRow - nextRow) + (rudolphCol - nextCol) * (rudolphCol - nextCol);

				if (distance <= newDistance) continue;

				distance = newDistance;
				nextSantaRow = nextRow;
				nextSantaCol = nextCol;
				moveDirection = j;

			}

			if (nextSantaRow != -1 && nextSantaCol != -1) {
				if (map[nextSantaRow][nextSantaCol] != 0) {
					if (map[nextSantaRow][nextSantaCol] == -1) santa.locked = 1;
					santa.score += D;
					if (moveDirection == 0) moveDirection = 2;
					else if (moveDirection == 1) moveDirection = 3;
					else if (moveDirection == 2) moveDirection = 0;
					else moveDirection = 1;
					
					nextSantaRow += deltas[moveDirection][0] * D;
					nextSantaCol += deltas[moveDirection][1] * D;
					
					if (nextSantaRow < 0 || nextSantaRow >= N || nextSantaCol < 0 || nextSantaCol >= N) {
						santa.isAlive = false;
						map[santa.row][santa.col] = 0;
					} else {
						map[santa.row][santa.col] = 0;
						rebellion(santa, nextSantaRow, nextSantaCol, moveDirection);
						santa.row = nextSantaRow;
						santa.col = nextSantaCol;
						map[santa.row][santa.col] = santa.num;
					}
				} else {
					map[santa.row][santa.col] = 0;
					santa.row = nextSantaRow;
					santa.col = nextSantaCol;
					map[santa.row][santa.col] = santa.num;
				}
			}
		}
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		P = Integer.parseInt(tokenizer.nextToken());
		C = Integer.parseInt(tokenizer.nextToken());
		D = Integer.parseInt(tokenizer.nextToken());

		tokenizer = new StringTokenizer(reader.readLine());
		rudolphRow = Integer.parseInt(tokenizer.nextToken()) - 1;
		rudolphCol = Integer.parseInt(tokenizer.nextToken()) - 1;

		santas = new Santa[P];
		for (int i = 0; i < P; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int num = Integer.parseInt(tokenizer.nextToken());
			int row = Integer.parseInt(tokenizer.nextToken()) - 1;
			int col = Integer.parseInt(tokenizer.nextToken()) - 1;
			santas[i] = new Santa(num, row, col);
		}
		Arrays.sort(santas, new Comparator<Santa>() {
			@Override
			public int compare(Santa o1, Santa o2) {
				return Integer.compare(o1.num, o2.num);
			}
		});

		map = new int[N][N];
		map[rudolphRow][rudolphCol] = -1;
		for (int i = 0; i < P; i++) {
			map[santas[i].row][santas[i].col] = santas[i].num;
		}

	}

	public static void main(String[] args) throws IOException {
		new RudolphRebellion_2().solution();
	}
}