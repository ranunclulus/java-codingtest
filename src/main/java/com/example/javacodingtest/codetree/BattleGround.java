package com.example.javacodingtest.codetree;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.04.13
 @link https://www.codetree.ai/ko/frequent-problems/problems/battle-ground/description
 @timecomplex
 @performance
 @category
 @note
 */
public class BattleGround {
	class Player {
		int row;
		int col;
		int direction;
		int initExp;
		int point;
		int gunExp;

		Player(int row, int col, int direction, int initExp) {
			this.row = row;
			this.col = col;
			this.direction = direction;
			this.initExp = initExp;
			this.point = 0;
			this.gunExp = 0;
		}
	}

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int N, M, K;
	static ArrayList<Integer>[][] map;
	static Player[] players;


	public void solution() throws IOException {
		init();

		for (int turn = 1; turn <= K; turn++) {
			for (Player player : players) {
				movePlayer(player);
			}
		}

		for(Player player : players) {
			builder.append(player.point).append(' ');
		}
		writer.write(builder.toString());
		writer.flush();
	}

	public void movePlayer(Player player) {
		int nextRow = player.row + deltas[player.direction][0];
		int nextCol = player.col + deltas[player.direction][1];
		int nextDirection = player.direction;

		if (!inRange(nextRow, nextCol)) {
			nextDirection = (player.direction + 2) % 4;
			nextRow = player.row + deltas[nextDirection][0];
			nextCol = player.col + deltas[nextDirection][1];
		}
		Player original = findPlayer(nextRow, nextCol);

		player.row = nextRow;
		player.col = nextCol;
		player.direction = nextDirection;

		if (original == null) {
			getGun(player, nextRow, nextCol);
		} else {
			fight(player, original, nextRow, nextCol);
		}
	}

	public void fight(Player player, Player original, int row, int col) {
		int playerPoint = player.initExp + player.gunExp;
		int originalPoint = original.initExp + original.gunExp;

		if ((playerPoint > originalPoint) || (playerPoint == originalPoint && player.initExp > original.initExp)) {
			player.point += (playerPoint - originalPoint);
			loserMove(original);
			getGun(player, row, col);
		} else {
			original.point += (originalPoint - playerPoint);
			loserMove(player);
			getGun(original, row, col);
		}
	}

	public void loserMove(Player player) {
		map[player.row][player.col].add(player.gunExp);
		player.gunExp = 0;

		for (int i = 0; i < 4; i++) {
			int nextDirection = (player.direction + i) % 4;
			int nextRow = player.row + deltas[nextDirection][0];
			int nextCol = player.col + deltas[nextDirection][1];

			if (!inRange(nextRow, nextCol)) continue;
			if (findPlayer(nextRow, nextCol) != null) continue;

			player.direction = nextDirection;
			getGun(player, nextRow, nextCol);
			return;
		}
	}

	public void getGun(Player player, int row, int col) {
		List<Integer> guns = map[row][col];
		guns.add(player.gunExp);
		Collections.sort(guns);
		player.gunExp = guns.get(guns.size() - 1);
		player.row = row;
		player.col = col;
		guns.remove(guns.size() - 1);
	}

	public Player findPlayer(int row, int col) {
		for(Player player : players) {
			if (player.row == row && player.col == col) return player;
		}
		return null;
	}

	public boolean inRange(int row, int col) {
		return (row >= 0 && row < N && col >= 0 && col < N);
	}

	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new ArrayList[N][N];
		players = new Player[M];

		for (int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < N; j++) {
				int gun = Integer.parseInt(tokenizer.nextToken());

				map[i][j] = new ArrayList<>();
				if (gun != 0) map[i][j].add(gun);
			}
		}

		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			players[i] = new Player(Integer.parseInt(tokenizer.nextToken()) - 1,
				Integer.parseInt(tokenizer.nextToken()) - 1,
				Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken()));
		}
	}

	public static void main(String[] args) throws IOException {
		new RoyalKnightDuel_2().solution();
	}
}


