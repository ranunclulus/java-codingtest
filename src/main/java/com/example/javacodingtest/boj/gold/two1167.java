package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1167
public class two1167 {
    private static int nodeCnt;
    private static List<List<int[]>> edges;
    private static boolean[] visited;
    private static int[] distance;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nodeCnt = Integer.parseInt(reader.readLine());
        edges = new ArrayList<>();
        for (int i = 0; i < nodeCnt; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < nodeCnt; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int tokenCnt = edgeToken.countTokens();
            int from = Integer.parseInt(edgeToken.nextToken()) - 1;
            for (int j = 0; j < (tokenCnt - 2) / 2; j++) {
                int to = Integer.parseInt(edgeToken.nextToken()) - 1;
                int cost = Integer.parseInt(edgeToken.nextToken());
                edges.get(from).add(new int[] {to, cost});
            }
        }

        int maxIndex = 0;

        visited = new boolean[nodeCnt];
        distance = new int[nodeCnt];
        bfs(0);

        for (int i = 1; i < nodeCnt; i++) {
            if (distance[maxIndex] < distance[i]) {
                maxIndex = i;
            }
        }

        visited = new boolean[nodeCnt];
        distance = new int[nodeCnt];
        bfs(maxIndex);

        Arrays.sort(distance);
        System.out.println(distance[nodeCnt - 1]);
    }

    private void bfs(int startNode) {
        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.add(startNode);
        visited[startNode] = true;
        while (!toVisit.isEmpty()) {
            int nowNode = toVisit.poll();
            for(int[] canVisit : edges.get(nowNode)) {
                int canNode = canVisit[0];
                int canCost = canVisit[1];
                if (!visited[canNode]) {
                    visited[canNode] = true;
                    toVisit.add(canNode);
                    distance[canNode] = distance[nowNode] + canCost;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two1167().solution();
    }
}