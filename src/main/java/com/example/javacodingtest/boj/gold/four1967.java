package com.example.javacodingtest.boj.gold;
// https://www.acmicpc.net/problem/1967

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class four1967 {
    private static List<List<int[]>> tree;
    private static int nodeCnt;
    private static int diameter;
    private static boolean[] visited;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nodeCnt = Integer.parseInt(reader.readLine());
        tree = new ArrayList<>();
        for (int i = 0; i < nodeCnt; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < nodeCnt - 1; i++) {
            StringTokenizer treeToken = new StringTokenizer(reader.readLine());
            int fromNode = Integer.parseInt(treeToken.nextToken()) - 1;
            int toNode = Integer.parseInt(treeToken.nextToken()) - 1;
            int distance = Integer.parseInt(treeToken.nextToken());

            tree.get(fromNode).add(new int[] {toNode, distance});
            tree.get(toNode).add(new int[] {fromNode, distance});
        }

        diameter = 0;
        for (int i = 0; i < nodeCnt; i++) {
            visited = new boolean[nodeCnt];
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.println(diameter);
    }

    private void dfs(int index, int distance) {
        for (int[] node : tree.get(index)) {
            int nextNode = node[0];
            int nextDistance = node[1];
            if (!visited[nextNode]) {
                visited[nextNode] = true;
                dfs(nextNode, distance + nextDistance);
            }
            diameter = (diameter < distance) ? distance : diameter;
        }
    }


    public static void main(String[] args) throws IOException {
        new four1967().solution();
    }
}