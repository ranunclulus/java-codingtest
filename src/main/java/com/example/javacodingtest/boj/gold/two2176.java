package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.04.08
 @link https://www.acmicpc.net/problem/2176
 @timecomplex
 @performance 22824KB 240MS
 @category
 @note
 */
public class two2176 {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int N, M;
    static List<List<Edge>> adjList;
    static int[] distance, dp;
    static int START = 0, END = 1;

    public void solution() throws IOException {
        init();
        dijkstra();
        counting();
        builder.append(dp[START]);
        writer.write(builder.toString());
        writer.flush();
    }

    private void dijkstra() {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[END] = 0;
        boolean[] visited = new boolean[N];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(END, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited[edge.node]) continue;
            visited[edge.node] = true;

            for(Edge neighbor : adjList.get(edge.node)) {
                if (distance[neighbor.node] > distance[edge.node] + neighbor.weight) {
                    distance[neighbor.node] = distance[edge.node] + neighbor.weight;
                    pq.add(new Edge(neighbor.node, distance[neighbor.node]));
                }
            }
        }

    }

    public void counting() {
        dp[END] = 1;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(new Edge(i, distance[i]));
        }
        pq.poll();

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            for(Edge neighbor : adjList.get(edge.node)) {
                if (distance[neighbor.node] < distance[edge.node]) {
                    dp[edge.node] += dp[neighbor.node];
                }
            }
        }
    }

    public void init() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;
            int c = Integer.parseInt(tokenizer.nextToken());

            adjList.get(a).add(new Edge(b, c));
            adjList.get(b).add(new Edge(a, c));
        }

        distance = new int[N];
        dp = new int[N];
    }

    class Edge implements Comparable<Edge> {
        int node;
        int weight;
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        new two2176().solution();
    }
}
