package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

public class three17642 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        long result = 0;
        for (int test = 1; test <= testNum; test++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            for (long i = 1; i < Long.MAX_VALUE; i++) {
                boolean isPrime = true;
                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (a + i == b - i) result = i;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three17642().solution();
    }
}
