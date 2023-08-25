package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class four1504 {
    private int nodes;
    private List<List<int []>> adjList;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        nodes = Integer.parseInt(infoToken.nextToken());
        int edges = Integer.parseInt(infoToken.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int fromNode = Integer.parseInt(edgeToken.nextToken()) - 1;
            int toNode = Integer.parseInt(edgeToken.nextToken()) - 1;
            int cost = Integer.parseInt(edgeToken.nextToken());

            adjList.get(fromNode).add(new int[] {toNode, cost});
            adjList.get(toNode).add(new int[] {fromNode, cost});
        }

        StringTokenizer vertexToken = new StringTokenizer(reader.readLine());
        int vertexOne = Integer.parseInt(vertexToken.nextToken()) - 1;
        int vertexTwo = Integer.parseInt(vertexToken.nextToken()) - 1;

        int distOne = dijkstra(0, vertexOne) + dijkstra(vertexOne, vertexTwo) + dijkstra(vertexTwo, nodes - 1);
        int distTwo = dijkstra(0, vertexTwo) + dijkstra(vertexTwo, vertexOne) + dijkstra(vertexOne, nodes - 1);

        int result = Math.min(distOne, distTwo);

        if(result < 0 || result >= Integer.MAX_VALUE || nodes == 0 || edges == 0) System.out.println(-1);
        else System.out.println(result);
    }

    public int dijkstra(int start, int end) {
        if (start == end) return 0;
        int[] dist = new int[nodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(o -> o[1])
        );
        minHeap.offer(new int[] {start, 0});

        while(!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            for(int[] neighbor : adjList.get(current[0])) {
                int neighborVertex = neighbor[0];
                int neighborCost = neighbor[1];

                if(dist[neighborVertex] > current[1] + neighborCost) {
                    dist[neighborVertex] = current[1] + neighborCost;
                    minHeap.offer(new int[] {neighborVertex, dist[neighborVertex]});
                }
            }
        }
        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        new four1504().solution();
    }
}
