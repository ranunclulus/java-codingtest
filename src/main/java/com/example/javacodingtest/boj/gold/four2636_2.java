package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.03.11
 @link https://www.acmicpc.net/problem/2636
 @timecomplex
 @performance 14680KB 116MS
 @category
 @note
 */
public class four2636_2 {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder builder = new StringBuilder();
	static StringTokenizer tokenizer;
	static int N, M, time, cheeseCount, lastCount;
	static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int[][] map;
	static boolean[][] visited;
	static Deque<int[]> thisVisit, nextVisit, nextMelt;

	public void solution() throws IOException {
		tokenizer = new StringTokenizer(reader.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		map = new int[N][M];


		for(int i = 0; i < N; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tokenizer.nextToken());
				if (map[i][j] == 1) cheeseCount++;
			}
		}

		thisVisit = new ArrayDeque<>();
		visited = new boolean[N][M];
		thisVisit.offer(new int[] {0, 0});
		while (cheeseCount > 0) {
			time++;
			nextVisit = new ArrayDeque<>();
			nextMelt = new ArrayDeque<>();

			while (!thisVisit.isEmpty()) {
				int[] now = thisVisit.poll();
				for(int[] delta : deltas) {
					int nextRow = now[0] + delta[0];
					int nextCol = now[1] + delta[1];

					if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
					if (visited[nextRow][nextCol]) continue;

					visited[nextRow][nextCol] = true;

					if (map[nextRow][nextCol] == 1) {
						nextVisit.offer(new int[] {nextRow, nextCol});
						nextMelt.offer(new int[] {nextRow, nextCol});
					} else {
						thisVisit.offer(new int[] {nextRow, nextCol});
					}
				}
			}

			lastCount = cheeseCount;
			cheeseCount -= nextMelt.size();
			thisVisit = nextVisit;
			while (!nextMelt.isEmpty()) {
				int[] target = nextMelt.poll();
				map[target[0]][target[1]] = 0;
			}

		}
		builder.append(time).append('\n').append(lastCount);
		writer.write(builder.toString());
		writer.flush();
	}

	public static void main(String[] args) throws IOException {
		new four2636_2().solution();
	}
}
