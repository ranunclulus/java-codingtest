package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.03.05
 @link https://www.acmicpc.net/problem/20303
 @timecomplex 561496KB 788MS
 @performance
 @category
 @note
 */
public class two20303 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int N, M, K;
    static int[] parents, candies, count;
    static int maxCandy;
    static List<int[]> candiesList;
    static int[][] dp;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        parents = new int[N + 1];
        candies = new int[N + 1];
        count = new int[N + 1];
        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 1; i <= N; i++) {
            candies[i] = Integer.parseInt(tokenizer.nextToken());
            parents[i] = i;
            count[i] = 1;
        }

        for(int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            union(a, b);
        }

        sum();
        candiesList = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if (parents[i] == i) candiesList.add(new int[] { candies[i], count[i]});
        }

        dp = new int[candiesList.size() + 1][K + 1];
        for(int i = 1; i <= candiesList.size(); i++) {
            int candyCnt = candiesList.get(i - 1)[0];
            int friendCnt = candiesList.get(i - 1)[1];
            for (int j = 1; j <= K; j++) {
                if (friendCnt > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - friendCnt] + candyCnt);
                }
            }
        }

        builder.append(dp[candiesList.size()][K - 1]);
        writer.write(builder.toString());
        writer.flush();

    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA <= rootB) parents[rootB] = rootA;
        else parents[rootA] = rootB;
    }

    public int find(int target) {
        if (parents[target] == target) return target;
        return parents[target] = find(parents[target]);
    }

    public void sum() {
        for(int i = 1; i <= N; i++) {
            if (parents[i] != i) {
                int parent = find(i);
                candies[parent] += candies[i];
                count[parent] += count[i];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two20303().solution();
    }
}
