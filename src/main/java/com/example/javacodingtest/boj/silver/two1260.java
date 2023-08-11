package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1260
public class two1260 {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int maxNode = Integer.parseInt(infoToken.nextToken());
        int edges = Integer.parseInt(infoToken.nextToken());
        int startNode = Integer.parseInt(infoToken.nextToken());
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= maxNode; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            StringTokenizer edgeTokenizer = new StringTokenizer(reader.readLine());
            int fromNode = Integer.parseInt(edgeTokenizer.nextToken());
            int toNode = Integer.parseInt(edgeTokenizer.nextToken());
            adjList.get(fromNode).add(toNode);
            adjList.get(toNode).add(fromNode);
        }

        for (List<Integer> adjRow : adjList) {
            // 정렬해주는 Collections.sort 메소드
            Collections.sort(adjRow);
        }

        boolean[] visited = new boolean[maxNode + 1];
        List<Integer> visitOrder = new ArrayList<>();
        dfs(startNode, maxNode, adjList, visited, visitOrder);
        StringBuilder dfs = new StringBuilder();
        for (Integer node:visitOrder) {
            dfs.append(node + " ");
        }
        System.out.println(dfs);

        Arrays.fill(visited, false);
        visitOrder.clear();
        Queue<Integer> toVisit = new LinkedList<>();

        int next = startNode;
        toVisit.offer(next);
        // 큐가 비어있지 않은 동안 반복
        while (!toVisit.isEmpty()) {
            // 다음 방문 정점 회수
            next = toVisit.poll();

            // 이미 방문 했다면 다음 반복으로
            if (visited[next]) continue;

            // 방문했다 표시
            visited[next] = true;
            // 방문한 순서 기록
            visitOrder.add(next);

            // 다음 방문 대상을 검색한다.
            // adjList.get(next)에 담겨있는 미방문 요소들을 다 넣는다.
            List<Integer> canVisitList = adjList.get(next);
            for (Integer canVisit : canVisitList) {
                if (!visited[canVisit])
                    toVisit.offer(canVisit);
            }
        }

        StringBuilder bfs = new StringBuilder();
        for (Integer node:visitOrder) {
            bfs.append(node + " ");
        }
        System.out.println(bfs);
    }

    private void dfs(
            int next,
            int maxNode,
            List<List<Integer>> adjList,
            boolean[] visited,
            List<Integer> visitOrder
    ) {
        visited[next] = true;
        visitOrder.add(next);
        for (int i = 1; i <= maxNode; i++) {
            if(adjList.get(next).contains(i) && !visited[i])
                dfs(i, maxNode, adjList, visited, visitOrder);
        }

    }

    public static void main(String[] args) throws IOException {
        new two1260().solution();
    }
}
