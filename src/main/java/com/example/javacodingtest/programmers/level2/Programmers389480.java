package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.06
 @link https://school.programmers.co.kr/learn/courses/30/lessons/389480
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers389480 {
    static int n, m, answer;
    static int[][] info;
    static int[][] dp;
    
    public int solution(int[][] info, int n, int m) {
        this.n = n;
        this.m = m;
        this.info = info;
        
        dp = new int[info.length + 1][m];
        for(int[] d : dp) {
            Arrays.fill(d, 100000);
        }
        dp[0][0] = 0;
        for(int i = 1; i <= info.length; i++){
            int a = info[i - 1][0];
            int b = info[i - 1][1];
            for(int j = 0; j < m; j++){
             
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);
               
                if(j + b < m){
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                }
            }    
        }
        
        int min = 100000;
        for(int j = 0; j < m; j++){
            min = Math.min(dp[info.length][j], min);
        }
        return min >= n ? -1 : min;
    }
}
