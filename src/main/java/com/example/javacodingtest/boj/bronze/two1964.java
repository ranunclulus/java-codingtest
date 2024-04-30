package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class two1964 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 1 -> 5
        // 2 -> 5 + 7 = 12
        // 3 -> 5 + 7 + 10 = 22;

        long total = 5;
        long dot = 7;
        for (int i = 2; i <= n; i++) {
            total += dot;
            dot += 3;
        }

        System.out.println(total % 45678);
    }

    public static void main(String[] args) throws IOException {
        new two1964().solution();
    }
}
