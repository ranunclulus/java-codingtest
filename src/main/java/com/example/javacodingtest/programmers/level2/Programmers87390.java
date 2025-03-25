package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.25
 @link https://school.programmers.co.kr/learn/courses/30/lessons/87390
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers87390 {
    static int n;
    static long left, right;
    static int[] answer;
    
    public int[] solution(int n, long left, long right) {
        this.n = n;
        this.left = left;
        this.right = right;
        this.answer = new int[(int) (right - left + 1)];
        
        for(long i = left; i <= right; i++) {
            int row = (int)(i / n) + 1;
            int col = (int)(i % n) + 1;
            answer[(int)(i - left)] = Math.max(row, col);
        }
        
        return answer;
    }
}
