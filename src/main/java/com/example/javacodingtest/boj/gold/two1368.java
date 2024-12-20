package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.04
 @link https://www.acmicpc.net/problem/1368
 @timecomplex
 @performance 25508kb 304ms
 @category
 @note
 */
public class two1368 {
    class Edge implements Comparable<Edge>{
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[] costs, parents;
    static PriorityQueue<Edge> priorityQueue;
    static int minCost;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        costs = new int[n + 1];
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            costs[i] = Integer.parseInt(reader.readLine());
            parents[i] = i;
        }


        priorityQueue = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= n; j++) {
                int value = Integer.parseInt(tokenizer.nextToken());
                if (i == j) priorityQueue.add(new Edge(0, i, costs[i]));
                else if (i < j) priorityQueue.add(new Edge(i, j, value));
            }
        }

        while (!priorityQueue.isEmpty()) {
            Edge now = priorityQueue.poll();

            if (find(now.start) == find(now.end)) continue;
            union(now.start, now.end);
            minCost += now.cost;
        }


        builder.append(minCost);
        writer.write(builder.toString());
        writer.flush();
    }

    private void union(int start, int end) {
        int startParent = find(start);
        int endParent = find(end);

        if (startParent < endParent) parents[endParent] = startParent;
        else parents[startParent] = endParent;
    }

    private int find(int node) {
        if (parents[node] == node) return node;
        else return parents[node] = find(parents[node]);
    }

    public static void main(String[] args) throws IOException {
        new two1368().solution();
    }
}
