package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class three2501 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int count = 0;
        int result = 0;

        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                count++;
            }
            if (count == k) {
                result = i;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new three2501().solution();
    }
}
