package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.04
 @link https://www.acmicpc.net/status?user_id=morion002&problem_id=1414&from_mine=1
 @timecomplex
 @performance 14604kb 108ms
 @category
 @note
 */
public class three1414 {
    class Edge implements Comparable<Edge> {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
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
    static int n, total, use;
    static List<Edge>[] edges;
    static boolean[] visited;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            for (int j = 0; j < n; j++) {
                int value = input.charAt(j);
                if (value == 48) continue;
                else if (value - 97 >= 0) {
                    edges[i].add(new Edge(j, value - 96));
                    edges[j].add(new Edge(i, value - 96));
                    total += value - 96;
                }
                else {
                    edges[i].add(new Edge(j, value - 38));
                    edges[j].add(new Edge(i, value - 38));
                    total += value - 38;
                }
            }
        }

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(0, 0));
        visited = new boolean[n];
        while (!priorityQueue.isEmpty()) {
            if (finished()) break;
            Edge edge = priorityQueue.poll();

            if (visited[edge.node]) continue;
            visited[edge.node] = true;
            use += edge.cost;

            for (Edge e : edges[edge.node]) {
                if (visited[e.node]) continue;
                priorityQueue.add(e);
            }
        }

        if (finished()) builder.append(total - use);
        else builder.append(-1);
        writer.write(builder.toString());
        writer.flush();

    }

    private boolean finished() {
        for (int i = 0; i < n; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new three1414().solution();
    }
}
