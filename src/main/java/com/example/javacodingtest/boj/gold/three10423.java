package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.05
 @link https://www.acmicpc.net/problem/10423
 @timecomplex
 @performance 48032kb 436ms
 @category
 @note
 */
public class three10423 {
    class Edge implements Comparable<Edge> {
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
    static PriorityQueue<Edge> priorityQueue;
    static int n, m, k, totalCost;
    static int[] parents, power;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        power = new int[k];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < k; i++) {
            power[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < k; i++) {
            parents[power[i]] = power[0];
        }

        priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            priorityQueue.add(new Edge(
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())));
        }

        while (!priorityQueue.isEmpty()) {
            Edge now = priorityQueue.poll();
            if (find(now.start) != find(now.end)) {
                union(now.start, now.end);
                totalCost += now.cost;
            }
        }

        builder.append(totalCost);
        writer.write(builder.toString());
        writer.flush();
    }

    private void union(int start, int end) {
        int startParent = find(start);
        int endParent = find(end);

        if (startParent < endParent) parents[endParent] = startParent;
        else parents[startParent] = endParent;
    }

    private int find(int target) {
        if (parents[target] == target) return target;
        else return parents[target] = find(parents[target]);
    }

    public static void main(String[] args) throws IOException {
        new three10423().solution();
    }
}
