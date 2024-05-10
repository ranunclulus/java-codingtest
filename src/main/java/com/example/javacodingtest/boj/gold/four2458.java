package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class four2458 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());

        boolean[][] check = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(infoToken.nextToken()) - 1;
            int end = Integer.parseInt(infoToken.nextToken()) - 1;
            check[start][end] = true;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (check[i][k] && check[k][j]) check[i][j] = true;
                }
            }
        }

        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j] || check[j][i]) cnt[i]++;
            }
        }

        int result = 0;
        for (int num : cnt) {
            if (num == n - 1) result++;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new four2458().solution();
    }
}
