package com.example.javacodingtest.programmers.level3;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.04.08
 @link https://school.programmers.co.kr/learn/courses/30/lessons/72414
 @timecomplex
 @performance 
 @category
 @note
 */
class Programmers72414 {
    static int[] advertise = new int[360000];
    static int playTime, advTime;
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        playTime = timeToInt(play_time);
        advTime = timeToInt(adv_time);
        
        for(String log : logs) {
            String[] startAndEnd = log.split("-");
            int start = timeToInt(startAndEnd[0]);
            int end = timeToInt(startAndEnd[1]);
            for(int i = start; i < end; i++) {
                advertise[i]++;
            }
        }
        
        int maxStart = 0;
        long totalTime = 0;
        for(int i = 0; i < advTime; i++) {
            totalTime += advertise[i];
        }
        long maxTotalTime = totalTime;
        
        for(int i = advTime; i < playTime; i++) {
            totalTime += (advertise[i] - advertise[i - advTime]);
            if (totalTime > maxTotalTime) {
                maxTotalTime = totalTime;
                maxStart = i - advTime + 1;
            }
        }
        return timeToString(maxStart);
    }
    
    public int timeToInt(String time) {
        String[] times = time.split(":");
        int intTime = 0;
        intTime += Integer.parseInt(times[0]) * 60 * 60;
        intTime += Integer.parseInt(times[1]) * 60;
        intTime += Integer.parseInt(times[2]);
        return intTime;
    }
    
    public String timeToString(int time) {
        int hour = time / (60 * 60);
        time %= (60 * 60);
        int minute = time / 60;
        time %= 60;
        int second = time;
        
        String result = "";
        if (hour < 10) result += ("0" + Integer.toString(hour));
        else result += Integer.toString(hour);
        result += ":";
        if (minute < 10) result += ("0" + Integer.toString(minute));
        else result += Integer.toString(minute);
        result += ":";
        if (second < 10) result += ("0" + Integer.toString(second));
        else result += Integer.toString(second);
        
        return result;
    }
}
