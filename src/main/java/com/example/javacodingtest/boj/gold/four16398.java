package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;


/*
 @author ranuinclulus
 @since 2024.11.18
 @link https://www.acmicpc.net/problem/16398
 @timecomplex
 @performance 125284kb 912ms
 @category
 @note
 */
public class four16398 {
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
    static int[] parent;
    static PriorityQueue<Edge> edges;
    static long totalCost;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        edges = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                int start = i;
                int end = j;
                int cost = Integer.parseInt(tokenizer.nextToken());

                if (start <= end) continue;
                edges.add(new Edge(start, end, cost));
            }
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();

            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                totalCost += edge.cost;;
            }
        }
        builder.append(totalCost);
        writer.write(builder.toString());
        writer.flush();
    }

    public int find(int target) {
        if (parent[target] == target) return target;
        else return parent[target] = find(parent[target]);
    }

    public void union(int start, int end) {
        int startParent = find(start);
        int endParent = find(end);

        if (startParent <= endParent) parent[endParent] = startParent;
        else parent[startParent] = endParent;
    }

    public static void main(String[] args) throws IOException {
        new four16398().solution();
    }
}
