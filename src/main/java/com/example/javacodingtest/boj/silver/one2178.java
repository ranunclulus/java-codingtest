package com.example.javacodingtest.boj.silver;
// https://www.acmicpc.net/problem/2178

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class one2178 {
    public static int[][] map;
    public static boolean[][] visited;
    public static int[][] distance;
    public static int[] dRow = {0, 0, -1, 1};
    public static int[] dCol = {1, -1, 0, 0};
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        distance = new int[n][m];

        for (int i = 0; i < n; i++) {
            String mapInfo = reader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(mapInfo.charAt(j)));
            }
        }

        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[] {0, 0});
        visited[0][0] = true;
        distance[0][0] = 1;

        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowCol = now[0];
            int nowRow = now[1];

            if (nowCol == n - 1 && nowRow == m - 1) break;

            for (int i = 0; i < 4; i++) {
                int nextCol = nowCol + dCol[i];
                int nextRow = nowRow + dRow[i];

                if (nextCol < 0 || nextCol >= n || nextRow < 0 || nextRow >= m) continue;
                if (visited[nextCol][nextRow]) continue;
                if (map[nextCol][nextRow] == 0) continue;

                visited[nextCol][nextRow] = true;
                toVisit.add(new int[] {nextCol, nextRow});
                distance[nextCol][nextRow] = distance[nowCol][nowRow] + 1;
            }
        }

        System.out.println(distance[n - 1][m - 1]);
    }

    public static void main(String[] args) throws IOException {
        new one2178().solution();
    }
}
