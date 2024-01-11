package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

public class four17218 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        String inputOne = sc.nextLine();
        String inputTwo = sc.nextLine();

        int lengthOne = inputOne.length();
        int lengthTwo = inputTwo.length();

        int[][] dp = new int[lengthOne + 1][lengthTwo + 1];

        for (int i = 1; i <= lengthOne; i++) {
            for (int j = 1; j <= lengthTwo; j++) {
                if (inputOne.charAt(i - 1) == inputTwo.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = dp[lengthOne][lengthTwo];
        int row = lengthOne;
        int col = lengthTwo;

        while (count > 0) {
            if (dp[row - 1][col] == count) {
                row--;
            } else if (dp[row][col - 1] == count) {
                col--;
            } else {
                sb.append(inputOne.charAt(row - 1));
                count--;
                row--;
                col--;
            }
        }
        System.out.println(sb.reverse().toString());
    }

    public static void main(String[] args) throws IOException {
        new four17218().solution();
    }
}
