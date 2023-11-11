package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class one14888 {
    private int numberCnt;
    private int[] numbers;
    private int[] operator;
    private int maxValue;
    private int minValue;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        numberCnt = sc.nextInt();
        numbers = new int[numberCnt];
        operator = new int[4];

        for (int i = 0; i < numberCnt; i++) {
            numbers[i] = sc.nextInt();
        }
        for (int i = 0; i < 4; i++) {
            operator[i] = sc.nextInt();
        }

        maxValue = Integer.MIN_VALUE;
        minValue = Integer.MAX_VALUE;

        calculation(0, numbers[0]);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    private void calculation(int depth, int value) {
        if (depth == numberCnt - 1) {
            maxValue = Math.max(maxValue, value);
            minValue = Math.min(minValue, value);
        } else {
            for (int i = 0; i < 4; i++) {
                if (operator[i] > 0) {
                    operator[i]--;
                    if (i == 0) {
                        calculation(depth + 1, value + numbers[depth + 1]);
                        operator[i]++;
                    } else if (i == 1) {
                        calculation(depth + 1, value - numbers[depth + 1]);
                        operator[i]++;
                    } else if (i == 2) {
                        calculation(depth + 1, value * numbers[depth + 1]);
                        operator[i]++;
                    } else {
                        calculation(depth + 1, value / numbers[depth + 1]);
                        operator[i]++;
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new one14888().solution();
    }
}
