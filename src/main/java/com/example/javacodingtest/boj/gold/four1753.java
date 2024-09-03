package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.02
 @link
 @timecomplex
 @performance
 @category
 @note
 */

public class four1753 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int nodeCnt, edgeCnt, startNode;
    static List<List<int[]>> adjList;
    static boolean[] visited;
    static int[] dist;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        nodeCnt = Integer.parseInt(tokenizer.nextToken());
        edgeCnt = Integer.parseInt(tokenizer.nextToken());
        startNode = Integer.parseInt(reader.readLine()) - 1;

        adjList = new ArrayList<>();
        for (int i = 0; i < nodeCnt; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeCnt; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken()) - 1;
            int to = Integer.parseInt(tokenizer.nextToken()) - 1;
            int cost = Integer.parseInt(tokenizer.nextToken());

            adjList.get(from).add(new int[]{to, cost});
        }

        visited = new boolean[nodeCnt];
        dist = new int[nodeCnt];
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
            if (distance == 2147483647) builder.append("INF");
            else builder.append(distance).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four1753().solution();
    }
}
