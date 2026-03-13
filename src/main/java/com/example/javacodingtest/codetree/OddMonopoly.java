package com.example.javacodingtest.codetree;

import java.util.*;
import java.io.*;

public class OddMonopoly {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, K;
	static Player[] players;
	static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] contractTime;
	static int[][] map;
	static int[][] playerCount;
	static int time;

	class Player {
		int number;
		int row;
		int col;
		int direction;
		int[][] priority;
		boolean isAlive;

		Player(int number, int row, int col) {
			this.number = number;
			this.row = row;
			this.col = col;
			this.direction = -1;
			this.priority = new int[4][4];
			this.isAlive = true;
		}
	}

	public void solution() throws IOException {
		init();

		while(true) {
			time++;
			movePlayer();
			reduceDuplication();
			resetContract();
			if (getAlivePlayer() == 1) break;
			if (time >= 1000) {
				time = -1;
				break;
			}
		}

		builder.append(time);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void movePlayer() {
		playerCount = new int[N][N];
		for(int i = 0; i < M; i++) {
			Player player = players[i];
			if (!player.isAlive) continue;

			boolean moved = false;
			for(int j = 0; j < 4; j++) {
				int nextRow = player.row + deltas[player.priority[player.direction][j]][0];
				int nextCol = player.col + deltas[player.priority[player.direction][j]][1];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if (contractTime[nextRow][nextCol] > 0) continue;

				player.row = nextRow;
				player.col = nextCol;
				player.direction = player.priority[player.direction][j];
				playerCount[nextRow][nextCol]++;
				moved = true;
				break;
			}

			if (!moved) {
				for(int j = 0; j < 4; j++) {
					int nextRow = player.row + deltas[player.priority[player.direction][j]][0];
					int nextCol = player.col + deltas[player.priority[player.direction][j]][1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
					if (map[nextRow][nextCol] != player.number) continue;

					player.row = nextRow;
					player.col = nextCol;
					player.direction = player.priority[player.direction][j];
					playerCount[nextRow][nextCol]++;
					moved = true;
					break;
				}
			}
		}
	}

	public void reduceDuplication() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {

				if (playerCount[i][j] < 1) continue;

				else if (playerCount[i][j] == 1) {
					for(int k = 0; k < M; k++) {
						Player player = players[k];
						if (!player.isAlive) continue;

						if (player.row == i && player.col == j) {
							map[i][j] = player.number;
							contractTime[i][j] = K + 1;
							break;
						}
					}
				}

				else {
					boolean isFind = false;
					for(int k = 0; k < M; k++) {
						Player player = players[k];
						if (!player.isAlive) continue;

						if (player.row == i && player.col == j) {
							if (!isFind) {
								map[i][j] = player.number;
								contractTime[i][j] = K + 1;
								isFind = true;
							} else {
								player.isAlive = false;
							}
						}

					}
				}
			}
		}
	}

	public void resetContract() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (contractTime[i][j] == 0) continue;
				contractTime[i][j]--;
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;
				if (contractTime[i][j] == 0) map[i][j] = 0;
			}
		}
	}

	public int getAlivePlayer() {
		int count = 0;
		for(int i = 0; i < M; i++) {
			if (players[i].isAlive) count++;
		}
		return count;
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		K = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		contractTime = new int[N][N];
		players = new Player[M];

		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());

				if (map[i][j] > 0) {
					players[map[i][j] - 1] = new Player(map[i][j], i, j);
					contractTime[i][j] = K;
				}
			}
		}

		tokenizer = new StringTokenizer(reader.readLine());
		for(int i = 0; i < M; i++) {
			Player player = players[i];
			player.direction = Integer.parseInt(tokenizer.nextToken()) - 1;

		}

		for(int i = 0; i < M; i++) {
			Player player = players[i];

			for(int j = 0; j < 4; j++) {
				tokenizer = new StringTokenizer(reader.readLine());
				for(int k = 0; k < 4; k++) {
					player.priority[j][k] = Integer.parseInt(tokenizer.nextToken()) - 1;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new OddMonopoly().solution();
	}

}