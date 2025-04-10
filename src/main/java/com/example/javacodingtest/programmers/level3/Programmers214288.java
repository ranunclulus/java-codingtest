package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.10
 @link https://school.programmers.co.kr/learn/courses/30/lessons/214288
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers214288 {
    class Counselor implements Comparable<Counselor>{
        int type;
        int endTime;
        
        Counselor(int type, int endTime) {
            this.type = type;
            this.endTime = endTime;
        }
        
        @Override
        public int compareTo(Counselor o) {
            return Integer.compare(this.endTime, o.endTime);
        }
    }
    
    static int n;
    static int[][] reqs;
    static int[] placed;
    static int waitingTime = Integer.MAX_VALUE;
    
    public int solution(int k, int n, int[][] reqs) {
        this.n = n;
        this.reqs = reqs;
        this.placed = new int[k];
        
        Arrays.fill(placed, 1);
        n -= k;
        
        placeCounselor(0, n, k);
        
        return waitingTime;
    }
    
    public void placeCounselor(int depth, int n, int k) {
        if (depth == k) {
            if (n != 0) return;
            calculation(k);
            return;
        }
        
        for(int i = 0; i <= n; i++) {
            placed[depth] = (1 + i);
            placeCounselor(depth + 1, n - i, k);
        }
    }
    
    public void calculation(int k) {
        PriorityQueue<Counselor>[] counselors = new PriorityQueue[k];
        
        for(int i = 0; i < k; i++) {
            counselors[i] = new PriorityQueue<>();
            for(int j = 0; j < placed[i]; j++) {
                counselors[i].add(new Counselor(i, 0));
            }
        }
        
        int totalWaiting = 0;
        
        for(int[] req : reqs) {
            int startTime = req[0];
            int counselingTime = req[1];
            int type = req[2] - 1;
            
            Counselor target = counselors[type].poll();
            
            if (target.endTime > startTime) {
                totalWaiting += (target.endTime - startTime);
                counselors[type].add(new Counselor(type, target.endTime + counselingTime));
            } else {
                counselors[type].add(new Counselor(type, startTime + counselingTime));
            }
        }
        
        waitingTime = Math.min(totalWaiting, waitingTime);
    }
}
