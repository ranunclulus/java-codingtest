package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2630

public class two2630 {
    public static int n;
    public static int[][] arr;
    public static int[] result = new int[2];

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer arrToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(arrToken.nextToken());
            }
        }

        recursive(0, 0, n);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    private void recursive(int startCol, int startRow, int size) {
        if (allSame(startCol, startRow, size)) {
            result[arr[startCol][startRow]]++;
        } else {
            int half = size / 2;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    recursive(startCol + i * half, startRow + j * half, half);
                }
            }
        }
    }

    private boolean allSame(int startCol, int startRow, int size) {
        int value = arr[startCol][startRow];
        for (int i = startCol; i < startCol + size; i++) {
            for (int j = startRow; j < startRow + size; j++) {
                if (arr[i][j] != value) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new two2630().solution();
    }
}
