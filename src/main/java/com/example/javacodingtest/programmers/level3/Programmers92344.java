package com.example.javacodingtest.programmers.level3;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 @author ranuinclulus
 @since 2025.02.26
 @link https://school.programmers.co.kr/learn/courses/30/lessons/92344
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers92344 {
	static int answer;
	static int[][] diff;

	public int solution(int[][] board, int[][] skills) {
		diff = new int[board.length + 1][board[0].length + 1];
		for(int[] skill : skills) {
			if (skill[0] == 1) skill[5] *= (-1);
			diff[skill[1]][skill[2]] += skill[5];
			diff[skill[1]][skill[4] + 1] -= skill[5];
			diff[skill[3] + 1][skill[2]] -= skill[5];
			diff[skill[3] + 1][skill[4] + 1] += skill[5];
		}


		for(int i = 0; i < board.length + 1; i++) {
			for(int j = 1; j < board[0].length + 1; j++) {
				diff[i][j] += diff[i][j - 1];
			}
		}

		for(int i = 0; i < board[0].length + 1; i++) {
			for(int j = 1; j < board.length + 1; j++) {
				diff[j][i] += diff[j - 1][i];
			}
		}

		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if (board[i][j] + diff[i][j] > 0) answer++;
			}
		}

		return answer;
	}
}
