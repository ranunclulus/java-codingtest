package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.02.15
 @link https://school.programmers.co.kr/learn/courses/30/lessons/67258
 @timecomplex
 @performance 
 @category
 @note
 */
public class Programmers67258 {

	static Set<String> gemsSet;
	static Map<String, Integer> map;
	static int minPart = Integer.MAX_VALUE;

	public int[] solution(String[] gems) {
		int[] answer = new int[2];

		gemsSet = new HashSet<>();
		for(String gem : gems) {
			gemsSet.add(gem);
		}

		map = new HashMap<>();

		int left = 0;
		for(int right = 0; right < gems.length; right++) {
			map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);

			while (map.get(gems[left]) > 1) {
				map.put(gems[left], map.get(gems[left]) - 1);
				left++;
			}

			if (map.size() == gemsSet.size() && minPart > (right - left)) {
				minPart = (right - left);
				answer[0] = left + 1;
				answer[1] = right + 1;
			}
		}

		return answer;
	}
}
