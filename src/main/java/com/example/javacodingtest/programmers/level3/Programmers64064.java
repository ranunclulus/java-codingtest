package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.02.14
 @link https://school.programmers.co.kr/learn/courses/30/lessons/64064
 @timecomplex
 @performance 
 @category
 @note
 */
public class Programmers64064 {

	static boolean[][] blackList;
	static int answer;
	static boolean[] visited;
	static List<Integer> candidate;
	public int solution(String[] user_id, String[] banned_id) {
		blackList = new boolean[banned_id.length][user_id.length];

		for(int i = 0; i < banned_id.length; i++) {
			String banned = banned_id[i];

			for(int j = 0; j < user_id.length; j++) {
				String user = user_id[j];

				if (banned.length() != user.length()) continue;
				boolean signal = true;

				for(int k = 0; k < user.length(); k++) {
					if (banned.charAt(k) == '*') continue;
					if (user.charAt(k) != banned.charAt(k)) {
						signal = false;
						break;
					}
				}
				if (signal) blackList[i][j] = true;
			}
		}
		visited = new boolean[user_id.length];
		candidate = new ArrayList<>();
		counting(0, 0, banned_id.length, user_id.length);
		return candidate.size();
	}

	public void counting(int depth, int start, int n, int m) {
		if (depth == n) {
			answer = 0;

			for(int i = 0; i < m; i++) {
				if (visited[i]) answer += (1 << i);
			}
			if (!candidate.contains(answer)) candidate.add(answer);
			return;
		}

		for (int i = start; i < m; i++) {
			if (!blackList[depth][i]) continue;
			if (visited[i]) continue;

			visited[i] = true;
			counting(depth + 1, 0, n, m);
			visited[i] = false;
		}
	}
}
