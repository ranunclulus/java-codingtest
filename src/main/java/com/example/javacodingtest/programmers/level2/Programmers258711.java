package com.example.javacodingtest.programmers.level2;

import java.util.*;

/*
 @author ranuinclulus
 @since 2025.02.28
 @link https://school.programmers.co.kr/learn/courses/30/lessons/258711
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers258711 {
    static int nodeCount, donut, stick, eight, start;
    static List<List<Integer>> edgeGraph = new ArrayList<>();
    static int[] entranceCount, answer;
    static boolean[] visited;

    public int[] solution(int[][] edges) {

        for(int[] edge : edges) {
            nodeCount = Math.max(nodeCount, Math.max(edge[0], edge[1]));
        }

        for(int i = 0; i <= nodeCount; i++) {
            edgeGraph.add(new ArrayList<>());
        }

        entranceCount = new int[nodeCount + 1];
        for(int[] edge : edges) {
            edgeGraph.get(edge[0]).add(edge[1]);
            entranceCount[edge[1]]++;
        }

        start = findStart();
        List<Integer> startWay = edgeGraph.get(start);

        for(int way : startWay) {
            entranceCount[way]--;
        }

        visited = new boolean[nodeCount + 1];
        for(int way : startWay) {
            if(visited[way]) continue;
            int type = exploreGraph(way);
            if (type == 0) donut++;
            else if (type == 1) stick++;
            else if (type == 2) eight++;
        }

        answer = new int[] {start, donut, stick, eight};
        return answer;
    }

    public int exploreGraph(int startNode) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(startNode);
        visited[startNode] = true;

        while (!deque.isEmpty()) {
            int node = deque.poll();
            if (edgeGraph.get(node).size() == 2 && entranceCount[node] == 2) {
                return 2;
            } else if (edgeGraph.get(node).isEmpty()) {
                return 1;
            } else {
                for(int neighbor : edgeGraph.get(node)) {
                    if (visited[neighbor]) continue;
                    deque.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        return 0;
    }

    public int findStart() {
        for(int i = 1; i <= nodeCount; i++) {
            if (edgeGraph.get(i).size() >= 2 && entranceCount[i] == 0)
                return i;
        }
        return 0;
    }
}
