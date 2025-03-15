package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.15
 @link https://www.acmicpc.net/problem/7576
 @timecomplex
 @performance 112808KB 576MS
 @category
 @note
 */
public class five7576_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int n, m, time;
	static int[][] map;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static Deque<int[]> toVisit;
	static boolean[][] visited;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		m = Integer.parseInt(tokenizer.nextToken()); //6
		n = Integer.parseInt(tokenizer.nextToken());//4

		toVisit = new ArrayDeque<>();
		visited = new boolean[n][m];
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				if (map[i][j] == 1) {
					toVisit.offer(new int[] {i, j, 0});
					visited[i][j] = true;
				}
			}
		}

		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			time = Math.max(now[2], time);

			for(int[] delta : deltas) {
				int nextRow = now[0] + delta[0];
				int nextCol = now[1] + delta[1];

				if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if (visited[nextRow][nextCol]) continue;
				if (map[nextRow][nextCol] != 0) continue;

				toVisit.offer(new int[] {nextRow, nextCol, now[2] + 1});
				visited[nextRow][nextCol] = true;
				map[nextRow][nextCol] = 1;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) time = -1;
			}
		}

		builder.append(time);
		writer.write(builder.toString());
		writer.flush();
	}


	public static void main(String[] args) throws IOException {
		new five7576_2().solution();
	}
}
