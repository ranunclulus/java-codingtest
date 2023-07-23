package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class node implements Comparable<node> {
    int start;
    int end;
    int cost;

    public node(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(node o) {
        return this.cost - o.cost;
    }
}
public class four1647 {
    private int[] parent;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Node, Edge Tokenizer
        String[] infoToken = reader.readLine().split(" ");
        int nodeCount = Integer.parseInt(infoToken[0]);
        int edgeCount = Integer.parseInt(infoToken[1]);

        // Kruskal Algorithm은 서로소 집합의 개념을 이용해 서로 다른 두 정점을 연결했을 때의 사이클 검사
        parent = new int[nodeCount + 1];

        // 각각의 원소들이 자신을 대표자로 하는 집합으로 만들기
        for (int i = 0; i <= nodeCount; i++) {
            parent[i] = i;
        }

        // 간선 정보 해독
        PriorityQueue<node> edges = new PriorityQueue<>();
        for (int i = 0; i < edgeCount; i++) {
            String[] edgeToken = reader.readLine().split(" ");
            int start = Integer.parseInt(edgeToken[0]);
            int end = Integer.parseInt(edgeToken[1]);
            int cost = Integer.parseInt(edgeToken[2]);
            edges.add(new node(start, end, cost));
        }


        // 간선들을 가중치 순서대로 순회하면서 고르기
        int max = 0; // 선택한 간선 중 가장 큰 비용
        int totalWeight = 0; // 총 비용

        while(!edges.isEmpty()) {
            node current = edges.poll();
            int startParent = findSet(current.start);
            int endParent = findSet(current.end);
            if (startParent != endParent) {
                union(current.start, current.end);
                totalWeight += current.cost;
                max = current.cost;
            }
        }
        System.out.println(totalWeight - max);
    }

    // union: 둘을 합하는 연산
    public void union(int start, int end) {
        int startParent = findSet(start);
        int endParent = findSet(end);
        parent[endParent] = startParent;
    }

    // findSet: 내 부모가 나일 때까지 재귀 호출
    public int findSet(int node) {
        if (parent[node] != node) {
            return parent[node] = findSet(parent[node]);
        }
        else return node;
    }

    public static void main(String[] args) throws IOException {
        new four1647().solution();
    }
}
