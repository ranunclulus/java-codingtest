package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class three2443 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println(String.format(" ".repeat(i) + "*".repeat(2 * (n - i) - 1)));
        }
    }

    public static void main(String[] args) throws IOException {
        new three2443().solution();
    }
}
