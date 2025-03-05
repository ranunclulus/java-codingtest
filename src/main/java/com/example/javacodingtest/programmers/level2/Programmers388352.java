package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.05
 @link https://school.programmers.co.kr/learn/courses/30/lessons/388352
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers388352 {
    static int n, answer;
    static int[] code, ans;
    static int[][] q;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        code = new int[5];
        
        makeCode(0, 1);
        
        return answer;
    }
    
    public void makeCode(int depth, int start) {
        if (depth == 5) {
            check();
            return;
        }
        
        for(int i = start; i <= n; i++) {
            code[depth] = i;
            makeCode(depth + 1, i + 1);
        }
    }
    
    public void check() {
        for(int i = 0; i < ans.length; i++) {
            int same = 0;
            
            for(int c : code) {
                for(int qNum : q[i]) {
                    if (c == qNum) same++;
                }
            }
            
            if (same != ans[i]) return;
        }
        answer++;
    }
}
