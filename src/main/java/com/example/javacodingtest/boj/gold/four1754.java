package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class four1754 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int nodeCnt = Integer.parseInt(infoToken.nextToken());
        int edgeCnt = Integer.parseInt(infoToken.nextToken());
        int startNode = Integer.parseInt(reader.readLine()) - 1;

        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < nodeCnt; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeCnt; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(edgeToken.nextToken()) - 1;
            int to = Integer.parseInt(edgeToken.nextToken()) - 1;
            int cost = Integer.parseInt(edgeToken.nextToken());

            adjList.get(from).add(new int[]{to, cost});
        }

        boolean[] visited = new boolean[nodeCnt];
        int[] dist = new int[nodeCnt];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(o -> o[1])
        );

        minHeap.offer(new int[] {startNode, 0});

        while(!minHeap.isEmpty()) {
            int[] current = minHeap.poll();

            if(visited[current[0]]) continue;
            visited[current[0]] = true;

            for (int[] neighbor: adjList.get(current[0])) {
                int neighborVertex = neighbor[0];
                int neighborCost = neighbor[1];

                if(!visited[neighborVertex] &&
                        dist[neighborVertex] > current[1] + neighborCost) {
                    dist[neighborVertex] = current[1] + neighborCost;
                    minHeap.offer(new int[] {neighborVertex, dist[neighborVertex]});
                }
            }
        }
        for (int distance:dist) {
            if (distance == 2147483647) System.out.println("INF");
            else System.out.println(distance);
        }
    }

    public static void main(String[] args) throws IOException {
        new four1754().solution();
    }
}
