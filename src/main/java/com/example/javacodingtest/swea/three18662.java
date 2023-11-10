package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

public class three18662 {
    private int[] numbers;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 0; test < testNum; test++) {
            numbers = new int[3];
            for (int i = 0; i < 3; i++) {
                numbers[i] = sc.nextInt();
            }
            double difference = numbers[1] - ((numbers[0] + numbers[2]) / 2.0);
            System.out.printf("#%d %.1f\n", test + 1, Math.abs(difference));
        }
    }

    public static void main(String[] args) throws IOException {
        new three18662().solution();
    }
}
