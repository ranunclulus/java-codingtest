package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class one21317 {
    private int n;
    private int[][] jump;
    private int k;
    private int result = Integer.MAX_VALUE;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        // 돌의 개수
        n = sc.nextInt();
        jump = new int[n][2];
        // 작은 점프와 큰 점프
        for (int i = 1; i < n; i++) {
            jump[i][0] = sc.nextInt();
            jump[i][1] = sc.nextInt();
        }
        // 매우 큰 점프
        k = sc.nextInt();

        dfs(1, false, 0);
        System.out.println(result);
    }

    private void dfs(int cur, boolean used, int value) {
        if (cur == n) {
            result = Math.min(result, value);
            return;
        }

        if (value > result) {
            return;
        }

        if (cur + 1 <= n) {
            dfs(cur + 1, used, value + jump[cur][0]);
        }

        if (cur + 2 <= n) {
            dfs(cur + 2, used, value + jump[cur][1]);
        }

        if (!used && cur + 3 <= n) {
            dfs(cur + 3, true, value + k);
        }
    }

    public static void main(String[] args) throws IOException {
        new one21317().solution();
    }
}
