package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.17
 @link https://www.acmicpc.net/problem/2533
 @timecomplex
 @performance 416604kb, 2144ms
 @category
 @note
 */
public class three2533 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static List<List<Integer>> adjList;
    static int[][] dp;
    static boolean[] visited;


    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        visited = new boolean[n + 1];
        dp = new int[n + 1][2];

        dfs(1);

        builder.append(Math.min(dp[1][0], dp[1][1]));
        writer.write(builder.toString());
        writer.flush();
    }

    private void dfs(int current) {
        if (visited[current]) return;

        visited[current] = true;
        dp[current][0] = 0; // current를 선택하지 않으면 0개
        dp[current][1] = 1; // current를 선택하면 일단 1개

        for(int next : adjList.get(current)) {
            if (visited[next]) continue;

            dfs(next);
            dp[current][0] += dp[next][1];
            dp[current][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }

    public static void main(String[] args) throws IOException {
        new three2533().solution();
    }
}
