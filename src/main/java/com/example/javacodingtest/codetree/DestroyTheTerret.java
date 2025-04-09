package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.09
 @link https://www.codetree.ai/ko/frequent-problems/problems/destroy-the-turret/description
 @timecomplex
 @performance 
 @category
 @note
 */
public class DestroyTheTerret {
	class Terret implements Comparable<Terret> {
		int row;
		int col;
		int power;
		int recentAttack;

		Terret(int row, int col, int power) {
			this.row = row;
			this.col = col;
			this.power = power;
			this.recentAttack = 0;
		}

		@Override
		public int compareTo(Terret o) {
			if (this.power == o.power) {
				 if (this.recentAttack == o.recentAttack) {
					 if ((this.row + this.col) == (o.row + o.col)) {
						 return -Integer.compare(this.col, o.col); // 열 값이 가장 큰 애
					 }
					 return -Integer.compare((this.row + this.col), (o.row + o.col)); // 행과 열의 합이 가장 큰 애
				 }
				 return -Integer.compare(this.recentAttack, o.recentAttack); // 가장 최근에 공격한 애
			}
			return Integer.compare(this.power, o.power); // 공격력이 가장 약한 애
		}
	}

	class Point {
		int row;
		int col;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static PriorityQueue<Terret> maxHeap;
	static PriorityQueue<Terret> minHeap;

	static int N, M, K, answer;
	static Terret[][] map;
	static int[][] deltas = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; // 우 하 좌 상

	private void solution() throws IOException {
		init();
		for (int i = 1; i <= K; i++) {
			clearHeap();

			if (minHeap.isEmpty() || maxHeap.isEmpty()) break;

			Terret weak = minHeap.poll();
			Terret strong = maxHeap.poll();

			if (weak.row == strong.row && weak.col == strong.col) break;

			weak.recentAttack = i; // 최근 공격 시간 업데이트
			weak.power += (N + M); // 공격력 업데이트

			List<Point> attackList = findLaserRoot(weak, strong);

			if (attackList.isEmpty()) {
				attackList = terretAttack(weak, strong, weak.power);
			} else {
				laserAttack(attackList, weak.power);
			}
			getPower(attackList, weak, strong);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				answer = Math.max(answer, map[i][j].power);
			}
		}

		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
	}

	private void getPower(List<Point> attackList, Terret weak, Terret strong) {
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j].power <= 0) visited[i][j] = true;
			}
		}

		visited[weak.row][weak.col] = true;
		visited[strong.row][strong.col] = true;

		for(Point point : attackList) {
			visited[point.row][point.col] = true;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) continue;
				map[i][j].power++;
			}
		}
	}

	private List<Point> terretAttack(Terret weak, Terret strong, int attackPower) {
		List<Point> road = new ArrayList<>();
		road.add(new Point(strong.row, strong.col));

		map[strong.row][strong.col].power = Math.max(0, map[strong.row][strong.col].power - attackPower);

		for(int[] delta : deltas) {
			int nextRow = strong.row + delta[0];
			int nextCol = strong.col + delta[1];

			if (nextRow < 0) nextRow = N - 1;
			if (nextRow >= N) nextRow = 0;
			if (nextCol < 0) nextCol = M - 1;
			if (nextCol >= M) nextCol = 0;

			if (nextRow == weak.row && nextCol == weak.col) continue;
			map[nextRow][nextCol].power = Math.max(0, map[nextRow][nextCol].power - (attackPower / 2));
			road.add(new Point(nextRow, nextCol));
		}

		return road;
	}

	private List<Point> findLaserRoot(Terret weak, Terret strong) {
		List<Point> road = new ArrayList<>();

		Deque<Point> toVisit = new ArrayDeque<>();
		Point[][] visited = new Point[N][M];

		toVisit.add(new Point(weak.row, weak.col));
		visited[weak.row][weak.col] = new Point(weak.row, weak.col);

		while (!toVisit.isEmpty()) {
			Point now = toVisit.poll();

			if (now.row == strong.row && now.col == strong.col) {
				road.add(new Point(strong.row, strong.col));
				Point target = visited[strong.row][strong.col];
				while (!(target.row == weak.row && target.col == weak.col)) {
					road.add(new Point(target.row, target.col));
					target = visited[target.row][target.col];
				}
			}

			for(int dir : new int[] {0, 1, 2, 3}) {
				int nextRow = now.row + deltas[dir][0];
				int nextCol = now.col + deltas[dir][1];

				if (nextRow < 0) nextRow = N - 1;
				if (nextRow >= N) nextRow = 0;
				if (nextCol < 0) nextCol = M - 1;
				if (nextCol >= M) nextCol = 0;

				if (visited[nextRow][nextCol] != null) continue;
				if (map[nextRow][nextCol].power == 0) continue;

				toVisit.add(new Point(nextRow, nextCol));
				visited[nextRow][nextCol] = new Point(now.row, now.col);
			}
		}

		return road;
	}

	private void laserAttack(List<Point> attackList, int attackPower) {
		for (int i = 0; i < attackList.size(); i++) {
			Point now = attackList.get(i);

			if (i == 0) {
				Terret target = map[now.row][now.col];
				target.power = Math.max(0, target.power - attackPower);
			} else {
				Terret target = map[now.row][now.col];
				target.power = Math.max(0, target.power - (attackPower / 2));
			}
		}
	}

	private void clearHeap() {
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		minHeap = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j].power <= 0) continue;
				minHeap.offer(map[i][j]);
				maxHeap.offer(map[i][j]);
			}
		}
	}

	private void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new Terret[N][M];

		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < M; j++) {
				Terret terret = new Terret(i, j, Integer.parseInt(tokenizer.nextToken()));
				map[i][j] = terret;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new DestroyTheTerret().solution();
	}
}
