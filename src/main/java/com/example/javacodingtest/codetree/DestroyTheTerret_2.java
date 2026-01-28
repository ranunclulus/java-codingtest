package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2026.01.27
 @link https://www.codetree.ai/ko/frequent-problems/problems/destroy-the-turret/description
 @timecomplex
 @performance 
 @category
 @note
 */
public class DestroyTheTerret_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, K;
	static Turret[][] map;
	static Turret attacker, victim;
	static int[][] deltas = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	static boolean[][] affected;
	static int answer;

	class Point {
		int row;
		int col;
		int distance;
		Point(int row, int col, int distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;
		}
	}

	class Turret implements Comparable<Turret>{
		int row;
		int col;
		int power;
		int turn;

		Turret(int row, int col, int power) {
			this.row = row;
			this.col = col;
			this.power = power;
			this.turn = 0;
		}

		@Override
		public int compareTo(Turret o) {
			if (this.power == o.power) {
				if (this.turn == o.turn) {
					if (this.row + this.col == o.row + o.col) {
						return -Integer.compare(this.col, o.col);
					}
					return -Integer.compare(this.row + this.col, o.row + o.col);
				}
				return -Integer.compare(this.turn, o.turn);
			}
			return Integer.compare(this.power, o.power);
		}
	}

	public void solution() throws IOException {
		init();
		for(int i = 1; i <= K; i++) {
			selectAttackerAndVictim();
			attacker.turn = i;
			List<int[]> laserRoot = getLaserAttackRoot();
			affected = new boolean[N][M];
			if (laserRoot.size() != 0) {
				laserAttack(laserRoot);
			} else {
				bombAttack();
			}
			resetTurret();
			if (isEnd()) break;
		}
		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public boolean isEnd() {
		int turretCount = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j].power > 0) turretCount++;
			}
		}
		return turretCount == 1;
	}

	public void selectAttackerAndVictim() {
		PriorityQueue<Turret> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j].power == 0) continue;
				pq.add(map[i][j]);
			}
		}
		attacker = pq.poll();
		while(pq.size() != 1) {
			pq.poll();
		}
		victim = pq.poll();
		attacker.power += (N + M);
	}

	public List<int[]> getLaserAttackRoot() {
		List<int[]> root = new ArrayList<>();

		Deque<int[]> toVisit = new ArrayDeque<>();
		Point[][] rootMap = new Point[N][M];

		toVisit.add(new int[] {attacker.row, attacker.col, 0});
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				rootMap[i][j] = new Point(i, j, Integer.MAX_VALUE);
			}
		}
		rootMap[attacker.row][attacker.col] = new Point(-1, -1, 0);

		while(!toVisit.isEmpty()) {
			int[] now = toVisit.poll();

			if (now[0] == victim.row && now[1] == victim.col) {
				root.add(new int[] {victim.row, victim.col});

				int iRow = victim.row;
				int iCol = victim.col;

				while(true) {
					Point point = rootMap[iRow][iCol];
					root.add(new int[] {point.row, point.col});
					iRow = point.row;
					iCol = point.col;

					if (iRow == attacker.row && iCol == attacker.col) break;
				}
				break;
			}

			for(int i = 0; i < 4; i++) {
				int nextRow = (now[0] + deltas[i][0] + N) % N;
				int nextCol = (now[1] + deltas[i][1] + M) % M;
				int nextDistance = now[2] + 1;

				if (map[nextRow][nextCol].power == 0) continue;
				if (rootMap[nextRow][nextCol].distance <= nextDistance) continue;

				toVisit.add(new int[] {nextRow, nextCol, nextDistance});
				rootMap[nextRow][nextCol] = new Point(now[0], now[1], nextDistance);

			}
		}
		return root;
	}

	public void laserAttack(List<int[]> laserRoot) {
		for(int i = 0; i < laserRoot.size(); i++) {
			int[] root = laserRoot.get(i);
			if (root[0] == attacker.row && root[1] == attacker.col) continue;
			if (root[0] == victim.row && root[1] == victim.col) {
				map[root[0]][root[1]].power -= attacker.power;
			} else {
				map[root[0]][root[1]].power -= (attacker.power / 2);
				affected[root[0]][root[1]] = true;
			}

		}
		affected[attacker.row][attacker.col] = true;
		affected[victim.row][victim.col] = true;
	}

	public void bombAttack() {
		map[victim.row][victim.col].power -= attacker.power;

		for(int[] delta : deltas) {
			int nextRow = (victim.row + delta[0] + N) % N;
			int nextCol = (victim.col + delta[1] + M) % M;
			if(nextRow == attacker.row && nextCol == attacker.col) continue;
			map[nextRow][nextCol].power -= (attacker.power / 2);
			affected[nextRow][nextCol] = true;
		}

		affected[attacker.row][attacker.col] = true;
		affected[victim.row][victim.col] = true;

	}

	public void resetTurret() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if (map[i][j].power < 0) map[i][j].power = 0;
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if (map[i][j].power == 0) continue;
				if (affected[i][j]) continue;
				map[i][j].power++;

			}
		}

		answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				answer = Math.max(answer, map[i][j].power);
			}
		}
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new Turret[N][M];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = new Turret(i, j, Integer.parseInt(tokenizer.nextToken()));
			}
		}
	}


	public static void main(String[] args) throws IOException {
		new DestroyTheTerret_2().solution();
	}


}