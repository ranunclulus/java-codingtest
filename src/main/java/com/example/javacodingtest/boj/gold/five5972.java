package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.01.12
 @link https://www.acmicpc.net/problem/5972
 @timecomplex
 @performance 41712kb 396ms
 @category
 @note
 */
public class five5972 {
    class Node implements Comparable<Node>{
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
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
    static int n, m;
    static List<List<Node>> adjList;
    static int[] cows;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int cow = Integer.parseInt(tokenizer.nextToken());
            adjList.get(from).add(new Node(to, cow));
            adjList.get(to).add(new Node(from, cow));
        }

        cows = new int[n + 1];

        Arrays.fill(cows, Integer.MAX_VALUE / 2);
        cows[1] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(1, 0));

        while (!priorityQueue.isEmpty()) {
            Node now = priorityQueue.poll();

            if (cows[now.node] < now.cost) continue;

            for (Node next : adjList.get(now.node)) {
               if (cows[next.node] > cows[now.node] + next.cost) {
                   cows[next.node] = cows[now.node] + next.cost;
                   priorityQueue.offer(new Node(next.node, cows[next.node]));
               }
            }
        }

        builder.append(cows[n]);
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new five5972().solution();
    }
}
