package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.19
 @link https://school.programmers.co.kr/learn/courses/30/lessons/150369
 @timecomplex
 @performance
 @category
 @note
 */
class Solution {
    
    static int deliverIndex, pickupIndex, deliverCap, pickupCap;
    static long answer;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        answer = 0;
        deliverIndex = n - 1;
        pickupIndex = n - 1;
        
        while (deliverIndex >= 0 || pickupIndex >= 0) {
            
            while (deliverIndex >= 0 && deliveries[deliverIndex] == 0) deliverIndex--;
            while (pickupIndex >= 0 && pickups[pickupIndex] == 0) pickupIndex--;
            
            if (deliverIndex < 0 && pickupIndex < 0) break;
            
            int distance = Math.max(deliverIndex, pickupIndex) + 1;
            answer += (long) distance * 2;
            
            deliverCap = cap;
            pickupCap = cap;
            
            while (deliverIndex >= 0 && deliverCap > 0) {
                if (deliveries[deliverIndex] <= deliverCap) {
                    deliverCap -= deliveries[deliverIndex];
                    deliveries[deliverIndex] = 0;
                    deliverIndex--;
                } else {
                    deliveries[deliverIndex] -= deliverCap;
                    deliverCap = 0;
                }
            }
            
            while (pickupIndex >= 0 && pickupCap > 0) {
                if (pickups[pickupIndex] <= pickupCap) {
                    pickupCap -= pickups[pickupIndex];
                    pickups[pickupIndex] = 0;
                    pickupIndex--;
                } else {
                    pickups[pickupIndex] -= pickupCap;
                    pickupCap = 0;
                }
            }
        }
        
        return answer;
    }
}
