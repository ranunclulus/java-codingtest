package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1916
public class five1916 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        List<List<int[]>> adjList = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int fromNode = Integer.parseInt(edgeToken.nextToken()) - 1;
            int toNode = Integer.parseInt(edgeToken.nextToken()) - 1;
            int weight = Integer.parseInt(edgeToken.nextToken());

            adjList.get(fromNode).add(new int[] {toNode, weight});
        }

        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int startNode = Integer.parseInt(infoToken.nextToken()) - 1;
        int endNode = Integer.parseInt(infoToken.nextToken()) - 1;

        boolean[] visited = new boolean[nodes];
        int[] dist = new int[nodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(o -> o[1])
        );

        minHeap.offer(new int[] {startNode, 0});

        while(!minHeap.isEmpty()) {
            int[] current = minHeap.poll();

            if (visited[current[0]]) continue;
            visited[current[0]] = true;

            for (int[] neighbor:adjList.get(current[0])) {
                int neighborVertex = neighbor[0];
                int neighberCost = neighbor[1];

                if (!visited[neighborVertex] && dist[neighborVertex] > current[1] + neighberCost) {
                    dist[neighborVertex] = current[1] + neighberCost;
                    minHeap.offer(new int[] { neighborVertex, dist[neighborVertex] });
                }
            }
        }
        System.out.println(dist[endNode]);
    }

    public static void main(String[] args) throws IOException {
        new five1916().solution();
    }
}
