package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.19
 @link https://www.acmicpc.net/problem/1647
 @timecomplex
 @performance 344468kb 1508ms
 @category 크루스칼
 @note
 */
public class four1647_3 {
    class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        Edge(int start, int end, int cost) {
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
    static int n, m, totalCost, lastCost;
    static PriorityQueue<Edge> priorityQueue;
    static int[] parents;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()) - 1;
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(tokenizer.nextToken());

            priorityQueue.offer(new Edge(start, end, cost));
        }

        while (!priorityQueue.isEmpty()) {
            Edge now = priorityQueue.poll();
            if (find(now.start) != find(now.end)) {
                union(now.start, now.end);
                totalCost += now.cost;
                lastCost = Math.max(lastCost, now.cost);
            }
        }

        builder.append(totalCost - lastCost);
        writer.write(builder.toString());
        writer.flush();
    }

    private void union(int start, int end) {
        int startParent = find(start);
        int endParent = find(end);

        if (startParent <= endParent) parents[endParent] = startParent;
        else parents[startParent] = endParent;
    }

    private int find(int target) {
        if (parents[target] == target) return target;
        return parents[target] = find(parents[target]);
    }

    public static void main(String[] args) throws IOException {
        new four1647_3().solution();
    }
}
