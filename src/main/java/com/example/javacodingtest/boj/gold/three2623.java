package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.04
 @link https://www.acmicpc.net/problem/2623
 @timecomplex
 @performance 14708kb 136ms
 @category
 @note
 */
public class three2623 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k, start, end, count;
    static int[] indegree;
    static List<List<Integer>> adjList;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        indegree = new int[n];

        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            k = Integer.parseInt(tokenizer.nextToken());

            start = Integer.parseInt(tokenizer.nextToken()) - 1;
            for (int j = 0; j < k - 1; j++) {
                end = Integer.parseInt(tokenizer.nextToken()) - 1;
                adjList.get(start).add(end);
                start = end;
                indegree[end]++;
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) deque.add(i + 1);
        }

        while (!deque.isEmpty()) {
            int now = deque.poll();
            count++;
            builder.append(now).append('\n');

            for(int next : adjList.get(now - 1)) {
                indegree[next]--;
                if (indegree[next] == 0) deque.add(next + 1);
            }
        }

        if (count != n) builder.replace(0, builder.length(), "0");
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three2623().solution();
    }
}
