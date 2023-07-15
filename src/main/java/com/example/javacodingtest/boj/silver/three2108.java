package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://www.acmicpc.net/problem/2108
public class three2108 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        Map<Integer, Integer> frequency = new HashMap<>();
        int[] numbers = new int[n];
        int total = 0;

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(reader.readLine());
            total += number;
            numbers[i] = number;
            // 최소값 업데이트
            if (number < minValue) minValue = number;
            // 최대값 업데이트
            if (number > maxValue) maxValue = number;

            // 해시에 키가 있다면 밸류만 update
            if (frequency.containsKey(number)) {
                frequency.put(number, frequency.get(number) + 1);
            }
            else {
                frequency.put(number, 1);
            }
        }

        int mostFre = Collections.max(frequency.values());

        List<Integer> freCandi = new ArrayList<>();

        frequency.forEach((intKey, intvalue) -> {
            if (intvalue == mostFre) freCandi.add(intKey);
        } );

        Arrays.sort(numbers);
        Collections.sort(freCandi);

        System.out.println(Math.round(total / (double)n));
        System.out.println(numbers[n / 2]);
        if (freCandi.size() > 1) {
            System.out.println(freCandi.get(1));
        }
        else {
            System.out.println(freCandi.get(0));
        }
        System.out.println(maxValue - minValue);


    }

    public static void main(String[] args) throws IOException {
        new three2108().solution();
    }
}
