package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class one1697 {

    public static ArrayList<Integer>[] canVisit = new ArrayList[100000 + 1];
    public static int[] visited = new int[100000 + 1];
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());

        int start = Integer.parseInt(infoToken.nextToken());
        int end = Integer.parseInt(infoToken.nextToken());

        for (int i = 0; i < canVisit.length; i++) {
            canVisit[i] = new ArrayList<>();
            canVisit[i].add(i - 1);
            canVisit[i].add(i + 1);
            canVisit[i].add(i * 2);
        }

        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }

        visited[start] = 0;
        bfs(start, end);

        if (start == end) visited[end] = 0;
        System.out.println(visited[end]);
    }

    private void bfs(int start, int end) {
        Queue<Integer> toVisit = new LinkedList<>();
        for (int pos : canVisit[start]) {
            if (pos < 0 || pos > 100000) continue;
            else {
                visited[pos] = 1;
                toVisit.offer(pos);
            }
        }
        while (!toVisit.isEmpty()) {
            int current = toVisit.poll();
            int count = visited[current];

            if (visited[end] != -1) return;

            for (int next : canVisit[current]) {
                if (next < 0 || next > 100000) continue;
                if (visited[next] != -1) continue;
                visited[next] = count + 1;
                toVisit.offer(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new one1697().solution();
    }
}
