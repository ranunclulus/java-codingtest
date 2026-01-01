package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

public class RudolphRebellion{
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int[][] deltas = new int[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static int N, M, P, C, D;
	static int[] rudolph;
	static Santa[] santas;

	class Santa implements Comparable<Santa>{

		int number;
		int row;
		int col;
		int score;
		int active;
		boolean out;

		Santa(int number, int row, int col) {
			this.number = number;
			this.row = row;
			this.col = col;
			this.active = 0;
			this.out = false;
		}

		@Override
		public int compareTo(Santa o) {
			return Integer.compare(this.number, o.number);
		}

		@Override
		public String toString() {
			return String.format("Santa %d [r=%d, c=%d, score=%d, active=%d, out=%s]",
				number, row+1, col+1, score, active, out);
		}
	}

	class MoveRudolphNode implements Comparable<MoveRudolphNode> {
		int row;
		int col;
		int distance;
		int direction;

		MoveRudolphNode(int row, int santaCol, int distance, int direction) {
			this.row = row;
			this.col = santaCol;
			this.distance = distance;
			this.direction = direction;
		}

		@Override
		public int compareTo(MoveRudolphNode o) {
			if (this.distance == o.distance) {
				if (this.row == o.row) {
					return -Integer.compare(this.col,  o.col);
				} else return -Integer.compare(this.row,  o.row);
			}
			else return Integer.compare(this.distance,  o.distance);
		}
	}

	class MoveSantaNode implements Comparable<MoveSantaNode> {
		int row;
		int col;
		int distance;
		int direction;

		MoveSantaNode(int row, int santaCol, int distance, int direction) {
			this.row = row;
			this.col = santaCol;
			this.distance = distance;
			this.direction = direction;
		}

		@Override
		public int compareTo(MoveSantaNode o) {
			if (this.distance == o.distance) {
				return Integer.compare(this.direction,  o.direction);
			}
			else return Integer.compare(this.distance,  o.distance);
		}
	}

	public void solution() throws IOException {
		init();

		for(int game = 0; game < M; game++) {
			System.out.println("=== ROUND " + (game+1) + " START ===");
			printSantas();

			resetSanta();
			moveRudolph();
			printSantas();

			if (isAllOut()) break;
			moveSanta();
			printSantas();

			if (isAllOut()) break;
			finishSanta();
			printSantas();

			if (isAllOut()) break;
			System.out.println("=== ROUND " + (game+1) + " END ===\n");
		}
		print();
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void resetSanta() {
		for(int i = 0; i < P; i++) {
			if(santas[i].out) continue;
			if (santas[i].active == 0) continue;
			else if (santas[i].active > 0) santas[i].active--;
		}
	}

	public void moveRudolph() {
		PriorityQueue<MoveRudolphNode> santaPQ = new PriorityQueue<>();
		for (int i = 0; i < P; i++) {
			if (santas[i].out) continue;
			int distance = Math.abs(santas[i].row - rudolph[0]) * Math.abs(santas[i].row - rudolph[0])
				+ Math.abs(santas[i].col - rudolph[1]) * Math.abs(santas[i].col - rudolph[1]);

			santaPQ.offer(new MoveRudolphNode(santas[i].row, santas[i].col, distance, -1));
		}

		MoveRudolphNode target = santaPQ.poll();
		System.out.printf("루돌프 목표 산타 위치: (%d,%d), 거리=%d\n", target.row+1, target.col+1, target.distance);

		PriorityQueue<MoveRudolphNode> rudolphPQ = new PriorityQueue<>();
		for(int i = 0; i < 8; i++) {
			int nextRow = rudolph[0] + deltas[i][0];
			int nextCol = rudolph[1] + deltas[i][1];

			if (!inRange(nextRow, nextCol)) continue;

			int distance = Math.abs(target.row - nextRow) * Math.abs(target.row - nextRow)
				+ Math.abs(target.col - nextCol) * Math.abs(target.col - nextCol);

			rudolphPQ.add(new MoveRudolphNode(nextRow, nextCol, distance, i));
		}

		MoveRudolphNode move = rudolphPQ.poll();
		System.out.printf("루돌프 이동: (%d,%d) -> (%d,%d)\n", rudolph[0]+1, rudolph[1]+1, move.row+1, move.col+1);
		rudolph[0] = move.row;
		rudolph[1] = move.col;

		for(int i = 0; i < P; i++) {
			if (santas[i].out) continue;
			if (rudolph[0] == santas[i].row && rudolph[1] == santas[i].col) {
				System.out.printf("루돌프 충돌! Santa %d 충돌 지점 (%d,%d)\n", santas[i].number, rudolph[0]+1, rudolph[1]+1);
				santas[i].score += C;
				int nextRow = santas[i].row + deltas[move.direction][0] * C;
				int nextCol = santas[i].col + deltas[move.direction][1] * C;

				if (!inRange(nextRow, nextCol)) {
					System.out.printf("Santa %d OUT\n", santas[i].number);
					santas[i].out = true;
				}
				else {
					santas[i].row = nextRow;
					santas[i].col = nextCol;
					santas[i].active = 2;
					chainEffect(nextRow, nextCol, move.direction, santas[i].number);
				}
			}
		}
	}

	public void moveSanta() {
		for(int i = 0; i < P; i++) {
			if (santas[i].out || santas[i].active != 0) continue;

			int originalDistance = Math.abs(santas[i].row - rudolph[0]) * Math.abs(santas[i].row - rudolph[0])
				+ Math.abs(santas[i].col - rudolph[1]) * Math.abs(santas[i].col - rudolph[1]);

			PriorityQueue<MoveSantaNode> pq = new PriorityQueue<>();
			for(int direction : new int[] {0, 2, 4, 6}) {
				int nextRow = santas[i].row + deltas[direction][0];
				int nextCol = santas[i].col + deltas[direction][1];

				if (!inRange(nextRow, nextCol)) continue;
				if (inSanta(nextRow, nextCol)) continue;

				int newDistance = Math.abs(nextRow - rudolph[0]) * Math.abs(nextRow - rudolph[0])
					+ Math.abs(nextCol - rudolph[1]) * Math.abs(nextCol - rudolph[1]);

				if (newDistance >= originalDistance) continue;

				pq.add(new MoveSantaNode(nextRow, nextCol, newDistance, direction));
			}
			if (pq.isEmpty()) continue;
			MoveSantaNode target = pq.poll();
			System.out.printf("Santa %d 이동: (%d,%d) -> (%d,%d)\n",
				santas[i].number, santas[i].row+1, santas[i].col+1, target.row+1, target.col+1);

			santas[i].row = target.row;
			santas[i].col = target.col;

			if (rudolph[0] == santas[i].row && rudolph[1] == santas[i].col) {
				System.out.printf("Santa %d 루돌프와 충돌!\n", santas[i].number);
				santas[i].score += D;
				int nextRow = santas[i].row + deltas[(target.direction + 4) % 8][0] * D;
				int nextCol = santas[i].col + deltas[(target.direction + 4) % 8][1] * D;

				if (!inRange(nextRow, nextCol)) {
					System.out.printf("Santa %d OUT\n", santas[i].number);
					santas[i].out = true;
				}
				else {
					santas[i].row = nextRow;
					santas[i].col = nextCol;
					santas[i].active = 2;
					chainEffect(nextRow, nextCol, (target.direction + 4) % 8, santas[i].number);
				}
			}
		}
	}

	public void chainEffect(int row, int col, int direction, int santaNumber) {
		if (!inRange(row, col)) return;

		Santa target = null;
		for(int i = 0; i < P; i++) {
			if (santas[i].number == santaNumber) continue;
			if (santas[i].row == row && santas[i].col == col) target = santas[i];
		}

		if (target == null) return;

		System.out.printf("체인 충돌 발생! Santa %d 이동\n", target.number);

		int nextRow = row + deltas[direction][0];
		int nextCol = col + deltas[direction][1];

		if (inRange(nextRow, nextCol)) {
			target.row = nextRow;
			target.col = nextCol;
			chainEffect(nextRow, nextCol, direction, target.number);
		} else {
			System.out.printf("Santa %d OUT (체인)\n", target.number);
			target.out = true;
		}
	}

	public void finishSanta() {
		for(int i = 0; i < P; i++) {
			if (santas[i].out) continue;
			santas[i].score++;
		}
		System.out.println("라운드 종료 후 점수 1점 부여 완료");
	}

	public boolean inRange(int row, int col) {
		return ((row >= 0) && (row < N) && (col >= 0) && (col < N));
	}

	public boolean inSanta(int row, int col) {
		for(int i = 0; i < P; i++) {
			if (santas[i].out) continue;
			if (santas[i].row == row && santas[i].col == col) return true;
		}
		return false;
	}

	public boolean isAllOut() {
		for(int i = 0; i < P; i++) {
			if (!santas[i].out) return false;
		}
		return true;
	}

	public void print() {
		PriorityQueue<Santa> pq = new PriorityQueue<>();
		for(int i = 0; i < P; i++) {
			pq.offer(santas[i]);
		}

		while(!pq.isEmpty()){
			builder.append(pq.poll().score).append(' ');
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
		rudolph = new int[2];
		rudolph[0] = Integer.parseInt(tokenizer.nextToken()) - 1;
		rudolph[1] = Integer.parseInt(tokenizer.nextToken()) - 1;

		santas = new Santa[P];
		for(int i = 0; i < P; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			santas[i] = new Santa(Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken()) - 1,
				Integer.parseInt(tokenizer.nextToken()) - 1);
		}
		Arrays.sort(santas);
	}

	public void printSantas() {
		System.out.printf("루돌프 현재 위치: (%d,%d)\n", rudolph[0]+1, rudolph[1]+1);
		for (Santa s : santas) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) throws IOException {
		new RudolphRebellion().solution();
	}
}