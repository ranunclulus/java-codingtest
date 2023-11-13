package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AYaf9W8afyMDFAQ9&categoryId=AYaf9W8afyMDFAQ9&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=1
public class three16800 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();
        for (int test = 1; test <= testNum; test++) {
            long result = Long.MAX_VALUE;
            long number = Long.parseLong(sc.next());
            boolean isPrime = true;

            for (long i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    result = Math.min(result, i + number / i - 2);
                    isPrime = false;
                }
            }
            if (isPrime) result = number - 1;
            if (number == 0) result = 0;

            System.out.printf("#%d %d\n", test, result);
        }
    }

    public static void main(String[] args) throws IOException {
        new three16800().solution();
    }
}
