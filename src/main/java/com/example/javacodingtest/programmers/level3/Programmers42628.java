package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.02.17
 @link https://school.programmers.co.kr/learn/courses/30/lessons/42628
 @timecomplex
 @performance 
 @category
 @note
 */
public class Programmers42628 {

	static int[] answer = new int[2];
	static PriorityQueue<Integer> minHeap;
	static PriorityQueue<Integer> maxHeap;

	public int[] solution(String[] operations) {
		minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

		for(String operation : operations) {
			if (operation.charAt(0) == 'I') {
				int num = Integer.parseInt(operation.substring(2));
				minHeap.add(num);
				maxHeap.add(num);
			} else {
				if (operation.charAt(2) == '1' && !minHeap.isEmpty()) {
					int num = maxHeap.poll();
					minHeap.remove(num);
				} else if (!maxHeap.isEmpty()){
					int num = minHeap.poll();
					maxHeap.remove(num);
				}
			}
		}

		if (minHeap.size() == 1) {
			int num = minHeap.poll();
			answer[0] = num;
			answer[1] = num;
		} else if (minHeap.size() > 1) {
			answer[0] = maxHeap.poll();
			answer[1] = minHeap.poll();
		}

		return answer;
	}
}
