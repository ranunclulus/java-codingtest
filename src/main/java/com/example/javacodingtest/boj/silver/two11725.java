package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class two11725 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodeCnt = Integer.parseInt(reader.readLine());
        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < nodeCnt; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < nodeCnt - 1; i++) {
            StringTokenizer treeToken = new StringTokenizer(reader.readLine());
            int nodeOne = Integer.parseInt(treeToken.nextToken()) - 1;
            int nodeTwo = Integer.parseInt(treeToken.nextToken()) - 1;

            tree.get(nodeOne).add(nodeTwo);
            tree.get(nodeTwo).add(nodeOne);
        }

        boolean[] visited = new boolean[nodeCnt];
        int[] parent = new int[nodeCnt];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int currentNode = queue.remove();
            for (int node : tree.get(currentNode)) {
                if (!visited[node]) {
                    queue.add(node);
                    visited[node] = true;
                    parent[node] = currentNode;
                }
            }
        }
        for (int i = 1; i < nodeCnt; i++) {
            System.out.println(parent[i] + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        new two11725().solution();
    }
}
