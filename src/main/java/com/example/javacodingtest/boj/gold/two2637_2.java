package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.06
 @link https://www.acmicpc.net/problem/2637
 @timecomplex
 @performance
 @category
 @note
 */
public class two2637_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static List<List<int[]>> adjList;
    static int[] xCount, yCount, result;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        xCount = new int[n + 1];
        yCount = new int[n + 1];
        result = new int[n + 1];
        adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int k = Integer.parseInt(tokenizer.nextToken());
            xCount[x]++;
            yCount[y]++;
            adjList.get(x).add(new int[] {y, k});
        }

        Deque<int[]> toVisit = new ArrayDeque<>();
        toVisit.add(new int[] {n, 1});
        result[n] = 1;
        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            for (int[] pre : adjList.get(now[0])) {
                result[pre[0]] += result[now[0]] * pre[1];
                yCount[pre[0]]--;
                if (yCount[pre[0]] == 0) toVisit.add(new int[] {pre[0], result[pre[0]]});
            }
        }

        for (int i = 1; i <= n; i++) {
            if (xCount[i] == 0) builder.append(i).append(" ").append(result[i]).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new two2637_2().solution();
    }
}
