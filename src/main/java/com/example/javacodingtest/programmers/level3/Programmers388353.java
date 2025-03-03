package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.03
 @link https://school.programmers.co.kr/learn/courses/30/lessons/388353
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers388353 {
	static char[][] map;
	static int n, m, answer;
	static int[][] deltas = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public int solution(String[] storage, String[] requests) {

		n = storage.length;
		m = storage[0].length();
		map = new char[n][m];
		for(int i = 0; i < storage.length; i++) {
			map[i] = storage[i].toCharArray();
		}

		for(String request : requests) {
			char target = request.charAt(0);

			if (request.length() == 2) {
				for(int i = 0; i < storage.length; i++) {
					for(int j = 0; j < storage[0].length(); j++) {
						if (map[i][j] == target) map[i][j] = 'a';
					}
				}
				continue;
			}

			int[][] visited = new int[n][m];
			for(int i = 0; i < n; i++) {
				Arrays.fill(visited[i], 2);
			}

			List<int[]> candidate = new ArrayList<>();
			Deque<int[]> toVisit = new ArrayDeque<>();

			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if (i != 0 && i != n - 1 && j != 0 && j != m - 1) continue;
					if (visited[i][j] != 2) continue;
					if(map[i][j] != 'a') {
						visited[i][j] = 1;
						if(map[i][j] == target) {
							candidate.add(new int[] {i, j});
						}
						continue;
					}

					visited[i][j] = 0;
					toVisit.offer(new int[] {i, j});
					while(!toVisit.isEmpty()) {
						int[] now = toVisit.poll();
						for(int k = 0; k < 4; k++) {
							int nextRow = now[0] + deltas[k][0];
							int nextCol = now[1] + deltas[k][1];


							if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
							if (visited[nextRow][nextCol] != 2) continue;

							if (map[nextRow][nextCol] == 'a') {
								visited[nextRow][nextCol] = 0;
								toVisit.offer(new int[] {nextRow, nextCol});
								continue;
							}
							visited[nextRow][nextCol] = 1;
							if (map[nextRow][nextCol] == target) {
								candidate.add(new int[] {nextRow, nextCol});
							}
						}
					}
				}
			}
			for(int[] row : candidate) {
				map[row[0]][row[1]] = 'a';
			}
		}

		for(char[] row : map) {
			for(char c : row) {
				if (c != 'a') answer++;
			}
		}
		return answer;
	}
}
