package com.example.javacodingtest.codetree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranunclulus
 @since 2026.01.26
 @link https://www.codetree.ai/ko/frequent-problems/problems/battle-ground/description
 @timecomplex
 @performance
 @category
 @note
 */
public class BattleGround_2 {
	class Player {
		int row;
		int col;
		int direction;
		int initPower;
		int gun;
		int point;

		Player(int row, int col, int direction, int initPower) {
			this.row = row;
			this.col = col;
			this.direction = direction;
			this.initPower = initPower;
			this.gun = 0;
			this.point = 0;
		}

	}
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int N, M, K;
	static Player[] players;
	static List[][] gunMap;


	public void solution() throws IOException {
		init();
		while(K --> 0) {
			for(int i = 0; i < M; i++) {
				movePlayer(i);
				if (existPlayer(i) == -1) {
					getGun(i);
				} else {
					fightPlayer(i);
				}
			}
		}
		for(int i = 0; i < M; i++) {
			builder.append(players[i].point).append(' ');
		}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void movePlayer(int num) {
		Player player = players[num];

		int nextRow = player.row + deltas[player.direction][0];
		int nextCol = player.col + deltas[player.direction][1];

		if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
			player.direction = (player.direction + 2) % 4;
			nextRow = player.row + deltas[player.direction][0];
			nextCol = player.col + deltas[player.direction][1];
		}
		player.row = nextRow;
		player.col = nextCol;
	}

	public int existPlayer(int num) {

		for(int i = 0; i < M; i++) {
			if (i == num) continue;
			Player player = players[i];

			if (player.row == players[num].row && player.col == players[num].col)
				return i;
		}

		return -1;
	}

	public void getGun(int num) {
		Player player = players[num];
		List<Integer> gunList = gunMap[player.row][player.col];
		Collections.sort(gunList, Comparator.reverseOrder());

		if (gunList.size() == 0) return;

		if (player.gun < gunList.get(0)) {
			int newGun = gunList.get(0);
			gunList.remove(0);
			if (player.gun != 0 ) gunList.add(player.gun);
			player.gun = newGun;
		}
	}

	public void fightPlayer(int num) {
		Player one = players[num];
		int twoNum = existPlayer(num);
		Player two = players[twoNum];

		int onePower = one.initPower + one.gun;
		int twoPower = two.initPower + two.gun;

		if (onePower < twoPower) {
			two.point += (twoPower - onePower);
			rollback(num);
			getGun(twoNum);
		} else if (onePower > twoPower) {
			one.point += (onePower - twoPower);
			rollback(twoNum);
			getGun(num);
		} else {
			if (one.initPower > two.initPower) {
				rollback(twoNum);
				getGun(num);
			} else {
				rollback(num);
				getGun(twoNum);
			}
		}
	}

	public void rollback(int num) {
		Player player = players[num];

		if (player.gun != 0) {
			gunMap[player.row][player.col].add(player.gun);
			player.gun = 0;
		}

		for(int i = 0; i < 4; i++) {
			int direction = (player.direction + i) % 4;

			int nextRow = player.row + deltas[direction][0];
			int nextCol = player.col + deltas[direction][1];

			if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
			if (existPlayer(nextRow, nextCol)) continue;

			player.direction = direction;
			player.row = nextRow;
			player.col = nextCol;
			getGun(num);
			break;
		}
	}

	public boolean existPlayer(int row, int col) {
		for(int i = 0; i < M; i++) {
			if (players[i].row == row && players[i].col == col) return true;
		}
		return false;
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());
		gunMap = new List[N][N];

		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < N; j++) {
				gunMap[i][j] = new ArrayList<>();
				int gun = Integer.parseInt(tokenizer.nextToken());
				if (gun != 0) gunMap[i][j].add(gun);
			}
		}

		players = new Player[M];
		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			players[i] = new Player(
				Integer.parseInt(tokenizer.nextToken()) - 1,
				Integer.parseInt(tokenizer.nextToken()) - 1,
				Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken())
			);
		}
	}

	public static void main(String[] args) throws IOException {
		new BattleGround_2().solution();
	}
}


