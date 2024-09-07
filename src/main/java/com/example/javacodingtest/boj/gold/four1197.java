package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.07
 @link https://www.acmicpc.net/problem/1197
 @timecomplex
 @performance 63256kb, 1376ms
 @category
 @note
 */
public class four1197 {
    class Node implements Comparable<Node>{
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int v, e;
    static List<List<Node>> edges;
    static boolean[] visited;
    static long totalCost;


    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        v = Integer.parseInt(tokenizer.nextToken());
        e = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[v];
        edges = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            edges.add(new LinkedList<>());
        }

        for (int i = 0; i < e; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()) - 1;
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(tokenizer.nextToken());
            edges.get(start).add(new Node(end, cost));
            edges.get(end).add(new Node(start, cost));
        }

        totalCost = 0;
        primAlgorithm(0, 0);
        builder.append(totalCost);
        writer.write(builder.toString());
        writer.flush();
    }

    private void primAlgorithm(int startVertex, int startCost) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(startVertex, startCost));

        while(!priorityQueue.isEmpty()) {
            Node nowNode = priorityQueue.poll();
            if (visited[nowNode.vertex]) continue;
            visited[nowNode.vertex] = true;
            totalCost += nowNode.cost;

            for(Node next : edges.get(nowNode.vertex)) {
                if (visited[next.vertex]) continue;
                priorityQueue.add(new Node(next.vertex, next.cost));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four1197().solution();
    }
}
