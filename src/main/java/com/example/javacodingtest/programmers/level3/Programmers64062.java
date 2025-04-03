package com.example.javacodingtest.programmers.level3

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.03
 @link https://school.programmers.co.kr/learn/courses/30/lessons/64062
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers64062 {
    static int[] stones;
    static int k;
    
    public int solution(int[] stonesInput, int kInput) {
        this.stones = stonesInput;
        this.k = kInput;
        
        int left = 0;
        int right = 0;
        
        for(int stone : stones) {
            right = Math.max(right, stone);
        }
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int jump = 0;
            int maxJump = 0;
            
            for(int stone : stones) {
                if (stone - mid <= 0) {
                    jump++;
                    maxJump = Math.max(jump, maxJump);
                } else jump = 0;
            }
            
            if (maxJump + 1 > k) {
                right = mid - 1;
            } else left = mid + 1;
        }
        
        return left;
    }
}
