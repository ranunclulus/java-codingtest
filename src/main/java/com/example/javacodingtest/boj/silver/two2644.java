package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class two2644 {
    int peopleNum;
    int start;
    int end;
    int[][] relationship;
    boolean[][] visited;
    int[] bridge;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        peopleNum = Integer.parseInt(reader.readLine());

        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        start = Integer.parseInt(infoToken.nextToken());
        end = Integer.parseInt(infoToken.nextToken());

        int relationshipNum = Integer.parseInt(reader.readLine());
        relationship = new int[peopleNum + 1][peopleNum + 1];
        visited = new boolean[peopleNum + 1][peopleNum + 1];
        bridge = new int[peopleNum + 1];
        Arrays.fill(bridge, -1);
        for (int i = 0; i < relationshipNum; i++) {
            StringTokenizer relationshipToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(relationshipToken.nextToken());
            int to = Integer.parseInt(relationshipToken.nextToken());
            relationship[from][to] = 1;
            relationship[to][from] = 1;
        }

        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.add(start);
        bridge[start] = 0;
        while (!toVisit.isEmpty()) {
            int now = toVisit.poll();
            for (int i = 1; i <= peopleNum; i++) {
                if (relationship[now][i] == 1 && !visited[now][i] && !visited[i][now]) {
                    toVisit.add(i);
                    bridge[i] = bridge[now] + 1;
                    visited[now][i] = true;
                    visited[i][now] = true;
                }
            }
        }
        System.out.println(bridge[end]);
    }

    public static void main(String[] args) throws IOException {
        new two2644().solution();
    }
}
