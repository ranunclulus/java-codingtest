package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.06
 @link https://school.programmers.co.kr/learn/courses/30/lessons/389479
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers389498 {
    class Server implements Comparable<Server>{
        int count;
        int end;
        
        Server(int count, int end) {
            this.count = count;
            this.end = end;
        }
        
        @Override
        public int compareTo(Server o) {
            return Integer.compare(this.end, o.end);
        }
    }
    
    static int answer, currentServer;
    static PriorityQueue<Server> activeServers;
    
    public int solution(int[] players, int m, int k) {
        activeServers = new PriorityQueue<>();
        
        for(int i = 0; i < 24; i++) {
            int people = players[i];
            
            while (!activeServers.isEmpty() && activeServers.peek().end < i) {
                currentServer -= activeServers.poll().count;
            }
            
            int needServer = Math.max(0, people / m - currentServer);
            
            if (needServer > 0) {
                activeServers.offer(new Server(needServer, i + k - 1));
                currentServer += needServer;
                answer += needServer;
            }
            
        }
        
        return answer;
    }
}
