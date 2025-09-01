package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranunclulus
 @since 2025.09.02
 @link https://www.acmicpc.net/problem/15684
 @timecomplex
 @performance
 @category
 @note
 */
public class three15684_2{
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tokenizer;
	static StringBuilder builder = new StringBuilder();
	static int N, M, H;
	static int[][] lines;
	static int answer = Integer.MAX_VALUE;
	static boolean finish;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		H = Integer.parseInt(tokenizer.nextToken());
		lines = new int[H + 1][N + 1];
		for(int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(tokenizer.nextToken());
			int b = Integer.parseInt(tokenizer.nextToken());
			lines[a][b] = 1;
			lines[a][b + 1] = 2;
		}

		for(int i = 0; i <= 3; i++) {
			answer = i;
			makeLine(1, 0);
			if (finish) break;
		}

		builder.append(finish ? answer : -1);
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}


	public void makeLine(int col, int count) {
		if (finish) return;
		if (answer == count) {
			if (check()) finish = true;
			return;
		}

		for(int i = col; i <= H; i++) {
			for(int j = 1; j < N; j++) {
				if (lines[i][j] == 0 && lines[i][j + 1] == 0) {
					lines[i][j] = 1;
					lines[i][j + 1] = 2;
					makeLine(col, count + 1);
					lines[i][j] = 0;
					lines[i][j + 1] = 0;
				}
			}
		}

	}



	private boolean check() {
		for(int i = 1; i <= N; i++) {
			int row = i;
			for(int j = 1; j <= H; j++) {
				if (lines[j][row] == 1) row++;
				else if (lines[j][row] == 2) row--;
			}
			if (row != i) return false;
		}
		return true;
	}


	public static void main(String[] args) throws IOException {
		new three15684_2().solution();
	}
}
