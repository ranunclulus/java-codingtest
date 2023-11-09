package com.example.javacodingtest.swea;
// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=2&contestProbId=AV5QSEhaA5sDFAUq&categoryId=AV5QSEhaA5sDFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=2&pageSize=10&pageIndex=1&&&&&&&&&&

import java.io.IOException;
import java.util.Scanner;

public class two2072 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        for (int test = 0; test < testCase; test++) {
            int result = 0;
            for (int i = 0; i < 10; i++) {
                int num = sc.nextInt();
                if ((num % 2) == 1) {
                    result += num;
                }
            }
            System.out.printf("#%d %d\n", test + 1, result);
        }
    }

    public static void main(String[] args) throws IOException {
        new two2072().solution();
    }
}
