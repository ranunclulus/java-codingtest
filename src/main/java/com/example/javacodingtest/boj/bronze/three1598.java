package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class three1598 {
    public void solution() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt() - 1;
        int b = scanner.nextInt() - 1;

        int answer = 0;

        answer += Math.abs(a / 4 - b / 4);
        answer += Math.abs(a % 4 - b % 4);

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new three1598().solution();
    }
}
