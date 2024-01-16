package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

public class four11054 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        int[] dpOne = new int[n];
        int[] dpTwo = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            dpOne[i] = 1;
            for (int j = 0; j < i; j++) {
                if (numbers[i] > numbers[j] && dpOne[j] >= dpOne[i])
                    dpOne[i] = dpOne[j] + 1;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            dpTwo[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (numbers[i] > numbers[j] && dpTwo[j] >= dpTwo[i])
                    dpTwo[i] = dpTwo[j] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            result = Math.max(dpOne[i] + dpTwo[i] - 1, result);
        }
        System.out.println(result);
    }


    public static void main(String[] args) throws IOException {
        new four11054().solution();
    }
}
