package com.example.javacodingtest.programmers.level3;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 @author ranuinclulus
 @since 2025.02.06
 @link https://school.programmers.co.kr/learn/courses/30/lessons/12927?language=java
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers12927 {
	public long solution(int n, int[] works) {
		long answer = 0;

		PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
		for(int i = 0; i < works.length; i++) {
			queue.add(works[i]);
		}

		while (n --> 0) {
			int current = queue.poll();
			queue.add(--current);
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();
			if (current < 0) continue;
			answer += (current * current);
		}
		return answer;
	}
}
