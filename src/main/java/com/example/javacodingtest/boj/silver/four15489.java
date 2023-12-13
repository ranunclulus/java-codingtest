package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class four15489 {
    private int[][] pascal = new int[31][31];

    public void solution() throws IOException {
        pascal[1][1] = 1;
        pascal[2][1] = 1;
        pascal[2][2] = 1;
        pascal[3][1] = 1;

        for (int i = 1; i <= 30; i++) {
            pascal[i][1] = 1;
            pascal[i][i] = 1;
        }

        for (int i = 3; i < 30; i++) {
            for (int j = 2; j < 30; j++) {
                pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
            }
        }

        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int w = sc.nextInt();

        int result = 0;
        for (int i = r; i < r + w; i++) {
            for (int j = 0; j <= i - r; j++) {
                result += pascal[i][c + j];
            }
        }
        System.out.println(result);

    }

    public static void main(String[] args) throws IOException {
        new four15489().solution();
    }
}
