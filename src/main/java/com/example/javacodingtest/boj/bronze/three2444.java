package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class three2444 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n - 1; i++) {
            System.out.println(String.format(" ".repeat(n - i - 1) + "*".repeat(2 * (i + 1) - 1)));
        }

        for (int i = 0; i < n; i++) {
            System.out.println(String.format(" ".repeat(i) + "*".repeat(2 * (n - i) - 1)));
        }
    }

    public static void main(String[] args) throws IOException {
        new three2444().solution();
    }
}
