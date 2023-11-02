package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class two13458 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] testCenter = new int[n];

        for (int i = 0; i < n; i++) {
            testCenter[i] = sc.nextInt();
        }

        int b = sc.nextInt();
        int c = sc.nextInt();

        long result = n;

        for (int i = 0; i < n; i++) {
            if ((testCenter[i] - b) > 0) {
                result += ((testCenter[i] - b) / c);
                int rest = (testCenter[i] - b) % c;
                if (rest != 0) result++;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new two13458().solution();
    }
}
