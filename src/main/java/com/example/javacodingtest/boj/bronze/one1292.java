package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class one1292 {
    public void solution() throws IOException {
        int[] result = new int[1002];

        result[1] = 1;
        for (int i = 2; (i - 1) * i / 2 + 1 < 1001; i++) {
            for (int j = 0; j < i; j++) {
                int index = (i - 1) * i / 2 + 1;
                if ((i - 1) * i / 2 + 1 + j > 1001) continue;
                result[index + j] = result[index + j - 1] + i;
            }
        }
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();

        System.out.println(result[end] - result[start - 1]);

    }

    // 1 3 5 8 11 14 18 22 26 30 35 40 45 50 55
    public static void main(String[] args) throws IOException {
        new one1292().solution();
    }
}
