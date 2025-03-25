package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.25
 @link https://school.programmers.co.kr/learn/courses/30/lessons/87946
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers87946 {
    
    static int tired, answer;
    static int[][] dungeons;
    static int[] sequence;
    static boolean[] visited;
    
    public int solution(int tired, int[][] dungeons) {
        this.tired = tired;
        this.dungeons = dungeons;
        this.sequence = new int[dungeons.length];
        this.visited = new boolean[dungeons.length];
        
        dfs(0);
        
        return answer;
    }
    
    public void dfs(int depth) {
        if (depth == dungeons.length) {
            calculation();
            return;
        }
        
        for(int i = 0; i < dungeons.length; i++) {
            if (visited[i]) continue;
            sequence[depth] = i;
            visited[i] = true;
            dfs(depth + 1);
            visited[i] = false;
        }
    }
    
    public void calculation() {
        int tempTired = tired;
        int count = 0;
        for(int index : sequence) {
            int[] dungeon = dungeons[index];
            if (tempTired < dungeon[0]) continue;
            tempTired -= dungeon[1];
            count++;
        }
        answer = Math.max(answer, count);
    }
}
