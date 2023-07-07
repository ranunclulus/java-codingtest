package com.example.javacodingtest.boj.gold;
// https://www.acmicpc.net/problem/1238
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class three1238 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());

        int nodes = Integer.parseInt(token.nextToken());
        int edges = Integer.parseInt(token.nextToken());
        int destination = Integer.parseInt(token.nextToken()) - 1; // 목적지

        int[][] adjMat = new int[nodes][nodes];
        for (int[] row : adjMat) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < edges; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(edgeToken.nextToken()) - 1;
            int to = Integer.parseInt(edgeToken.nextToken()) - 1;
            int cost = Integer.parseInt(edgeToken.nextToken());
            adjMat[from][to] = cost;
        }


        boolean[][] visited = new boolean[nodes][nodes];
        int[][] dist = new int[nodes][nodes];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = 0; i < nodes; i++) {
            dist[i][i] = 0;
        }

        for (int start = 0; start < nodes; start++) {

            // 어떤 노드를 기준으로 edge를 볼 건지
            for (int i = 0; i < nodes; i++) {
                // 해당 노드에서 뻗은 edge중 가장 가까운 node 고르기
                int minDist = Integer.MAX_VALUE;
                int minDistNode = -1;
                for (int j = 0; j < nodes; j++) {
                    if (!visited[start][j] && dist[start][j] < minDist) {
                        minDistNode = j;
                        minDist = dist[start][j];
                    }
                }
                if (minDistNode == -1) break;

                visited[start][minDistNode] = true;

                // 거리 업데이트
                for (int j = 0; j < nodes; j++) {
                    if (adjMat[minDistNode][j] == -1) continue;
                    int cost = adjMat[minDistNode][j];
                    if (dist[start][j] > dist[start][minDistNode] + cost)
                        dist[start][j] = dist[start][minDistNode] + cost;
                }
            }
        }

        int[] result = new int[nodes];

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < nodes; i++) {
            result[i] += (dist[i][destination] + dist[destination][i]);
            if (result[i] > answer) answer = result[i];
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new three1238().solution();
    }
}




