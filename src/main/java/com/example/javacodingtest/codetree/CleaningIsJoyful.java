package com.example.javacodingtest.codetree;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CleaningIsJoyful {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N;
	static int[][] map;
	static List<int[]> pointers;
	static int[][] deltas = new int[][] {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static Rest[] rests;
	static int out;

	class Rest {
		int[][] restMap;

		Rest(int[][] restMap) {
			this.restMap = restMap;
		}
	}

	public void solution() throws IOException {
		init();

		for(int i = 1; i < pointers.size(); i++) {
			int[] pre = pointers.get(i - 1);
			int[] now = pointers.get(i);

			int direction = -1;
			for(int j = 0; j < 4; j++) {
				int nextRow = pre[0] + deltas[j][0];
				int nextCol = pre[1] + deltas[j][1];

				if (nextRow == now[0] && nextCol == now[1]) {
					direction = j;
					break;
				}
			}

			clean(now, direction);
		}
		builder.append(out);
		writer.write(builder.toString());
		writer.flush();
		writer.close();

	}

	public void clean(int[] now, int direction) {
		int dust = map[now[0]][now[1]];
		Rest rest = rests[direction];
		int remain = dust;


		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				int row = now[0] + i;
				int col = now[1] + j;

				int percentage = rest.restMap[i + 2][j + 2];
				if (percentage == 0 || percentage == -1) continue;

				int addAmount = 0;
				addAmount = dust * percentage / 100;
				remain -= addAmount;

				if (row < 0 || row >= N || col < 0 || col >= N){
					out += addAmount;
					continue;
				}

				map[row][col] += addAmount;

			}
		}

		int nextRow = now[0] + deltas[direction][0];
		int nextCol = now[1] + deltas[direction][1];
		if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
			out += remain;
		} else {
			map[nextRow][nextCol] += remain;
		}

		map[now[0]][now[1]] = 0;
	}


	public void init() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}


		pointers = new ArrayList<int[]>();
		int row = N / 2;
		int col = N / 2;
		int gap = 1;
		int count = 1;
		int direction = 0;
		pointers.add(new int[] {row, col});

		while (count < N * N) {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < gap; j++) {
					row += deltas[direction][0];
					col += deltas[direction][1];
					pointers.add(new int[] {row, col});
					count++;
					if (count == N * N) break;
				}
				direction = (direction + 1) % 4;
				if (count == N * N) break;
			}
			gap++;
			if (count == N * N) break;
		}

		rests = new Rest[4];
		rests[0] = new Rest(new int[][] {{0, 0, 2, 0, 0},
			{0, 10, 7, 1, 0},
			{5, -1, 0, 0, 0},
			{0, 10, 7, 1, 0},
			{0, 0, 2, 0, 0}});

		rests[1] = new Rest(new int[][] {{0, 0, 0, 0, 0},
			{0, 1, 0, 1, 0},
			{2, 7, 0, 7, 2},
			{0, 10, -1, 10, 0},
			{0, 0, 5, 0, 0}});

		rests[2] = new Rest(new int[][] {{0, 0, 2, 0, 0},
			{0, 1, 7, 10, 0},
			{0, 0, 0, -1, 5},
			{0, 1, 7, 10, 0},
			{0, 0, 2, 0, 0}});

		rests[3] = new Rest(new int[][] {{0, 0, 5, 0, 0},
			{0, 10, -1, 10, 0},
			{2, 7, 0, 7, 2},
			{0, 1, 0, 1, 0},
			{0, 0, 0, 0, 0}});
	}


	public static void main(String[] args) throws IOException {
		new CleaningIsJoyful().solution();
	}

}
