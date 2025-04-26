package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranunclulus
 @since 2025.04.26
 @link https://school.programmers.co.kr/learn/courses/30/lessons/81302
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers81302 {
	static String[][] seats;
	static int[] answer;
	static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	public int[] solution(String[][] places) {
		this.seats = places;
		this.answer = new int[5];

		for(int i = 0; i < 5; i++) {
			String[] seat = seats[i];
			boolean signal = true;

			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 5; k++) {
					char ch = seat[j].charAt(k);
					if (ch != 'P') continue;

					Deque<int[]> toVisit = new ArrayDeque<>();
					boolean[][] visited = new boolean[5][5];
					toVisit.add(new int[] {j, k, 0});
					visited[j][k] = true;

					while (!toVisit.isEmpty()) {
						int[] now = toVisit.poll();

						if (now[2] == 2) continue;

						for(int[] delta : deltas) {
							int newRow = now[0] + delta[0];
							int newCol = now[1] + delta[1];

							if (newRow < 0 || newRow >= 5 || newCol < 0 || newCol >= 5) continue;
							if (visited[newRow][newCol]) continue;
							if (seat[newRow].charAt(newCol) == 'P') {
								signal = false;
								toVisit.clear();
								break;
							}
							if (seat[newRow].charAt(newCol) == 'X') continue;

							toVisit.add(new int[] {newRow, newCol, now[2] + 1});
						}
					}

				}
			}

			answer[i] = signal ? 1 : 0;
		}
		return answer;
	}
}