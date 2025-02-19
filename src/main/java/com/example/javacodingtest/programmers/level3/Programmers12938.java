package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.02.20
 @link https://school.programmers.co.kr/learn/courses/30/lessons/12938
 @timecomplex
 @performance 
 @category
 @note
 */

class Programmers12938 {
    public int[] solution(int n, int s) {
        if (n > s) return  new int[] {-1};
        
        int[] answer = new int[n];
        int idx = 0;
        while(n > 0){
            int value = s / n;
            answer[idx++] = value;
            s -= value;
            n--;
        }
        return answer;
    }
  
}
