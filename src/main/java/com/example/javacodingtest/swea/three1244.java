package com.example.javacodingtest.swea;
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15Khn6AN0CFAYD&categoryId=AV15Khn6AN0CFAYD&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

import java.io.IOException;
import java.util.Scanner;

public class three1244 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 1; test <= testNum; test++) {
            String rawNumString = sc.next();
            int radix = rawNumString.length();
            int switchNum = sc.nextInt();
            int[] numbers = new int[radix];
            for (int i = 0; i < radix; i++) {
                numbers[i] = Integer.parseInt(String.valueOf(rawNumString.charAt(i)));
            }

            int change = 0;
            for (int i = 0; i < radix - 1; i++) {
                int maxIndex = -1;
                int maxValue = numbers[i];

                for (int j = i + 1; j < radix; j++) {
                    int index = j % radix;
                    if (numbers[index] >= maxValue) {
                        maxIndex = index;
                        maxValue = numbers[index];
                    }
                }

                if (maxValue > numbers[i]) {
                    numbers[maxIndex] = numbers[i];
                    numbers[i] = maxValue;
                    change++;
                }

                if (i > 0 && maxIndex != -1) {
                    if (numbers[i] == numbers[i - 1]) {
                        int maxSecondIndex = -1;
                        int maxSecondValue = -1;
                        for (int j = maxIndex; j < radix; j++) {
                            if (numbers[j] > maxSecondValue) {
                                maxSecondIndex = j;
                                maxSecondValue = numbers[j];
                            }
                        }
                        if (maxSecondIndex != i) {
                            numbers[maxSecondIndex] = numbers[maxIndex];
                            numbers[maxIndex] = maxSecondValue;
                        }
                    }
                }

                if (maxIndex == -1 && i == (radix / 2)) {
                    while (change == switchNum - 1) {
                        int temp = numbers[i];
                        numbers[i] = numbers[i - 1];
                        numbers[i - 1] = temp;
                        change++;
                    }
                    int temp = numbers[radix - 1];
                    numbers[radix - 1] = numbers[radix - 2];
                    numbers[radix - 2] = temp;
                    break;
                }
                if (change == switchNum) break;
            }

            String result = "";
            for (int i = 0; i < radix; i++) {
                result += numbers[i];
            }
            System.out.printf("#%d %s\n", test, result);
        }
    }

    public static void main(String[] args) throws IOException {
        new three1244().solution();
    }
}
