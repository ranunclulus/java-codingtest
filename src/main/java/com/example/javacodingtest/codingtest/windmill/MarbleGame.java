package com.example.javacodingtest.codingtest.windmill;

import java.util.Scanner;

public class MarbleGame {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int count = dfs(n, m, k, 0);
        System.out.println(count);
    }

    private static int dfs(int n, int m, int k, int i) {
        if (i == k + 1) return 0; // 게임이 k번 진행된 경우
        if (n == 0 || m == 0) return 1; // 누군가 한 명이 0이 된 경우
        return (dfs(n - 1, m + 1, k, i + 1) + // m이 이김
                dfs(n + 1, m - 1, k, i + 1) + // n이 이김
                dfs(n, m, k, i + 1)); // 비김
    }
}
