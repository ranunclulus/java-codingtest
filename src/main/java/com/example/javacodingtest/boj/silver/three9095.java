package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class three9095 {
    static int[] dp = new int[13];
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testCase; i++) {
            int number = Integer.parseInt(reader.readLine());
            System.out.println(plusCombination(number));
        }
    }

    private int plusCombination(int number) {
        if (number == 1) return 1;
        if (number == 2) return 2;
        if (number == 3) return 4;
        dp[number] = plusCombination(number - 1) // + 1 한 경우
        + plusCombination(number - 2) // + 2 한 경우
        + plusCombination(number - 3);
        return dp[number];
    }

    public static void main(String[] args) throws IOException {
        new three9095().solution();
    }
}
