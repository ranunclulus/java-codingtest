package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranunclulus
 @since 2025.08.26
 @link https://www.acmicpc.net/problem/1405
 @timecomplex
 @performance 15064KB 156MS
 @category
 @note
 */
public class four1405 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int moveCount;
	static double[] percentages = new double[4];
	static int[][] deltas = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static boolean[][] visited = new boolean[30][30];
	static double answer;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		moveCount = Integer.parseInt(tokenizer.nextToken());
		percentages[0] = Double.parseDouble(tokenizer.nextToken()) * 0.01;
		percentages[1] = Double.parseDouble(tokenizer.nextToken()) * 0.01;
		percentages[2] = Double.parseDouble(tokenizer.nextToken()) * 0.01;
		percentages[3] = Double.parseDouble(tokenizer.nextToken()) * 0.01;

		dfs(0, 15, 15, 1);

		builder.append(answer);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	public void dfs(int depth, int row, int col, double percent) {
		if (depth == moveCount) {
			answer += percent;
			return;
		}

		visited[row][col] = true;
		for(int i = 0; i < 4; i++) {
			int nextRow = row + deltas[i][0];
			int nextCol = col + deltas[i][1];

			if (!visited[nextRow][nextCol]) {
				visited[nextRow][nextCol] = true;
				dfs(depth + 1, nextRow, nextCol, percent * percentages[i]);
				visited[nextRow][nextCol] = false;
			}
		}
	}


	public static void main(String[] args) throws IOException {
		new four1405().solution();
	}

}

