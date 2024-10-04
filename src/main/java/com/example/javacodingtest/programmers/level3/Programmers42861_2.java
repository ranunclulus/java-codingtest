package com.example.javacodingtest.programmers.level3;

import java.util.*;

public class Programmers42861_2 {
    class Edge implements Comparable<Edge>{
        int node;
        int cost;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    List<Edge>[] edges;
    boolean[] visited;
    public int solution(int n, int[][] costs) {
        edges = new List[n];
        for(int i = 0; i < n; i++) {
            edges[i] = new LinkedList<>();
        }

        for(int[] edge : costs) {
            edges[edge[0]].add(new Edge(edge[1], edge[2]));
            edges[edge[1]].add(new Edge(edge[0], edge[2]));
        }

        PriorityQueue<Edge> queue = new PriorityQueue();
        visited = new boolean[n];
        queue.add(new Edge(0, 0));

        int answer = 0;
        while(!queue.isEmpty()) {
            Edge now = queue.poll();
            if (visited[now.node]) continue;
            visited[now.node] = true;
            answer += now.cost;
            for (Edge edge : edges[now.node]) {
                if (visited[edge.node]) continue;
                queue.add(edge);
            }
        }
        return answer;
    }
}
