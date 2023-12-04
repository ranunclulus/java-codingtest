package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class two11055 {
    private int[] sequence;
    private int[] dp;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sequence = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = sc.nextInt();
        }
        dp[0] = sequence[0];

        for (int i = 1; i < n; i++) {
            List<Integer> candidates = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) {
                    candidates.add(j);
                }
            }

            int maxValue = 0;
            for (Integer candidate : candidates) {
                maxValue = Math.max(maxValue, dp[candidate]);
            }

            dp[i] = maxValue + sequence[i];

        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }
        
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new two11055().solution();
    }
}
