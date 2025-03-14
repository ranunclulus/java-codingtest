package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.03.14
 @link https://school.programmers.co.kr/learn/courses/30/lessons/92342
 @timecomplex
 @performance
 @category
 @note
 */
class Programmers92342 {
    static int maxDiff;
    static int[] apeach, rion, answer;
    static boolean signal;
    
    public int[] solution(int n, int[] apeach) {
        this.apeach = apeach;
        this.rion = new int[11];
        this.answer = new int[11];
        
        shooting(0, n);
        if (!signal) answer = new int[] {-1};
        return answer;
    }
    
    public void shooting(int depth, int arrows) {
        if (depth == 11) {
            if (arrows != 0) return;
            calculation();
            return;
        }
        
        for(int i = arrows; i > -1; i--) {
            rion[depth] = i;
            shooting(depth + 1, arrows - i);
        }
    }
    
    public void calculation() {
        int apeachPoint = 0;
        int rionPoint = 0;
        
        for(int i = 0; i < 11; i++) {
            if (rion[i] == 0 && apeach[i] == 0) continue;
            if (rion[i] > apeach[i]) rionPoint += (10 - i);
            else apeachPoint += (10 - i);
        }
        
        if (rionPoint - apeachPoint > maxDiff) {
            signal = true;
            maxDiff = rionPoint - apeachPoint;
            answer = rion.clone();
        }
        else if (rionPoint - apeachPoint == maxDiff) {
            boolean flag = true;
            for(int i = 10; i >= 0; i--) {
                if (answer[i] == rion[i]) continue;
                if (answer[i] > rion[i]) flag = false;
                break;
            }
            if (flag) answer = rion.clone();
        }
    }
}
