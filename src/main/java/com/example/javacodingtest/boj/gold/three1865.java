package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class three1865 {
    public int nodeCnt;
    public int edgeCnt;
    public List<List<int[]>> edges;
    public int[] dist;
    static final int INF = 987654321;
    public int warmholeCnt;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        for (int i = 0; i < testCase; i++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            nodeCnt = Integer.parseInt(infoToken.nextToken());
            edgeCnt = Integer.parseInt(infoToken.nextToken());
            warmholeCnt = Integer.parseInt(infoToken.nextToken());

            edges = new ArrayList<>();
            for (int j = 0; j < nodeCnt; j++) {
                edges.add(new ArrayList<>());
            }

            for (int j = 0; j < edgeCnt; j++) {
                StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
                int startNode = Integer.parseInt(edgeToken.nextToken()) - 1;
                int endNode = Integer.parseInt(edgeToken.nextToken()) - 1;
                int time = Integer.parseInt(edgeToken.nextToken());
                edges.get(startNode).add(new int[] {endNode, time});
                edges.get(endNode).add(new int[] {startNode, time});
            }

            for (int j = 0; j < warmholeCnt; j++) {
                StringTokenizer warmholeToken = new StringTokenizer(reader.readLine());
                int startNode = Integer.parseInt(warmholeToken.nextToken()) - 1;
                int endNode = Integer.parseInt(warmholeToken.nextToken()) - 1;
                int time = Integer.parseInt(warmholeToken.nextToken()) * (-1);
                edges.get(startNode).add(new int[]{endNode, time});
            }

            boolean minusCycle = false;
            dist = new int[nodeCnt];
            for (int j = 0; j < nodeCnt; j++) {
                if (bellmanFord(i)) {
                    minusCycle = true;
                    System.out.println("YES");
                    break;
                }
            }

            if (!minusCycle) {
                System.out.println("NO");
            }
        }
        reader.close();
    }

    private boolean bellmanFord(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean update = false;

        for (int i = 0; i < nodeCnt - 1; i++) {
            update = false;

            for (int j = 0; j < nodeCnt; j++) {
                for (int[] edge : edges.get(j)) {
                    if (dist[edge[0]] > dist[j] + edge[1]) {
                        dist[edge[0]] = dist[j] + edge[1];
                        update = true;
                    }
                }
            }
            if (!update) {
                break;
            }
        }

        if (update) {
            for (int i = 0; i < nodeCnt; i++) {
                for (int[] edge : edges.get(i)) {
                    if (dist[edge[0]] > dist[i] + edge[1]) {
                        dist[edge[0]] = dist[i] + edge[1];
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        new three1865().solution();
    }
}