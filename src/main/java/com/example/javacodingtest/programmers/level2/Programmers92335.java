package com.example.javacodingtest.programmers.level2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 @author ranuinclulus
 @since 2025.02.28
 @link https://school.programmers.co.kr/learn/courses/30/lessons/92335
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers92335 {
    static int answer;

    public int solution(int n, int k) {
        String[] numbers = Integer.toString(n, k).split("0");

        for(String number : numbers) {
            if (number.equals("")) continue;
            long numValue = Long.valueOf(number);
            if (isPrime(numValue)) answer++;
        }
        return answer;
    }

    public boolean isPrime(long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for(int i = 3; i <= Math.sqrt(num); i+=2) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
