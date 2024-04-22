package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class three2441 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print(" ".repeat(i) + "*".repeat(n - i));
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        new three2441().solution();
    }
}
