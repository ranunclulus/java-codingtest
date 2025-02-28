package com.example.javacodingtest.programmers.level2;

import java.util.*;
/*
 @author ranuinclulus
 @since 2025.02.28
 @link https://school.programmers.co.kr/learn/courses/30/lessons/118667
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers118667 {
    static int answer;
    static long oneSum, twoSum;
    static Deque<Integer> q1, q2;

    public int solution(int[] queue1, int[] queue2) {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
        for(int i = 0; i < queue1.length; i++) {
            oneSum += queue1[i];
            twoSum += queue2[i];
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }


        if (oneSum == twoSum) return 0;

        while (oneSum != twoSum) {
            if (answer > queue1.length * 2 + 1) return -1;
            if (oneSum < twoSum) {
                if (q2.isEmpty()) break;
                int num = q2.poll();
                q1.offer(num);
                oneSum += num;
                twoSum -= num;
            } else {
                if (q2.isEmpty()) break;
                int num = q1.poll();
                q2.offer(num);
                twoSum += num;
                oneSum -= num;
            }
            answer++;
            if (oneSum == twoSum) return answer;
        }

        return -1;
    }
}
