package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class four2440 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String star = "*";
        for (int i = 0; i < n; i++) {
            System.out.printf("%s", star.repeat(n - i));
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        new four2440().solution();
    }
}
