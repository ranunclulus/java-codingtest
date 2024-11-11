package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.11
 @link https://www.acmicpc.net/problem/1167
 @timecomplex
 @performance 99844kb 860ms
 @category
 @note
 */
public class two1167_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static List<List<int[]>> edges;
    static int n;
    static int[] distance;
    static boolean[] visited;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int tokenCnt = tokenizer.countTokens();
            int from = Integer.parseInt(tokenizer.nextToken()) - 1;
            for (int j = 0; j < (tokenCnt - 2) / 2; j++) {
                int to = Integer.parseInt(tokenizer.nextToken()) - 1;
                int cost = Integer.parseInt(tokenizer.nextToken());
                edges.get(from).add(new int[] {to, cost});
            }
        }

        visited = new boolean[n];
        distance = new int[n];
        bfs(0);

        int maxDepthIndex = 0;
        for (int i = 1; i < n; i++) {
            if (distance[maxDepthIndex] < distance[i]) {
                maxDepthIndex = i;
            }
        }

        visited = new boolean[n];
        distance = new int[n];
        bfs(maxDepthIndex);

        Arrays.sort(distance);
        builder.append(distance[n - 1]);
        writer.write(builder.toString());
        writer.flush();
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
        new two1167_2().solution();
    }
}
