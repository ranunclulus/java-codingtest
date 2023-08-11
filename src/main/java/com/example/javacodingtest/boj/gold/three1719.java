package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1719
public class three1719 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());
        int nodes = Integer.parseInt(token.nextToken());
        int edges = Integer.parseInt(token.nextToken());

        int[][] adjMat = new int[nodes][nodes];
        for (int[] row: adjMat) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < edges; i++) {
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(edgeToken.nextToken()) - 1;
            int to = Integer.parseInt(edgeToken.nextToken()) - 1;
            int cost = Integer.parseInt(edgeToken.nextToken());
            adjMat[from][to] = cost;
            adjMat[to][from] = cost;
        }

        boolean[][] visited = new boolean[nodes][nodes];
        int[][] dist = new int[nodes][nodes];
        for (int[] row: dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = 0; i < nodes; i++) {
            dist[i][i] = 0;
        }

        int[][] schedule = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            schedule[i][i] = -1;
        }

        // 출발지인 행 결정
        for (int start = 0; start < nodes; start++) {
            // 노드에 하나씩 접근하면서
            for (int i = 0; i < nodes; i++) {
                // edge가 가장 짧은 노드 결정하기
                int minDist = Integer.MAX_VALUE;
                int minDistNode = -1;
                for (int j = 0; j < nodes; j++) {
                    // 만약 노드를 방문한 적이 없고 시작점부터 j 노드까지의 길이가 가장 짧은 애 선택
                    if (!visited[start][j] && dist[start][j] < minDist) {
                        minDist = dist[start][j];
                        minDistNode = j;
                    }
                }
                // 짧은 노드 없으면 그냥 다음 노드로 패스
                if (minDistNode == -1) break;
                // 짧은 노드 발견했다면 방문 처리
                visited[start][minDistNode] = true;

                // start에서 가장 짧은 노드까지의 최단 거리는 구했으니
                // 가장 짧은 노드와 간선으로 연결된 노드들 업데이트
                for (int j = 0; j < nodes; j++) {
                    // 간선 연결이 없다면 패스
                    if (adjMat[minDistNode][j] == -1) continue;
                    // 아니라면 간선 비용 저장
                    int cost = adjMat[minDistNode][j];

                    // 원래 j까지 오는 거리랑 가장 짧은 노드의 간선을 타고 오는 것 중에 최단인 애 저장
                    // 만약 가장 짧은 노드 타고 오는 게 더 저렴하고 빠르다면
                    if(dist[start][j] > dist[start][minDistNode] + cost) {
                        dist[start][j] = dist[start][minDistNode] + cost;
                        schedule[start][j] = minDistNode + 1;
                        System.out.printf("%d %d %d \n", start + 1, j + 1, minDistNode + 1);
                    }
                }
            }

        }
        for (int[] row: schedule) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) throws IOException {
        new three1719().solution();
    }
}
