package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranunclulus
 @since 2025.09.02
 @link https://www.acmicpc.net/problem/15686
 @timecomplex
 @performance
 @category
 @note
 */
public class five15686_3{
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	static List<Point> shops, houses;
	static boolean[] selected;

	class Point {
		int row;
		int col;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		map = new int[N][N];

		shops = new ArrayList<>();
		houses = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				if (map[i][j] == 2) shops.add(new Point(i, j));
				else if (map[i][j] == 1) houses.add(new Point(i, j));
			}
		}

		selected = new boolean[shops.size()];
		select(0, 0);

		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void select(int depth, int count) {
		if (depth == shops.size()) {
			if (count != M) return;
			calculate();
			return;
		}

		select(depth + 1, count);

		if (count < M) {
			selected[depth] = true;
			select(depth + 1, count + 1);
			selected[depth] = false;
		}
	}

	public void calculate() {
		int sum = 0;

		for(Point house : houses) {
			int minDistance = Integer.MAX_VALUE;
			for(int i = 0; i < shops.size(); i++) {
				if (!selected[i]) continue;
				minDistance = Math.min(minDistance, Math.abs(house.row - shops.get(i).row) + Math.abs(house.col - shops.get(i).col));
			}
			sum += minDistance;
		}

		answer = Math.min(answer, sum);
	}

	public static void main(String[] args) throws IOException {
		new five15686_3().solution();
	}

}

