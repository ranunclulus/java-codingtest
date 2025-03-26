package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.26
 @link https://school.programmers.co.kr/learn/courses/30/lessons/42884
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers42884 {
    
    static int[][] routes;
    static int answer;
    
    public int solution(int[][] routes) {
        this.routes = routes;
        
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                else return Integer.compare(o1[0], o2[0]);
            }
        });     
        
        int[] now = routes[0];
        answer = 1;
        
        for(int i = 1; i < routes.length; i++) {
            int[] route = routes[i];
            
            if (now[1] < route[0]) {
                now = route;
                answer++;
            } else if (now[1] > route[1]) {
                now = route;
            }
        }
        return answer;
    }
}
