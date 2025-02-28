package com.example.javacodingtest.programmers.level3;

/*
 @author ranuinclulus
 @since 2025.02.28
 @link https://school.programmers.co.kr/learn/courses/30/lessons/49191
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers49191 {
	static int[][] rating;
	static int answer;

	public int solution(int n, int[][] results) {
		rating = new int[n][n];
		for(int[] result : results) {
			rating[result[0] - 1][result[1] - 1] = 1;
			rating[result[1] - 1][result[0] - 1] = -1;
		}

		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if (i == j) continue;

					if (rating[i][k] == 1 && rating[k][j] == 1) {
						rating[i][j] = 1;
						rating[k][i] = -1;
						rating[j][k] = -1;
						rating[j][i] = -1;
					}
				}
			}
		}

		for(int i = 0; i < n; i++) {
			int zeroCount = 0;
			for(int j = 0; j < n; j++) {
				if (rating[i][j] == 0) zeroCount++;
				if (zeroCount > 1) break;
			}
			if (zeroCount == 1) answer++;
		}
		return answer;
	}
}
