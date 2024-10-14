package com.example.javacodingtest.programmers.level3;

import java.util.*;
public class Programmers42895 {
    public int solution(int N, int number) {
        List<HashSet<Integer>> dp = new LinkedList<>();
        for(int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
        dp.get(1).add(N);

        if (number == N) return 1;

        for(int i = 2; i <= 8; i++) {
            HashSet<Integer> total = dp.get(i);

            for(int j = 1; j < i; j++) {
                HashSet<Integer> aList = dp.get(j);
                HashSet<Integer> bList = dp.get(i - j);

                for(int a : aList) {
                    for(int b : bList) {
                        total.add(a + b);
                        total.add(a - b);
                        total.add(a * b);
                        if (a != 0 && b != 0) total.add(a / b);
                    }
                }
                total.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }
            if (total.contains(number)) return i;
        }

        return -1;
    }
}
