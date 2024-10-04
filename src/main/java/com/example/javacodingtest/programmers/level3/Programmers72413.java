package com.example.javacodingtest.programmers.level3;

import java.util.*;
public class Programmers72413 {
    class Node implements Comparable<Node>{
        int node;
        int cost;

        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    int[] distance;
    List<List<Node>> edges = new LinkedList<>();

    public int solution(int N, int[][] road, int K) {
        for(int i = 0; i <= N; i++) {
            edges.add(new LinkedList<>());
        }

        distance = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < road.length; i++) {
            edges.get(road[i][0]).add(new Node(road[i][1], road[i][2]));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 0));
        distance[1] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (distance[now.node] < now.cost) continue;

            for (Node next : edges.get(now.node)) {
                if (distance[next.node] > distance[now.node] + next.cost) {
                    distance[next.node] = distance[now.node] + next.cost;
                    queue.add(new Node(next.node, distance[next.node]));
                }
            }
        }

        int count = 0;
        for(int dis : distance) {
            if (dis <= K) count++;
        }
        System.out.println(Arrays.toString(distance));
        return count;
    }
}
