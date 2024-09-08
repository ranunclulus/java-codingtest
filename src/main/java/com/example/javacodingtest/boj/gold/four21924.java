package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.09
 @link https://www.acmicpc.net/problem/30805
 @timecomplex
 @performance 167144kb, 916ms
 @category
 @note
 */
public class four21924 {
    class Node implements Comparable<Node>{
        int start;
        int end;
        int cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
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
    long totalCost, realCost;
    static int[] parents;
    static int edgeCnt;


    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken()) - 1;
            int end = Integer.parseInt(tokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(tokenizer.nextToken());
            totalCost += cost;
            pq.add(new Node(start, end, cost));
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (find(now.start) != find(now.end)) {
                edgeCnt++;
                union(now.start, now.end);
                realCost += now.cost;
            }
        }
        if (edgeCnt == n - 1) builder.append(totalCost - realCost);
        else builder.append(-1);
        writer.write(builder.toString());
        writer.flush();
    }


    private void union(int start, int end) {
        int parentStart = find(start);
        int parentEnd = find(end);
        if (parentStart < parentEnd) {
            parents[parentEnd] = parentStart;
        } else parents[parentStart] = parentEnd;
    }

    private int find(int target) {
        if (parents[target] == target) return target;
        else return parents[target] = find(parents[target]);
    }


    public static void main(String[] args) throws IOException {
        new four21924().solution();
    }
}
