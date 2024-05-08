package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class one2098 {
    public int n;
    public int[][] adjArray;
    public int[][] dp;
    public int answer;
    public static final int INF = 16000000;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        adjArray = new int[n][n];
        dp = new int[n][(1 << n) - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer adjToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                adjArray[i][j] = Integer.parseInt(adjToken.nextToken());
            }
        }
        answer = tcp(0, 1);
        System.out.println(answer);
    }

    private int tcp(int now, int visit) {
        // 모든 도시를 지난 경우
        if (visit == (1 << n) - 1) {
            if (adjArray[now][0] == 0) return INF;
            return adjArray[now][0];
        }

        if (dp[now][visit] != -1) return dp[now][visit];
        dp[now][visit] = INF;

        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) == 0 && adjArray[now][i] != 0) {
                dp[now][visit] = Math.min(tcp(i, visit | (1 << i)) + adjArray[now][i], dp[now][visit]);
            }
        }
        return dp[now][visit];
    }

    public static void main(String[] args) throws IOException {
        new one2098().solution();
    }
}
