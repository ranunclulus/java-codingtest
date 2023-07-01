package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class node implements Comparable<node>{
    int start, end, weight;
    public node(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(node o) {
        // TODO Auto-generated method stub
        return this.weight-o.weight;
    }
}

public class four1647 {
    int[] parent;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Node, Edge Tokenizer
        StringTokenizer neTokenizer = new StringTokenizer(reader.readLine());
        int nodeCount = Integer.parseInt(neTokenizer.nextToken());
        int edgeCount = Integer.parseInt(neTokenizer.nextToken());

        // Kruskal Algorithm은 서로소 집합의 개념을 이용해 서로 다른 두 정점을
        // 연결했을 때 사이클이 발생하는지 검사
        // 배열로 표현한 트리
        parent = new int[nodeCount];

        // 각각의 원소들이 자신을 대표자로
        for (int i = 0; i < nodeCount; i++) {
            parent[i] = i;
        }

        // 간선 정보 해독
        PriorityQueue<node> edges = new PriorityQueue<node>();
        for (int i = 0; i < edgeCount; i++) {
            StringTokenizer edgeTokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(edgeTokenizer.nextToken())-1;
            int end = Integer.parseInt(edgeTokenizer.nextToken())-1;
            int weight = Integer.parseInt(edgeTokenizer.nextToken());
            edges.add(new node(start, end, weight));

        }


        // 간선들을 가중치 순서대로 순회하면서 고른다
        int picked = 0;
        int totalWeight = 0;

        while(!edges.isEmpty()) {
            node nowEdge = edges.poll();
            if (findSet(nowEdge.start) != findSet(nowEdge.end)) {
                union(nowEdge.start, nowEdge.end);
                totalWeight += nowEdge.weight;
                picked++;
            }
            if (picked + 2 == nodeCount) break;
        }
        System.out.println(totalWeight);
    }
    public void union(int start, int end) {
        parent[findSet(end)] = findSet(start);
    }
    public int findSet(int node) {
        if(parent[node] != node) {
            return findSet(parent[node]);
        }
        return node;
    }
    public static void main(String[] args) throws IOException {
        new four1647().solution();
    }

}
/*
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4
 */

/*
7 9
1 2 29
1 5 75
2 3 35
2 6 34
3 4 7
4 6 23
4 7 13
5 6 53
6 7 25
 */

