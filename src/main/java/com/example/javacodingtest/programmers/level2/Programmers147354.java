package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.01
 @link https://school.programmers.co.kr/learn/courses/30/lessons/147354
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers147354 {
    
    static int[][] data;
    static int col, row_begin, row_end, answer;
    static int[] sums;
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        this.data = data;
        this.col = col;
        this.row_begin = row_begin;
        this.row_end = row_end;
        
        int sortCol = col - 1; 
        Arrays.sort(data, new Comparator<int[]>() {
           @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[sortCol] == o2[sortCol]) return -Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[sortCol], o2[sortCol]);
            }
        });
        
        sums = new int[row_end - row_begin + 1];
        for(int i = 0; i < row_end - row_begin + 1; i++) {
            int[] row = data[row_begin + i - 1];
            for(int value : row) {
                sums[i] += value % (row_begin + i);
            }
        }
        answer = sums[0];
        for(int i = 1; i < sums.length; i++) {
            answer = (answer ^ sums[i]);
        }
        return answer;
    }
}
