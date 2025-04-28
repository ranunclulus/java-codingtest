package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranunclulus
 @since 2025.04.28
 @link https://school.programmers.co.kr/learn/courses/30/lessons/92343
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers92343 {

    static int[] info;
    static int[][] edges;
    static int answer;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;
        boolean[] visited = new boolean[info.length];
        dfs(0, visited, 0, 0);
        return answer;
    }
    
    public void dfs(int index, boolean[] visited, int sheep, int wolf) {
        visited[index] = true;
        
        if (info[index] == 0) {
            sheep++;
        } else {
            wolf++;
        }
        
        if (wolf >= sheep) return;
        else answer = Math.max(answer, sheep);
        
        for (int[] edge : edges) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                boolean[] newVisited = new boolean[info.length];
                for(int i = 0; i < info.length; i++) {
                    newVisited[i] = visited[i];
                }
                dfs(edge[1], newVisited, sheep, wolf);
            }
        }
    }
    
}
