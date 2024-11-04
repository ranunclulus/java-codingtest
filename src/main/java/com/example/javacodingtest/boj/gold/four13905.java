package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.04
 @link https://www.acmicpc.net/problem/13905
 @timecomplex
 @performance 131084kb 664ms
 @category
 @note
 */
public class four13905 {
    class Edge implements Comparable<Edge>{
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return -Integer.compare(this.cost, o.cost);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, start, end, max;
    static List<Edge>[] edges;
    static boolean[] visited;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        start = Integer.parseInt(tokenizer.nextToken()) - 1;
        end = Integer.parseInt(tokenizer.nextToken()) - 1;

        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()) - 1;
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(tokenizer.nextToken());

            edges[start].add(new Edge(end, cost));
            edges[end].add(new Edge(start, cost));
        }

        max = Integer.MAX_VALUE;
        visited = new boolean[n];

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(start, Integer.MAX_VALUE));
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();

            if (visited[edge.node]) continue;
            visited[edge.node] = true;

            if (edge.cost < max) max = edge.cost;
            if (edge.node == end) break;

            for (Edge e : edges[edge.node]) {
                if (!visited[e.node]) priorityQueue.add(e);
            }
        }

        if (!visited[end]) max = 0;
        builder.append(max);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four13905().solution();
    }
}
