package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class four18223 {
    static int nodes;
    static List<List<int[]>> adjList = new ArrayList<>();

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer info = new StringTokenizer(reader.readLine());

        // 정점의 개수, 간선의 개수, 시작 정점
        nodes = Integer.parseInt(info.nextToken());
        int edges = Integer.parseInt(info.nextToken());
        int end = Integer.parseInt(info.nextToken()) - 1;

        for (int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(edgeToken.nextToken()) - 1;
            int to = Integer.parseInt(edgeToken.nextToken()) - 1;
            int cost = Integer.parseInt(edgeToken.nextToken());

            adjList.get(from).add(new int[]{to, cost});
            adjList.get(to).add(new int[]{from, cost});
        }

        if (dikstra(0, nodes - 1) == dikstra(0, end) + dikstra(end, nodes - 1)) {
            System.out.println("SAVE HIM");
        } else System.out.println("GOOD BYE");

    }

    public static int dikstra(int start, int end) {
        if (start == end) return 0;
        // 우선순위 큐 활용
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(o -> o[1])
        );
        int dist[] = new int[nodes];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 처음 가는 곳은 시작지점
        minHeap.offer(new int[]{start, 0});

        // 우선순위 큐에 더이상 방문할 정점이 기록되지 않을때까지
        while (!minHeap.isEmpty()) {
            // 최소힙에서 꺼내면 도달 가능한 가장 가까운 지점
            int[] current = minHeap.poll();

            for (int[] neighbor : adjList.get(current[0])) {
                int neighborVertex = neighbor[0];
                int neighborCost = neighbor[1];

                // 방문하지 않은 정점이고
                // 현재까지 기록된 거리보다 새로 가는 거리가 더 짧다.
                if (dist[neighborVertex] > current[1] + neighborCost) {
                    dist[neighborVertex] = current[1] + neighborCost;
                    minHeap.offer(new int[]{neighborVertex, dist[neighborVertex]});
                }
            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        new four18223().solution();
    }
}
