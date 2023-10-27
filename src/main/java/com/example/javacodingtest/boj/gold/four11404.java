package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/11404
public class four11404 {
    private static int cityCount;
    private static int busCount;
    private static List<List<int[]>> bus;
    private static boolean[][] visited;
    private static int[][] distance;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        cityCount = Integer.parseInt(reader.readLine());
        busCount = Integer.parseInt(reader.readLine());
        bus = new ArrayList<>();
        distance = new int[cityCount][cityCount];
        visited = new boolean[cityCount][cityCount];

        for (int i = 0; i < cityCount; i++) {
            bus.add(new ArrayList<>());
        }

        for (int i = 0; i < busCount; i++) {
            StringTokenizer busToken = new StringTokenizer(reader.readLine());
            int startCity = Integer.parseInt(busToken.nextToken()) - 1;
            int endCity = Integer.parseInt(busToken.nextToken()) - 1;
            int cost = Integer.parseInt(busToken.nextToken());
            bus.get(startCity).add(new int[] {endCity, cost});
        }

        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < cityCount; i++) {
            distance[i][i] = 0;
        }

        for (int start = 0; start < cityCount; start++) {
            for (int i = 0; i < cityCount; i++) {
                if (start == i) continue;
                int minDist = Integer.MAX_VALUE;
                int minDistCity = -1;
                for (int j = 0; j < cityCount; j++) {
                    if (!visited[start][j] && distance[start][j] < minDist) {
                        minDist = distance[start][j];
                        minDistCity = j;
                    }
                }
                if (minDistCity == -1) break;
                visited[start][minDistCity] = true;

                for (int[] busRow : bus.get(minDistCity)) {
                    int endCity = busRow[0];
                    int cost = busRow[1];
                    if (distance[start][endCity] > distance[start][minDistCity] + cost) {
                        distance[start][endCity] = distance[start][minDistCity] + cost;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        for (int[] row : distance) {
            for (int result : row) {
                if (result == Integer.MAX_VALUE) sb.append(0);
                else sb.append(result);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

        reader.close();
    }

    public static void main(String[] args) throws IOException {
        new four11404().solution();
    }
}