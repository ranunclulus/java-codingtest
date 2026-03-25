package com.example.javacodingtest.codetree;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OddDartGame {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, Q;
	static Circle[] circles;
	static Movement[] movements;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	class Circle {
		int[] numbers;

		Circle() {
			this.numbers = new int[M];
		}

		public void turnLeft(int gap) {
			for(int i = 0; i < gap; i++) {
				int head = this.numbers[0];

				for(int j = 1; j < M; j++) {
					this.numbers[j - 1] = this.numbers[j];
				}
				this.numbers[M - 1] = head;
			}
		}

		public void turnRight(int gap) {
			for(int i = 0; i < gap; i++) {
				int tail = this.numbers[M - 1];

				for(int j = M - 1; j > 0; j--) {
					this.numbers[j] = this.numbers[j - 1];
				}

				this.numbers[0] = tail;
			}
		}
	}

	class Movement {
		int round;
		int direction;
		int gap;

		Movement(int round, int direction, int gap) {
			this.round = round;
			this.direction = direction;
			this.gap = gap;
		}
	}

	public void solution() throws IOException {
		init();

		for(int i = 0; i < Q; i++) {
			Movement movement = movements[i];
			for(int j = 0; j < N; j++) {
				if ((j + 1) % movement.round != 0) continue;
				if (movement.direction == 0) {
					circles[j].turnRight(movement.gap);
				} else {
					circles[j].turnLeft(movement.gap);
				}
			}
			remove();
		}

		builder.append(getSum());
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void remove() {
		boolean[][] checked = new boolean[N][M];
		boolean isSame = false;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int num = circles[i].numbers[j];
				if (num == 0) continue;

				for(int[] delta : deltas) {
					int nextCircle = i + delta[0];
					int nextNumber = (j + delta[1] + M) % M;

					if (nextCircle < 0 || nextCircle >= N) continue;
					if (circles[nextCircle].numbers[nextNumber] != num) continue;

					checked[i][j] = true;
					checked[nextCircle][nextNumber] = true;
					isSame = true;
				}
			}
		}

		if (isSame) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if (checked[i][j]) {
						circles[i].numbers[j] = 0;
					}
				}
			}
		} else {
			int totalSum = 0;
			int totalCount = 0;

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if (circles[i].numbers[j] != 0) {
						totalSum += circles[i].numbers[j];
						totalCount++;
					}
				}
			}

			if (totalCount != 0) {
				int avgSum = totalSum / totalCount;
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < M; j++) {
						if (circles[i].numbers[j] != 0) {
							if (circles[i].numbers[j] > avgSum) circles[i].numbers[j]--;
							if (circles[i].numbers[j] < avgSum) circles[i].numbers[j]++;
						}
					}
				}
			}
		}
	}

	public int getSum() {
		int totalSum = 0;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if (circles[i].numbers[j] != 0) {
					totalSum += circles[i].numbers[j];
				}
			}
		}

		return totalSum;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		Q = Integer.parseInt(tokenizer.nextToken());

		circles = new Circle[N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			circles[i] = new Circle();
			for(int j = 0; j < M; j++) {
				circles[i].numbers[j] = Integer.parseInt(tokenizer.nextToken());
			}
		}

		movements = new Movement[Q];
		for(int i = 0; i < Q; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int round = Integer.parseInt(tokenizer.nextToken());
			int direction = Integer.parseInt(tokenizer.nextToken());
			int gap = Integer.parseInt(tokenizer.nextToken());

			movements[i] = new Movement(round, direction, gap);
		}
	}

	public static void main(String[] args) throws IOException {
		new OddDartGame().solution();
	}

}