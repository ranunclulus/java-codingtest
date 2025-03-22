package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.22
 @link https://school.programmers.co.kr/learn/courses/30/lessons/42885
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers42885 {
	static int[] people;
	static int limit;
	static int answer;

	public int solution(int[] people, int limit) {
		this.people = people;
		this.limit = limit;
		this.answer = 0;

		Arrays.sort(people);

		int left = 0;
		int right = people.length - 1;

		while (left <= right) {
			if (limit >= people[left] + people[right]) {
				left++;
			}
			right--;
			answer++;
		}

		return answer;
	}
}
