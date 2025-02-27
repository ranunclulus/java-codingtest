package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.02.27
 @link https://school.programmers.co.kr/learn/courses/30/lessons/258707
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers258707 {
    private boolean[] used;
    private int coin;
    private boolean canNext = true;
    
    public int solution(int coin, int[] cards) {
        int round = 1;
        List<Integer> select = new ArrayList<>();
        this.coin = coin;
        used = new boolean[cards.length];
        
        for(int i = 0; i < cards.length / 3; i++) {
            select.add(cards[i]);
        }
        
        for(int i = cards.length / 3; i < cards.length; i += 2) {
            select.add(cards[i]);
            select.add(cards[i + 1]);
            
            submit(select, cards.length + 1, cards);
            
            if (this.coin < 0 || !canNext) return round;
            
            round++;
        }
        return round;
    }
    
    public void submit(List<Integer> select, int target, int[] cards) {
        for(int i = 0; i < cards.length / 3; i++) {
            for(int j = i + 1; j < cards.length / 3; j++) {
                if (select.get(i) + select.get(j) == target && !used[i] && !used[j]) {
                    used[i] = true;
                    used[j] = true;
                    return;
                }
            }
        }
        
        for(int i = 0; i < select.size() - 1; i++) {
            for(int j = i + 1; j < select.size(); j++) {
                if (select.get(i) + select.get(j) == target && !used[i] && !used[j]) {
                    used[i] = true;
                    used[j] = true;
                    if (i >= cards.length / 3) {
                        coin--;
                    }
                    if (j >= cards.length / 3) {
                        coin--;
                    }
                    return;
                }
            }
        }
        canNext = false;
    }
   
}
