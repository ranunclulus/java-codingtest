package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class two2577 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int number = 1;
        for (int i = 0; i < 3; i++) {
            number *= sc.nextInt();
        }
        int[] numbers = new int[10];

        while (number > 0) {
            numbers[number % 10]++;
            number /= 10;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(numbers[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        new two2577().solution();
    }
}
