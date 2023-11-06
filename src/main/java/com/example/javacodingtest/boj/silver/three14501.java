package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class three14501 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] schedule = new int[n][2];
        int[] result = new int[n + 1];

        for (int i = 0; i < n; i++) {
            schedule[i][0] = sc.nextInt();
            schedule[i][1] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            int time = schedule[i][0];
            int cost = schedule[i][1];
            if ((i + time) <= n ) {
                result[i + time] = Math.max(result[i] + cost, result[i + time]);
            }
            result[i + 1] = Math.max(result[i + 1], result[i]);
        }
        System.out.println(result[n]);
    }

    public static void main(String[] args) throws IOException {
        new three14501().solution();
    }
}