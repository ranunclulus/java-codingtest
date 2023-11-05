package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class three11659 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] number = new int[n];

        number[0] = sc.nextInt();
        for (int i = 1; i < n; i++) {
            number[i] = number[i - 1] + sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            if (start == 1) {
                System.out.println(number[end - 1]);
            }
            else {
                System.out.println(number[end - 1] - number[start - 2]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three11659().solution();
    }
}
