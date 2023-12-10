package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class one22869 {
    private int n;
    private int k;
    int[] stairArr;
    private boolean[] dp;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        stairArr = new int[n];
        dp = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            stairArr[i] = sc.nextInt();
        }

        dfs(1);
        System.out.println(dp[n] ? "YES" : "NO");
    }

    private void dfs(int cur) {
        if (cur == n) {
            dp[cur] = true;
            return;
        }

        if (dp[cur]) {
            return;
        }

        dp[cur] = true;

        for (int i = cur + 1; i <= n; i++) {
            if ((i - cur) * (1 + Math.abs(stairArr[cur - 1] - stairArr[i - 1])) <= k) {
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new one22869().solution();
    }
}
