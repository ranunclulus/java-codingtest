package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class one1145 {
    public void solution() throws IOException {
        int[] numbers = new int[5];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);

        int index = numbers[0];
        while (true) {
            int count = 0;
            for (int i = 0; i < 5; i++) {
                if (index % numbers[i] == 0) count++;
            }
            if (count >= 3) {
                System.out.println(index);
                return;
            }
            index++;
        }
    }

    public static void main(String[] args) throws IOException {
        new one1145().solution();
    }
}
