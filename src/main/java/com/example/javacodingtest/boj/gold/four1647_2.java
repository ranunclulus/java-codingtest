package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/1647
 @timecomplex
 @performance 332616 KB, 1332 MS
 @category 크루스칼
 @note
 */
public class four1647_2 {
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
    static int m;
    static int[] parents;
    static PriorityQueue<Edge> edges;
    static int min;
    static int max = Integer.MIN_VALUE;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
        edges = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            edges.add(new Edge(start, end, cost));
        }

        while(!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (find(edge.start) != find(edge.end)) {
                min += edge.cost;
                max = edge.cost;
                union(edge.start, edge.end);
            }
        }
        builder.append(min - max);
        writer.write(builder.toString());
        writer.flush();
    }

    private void union(int start, int end) {
        int startParent = find(start);
        int endParent = find(end);

        if (startParent > endParent) {
            parents[startParent] = endParent;
        } else parents[endParent] = startParent;
    }

    private int find(int target) {
        if (parents[target] == target) return target;
        return parents[target] = find(parents[target]);
    }

    public static void main(String[] args) throws IOException {
        new four1647_2().solution();
    }
}
