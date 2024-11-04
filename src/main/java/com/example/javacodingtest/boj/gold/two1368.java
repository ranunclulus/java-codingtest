package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.04
 @link https://www.acmicpc.net/problem/1368
 @timecomplex
 @performance
 @category
 @note
 */
public class two1368 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[] costs, parents;
    static int[][] adjArray;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        costs = new int[n];
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(reader.readLine());
            parents[i] = i;
        }

        adjArray = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                adjArray[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }


    }

    public int find(int target) {
        if (parents[target] == target) return target;
        else return parents[target] = find(parents[target]);
    }

    public void union(int first, int second) {
        int firstParent = find(first);
        int secondParent = find(second);

        if (firstParent < secondParent) parents[secondParent] = firstParent;
        else parents[firstParent] = secondParent;
    }

    public static void main(String[] args) throws IOException {
        new two1368().solution();
    }
}
