package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.26
 @link https://school.programmers.co.kr/learn/courses/30/lessons/12987
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers12987 {
    
    static int[] A, B;
    static int answer;
    
    public int solution(int[] A, int[] B) {
        this.A = A;
        this.B = B;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int left = 0;
        int right = A.length - 1;
        
        for(int i = A.length - 1; i >= 0; i--) {
            if (left > right) break;
            if (A[i] < B[right]) {
                answer++;
                right--;
            } else {
                left++;
            }
        }
        
        return answer;
    }
    
}
