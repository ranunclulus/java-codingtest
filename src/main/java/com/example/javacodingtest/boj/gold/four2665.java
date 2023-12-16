package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class four2665 {
    private int n;
    private int[][] map;
    private int[] dRow = new int[]{0, 0, 1, -1};
    private int[] dCol = new int[]{1, -1, 0, 0};
    private int[][] visited;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer inputToken = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(inputToken.nextToken());
        map = new int[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer mapToken = new StringTokenizer(reader.readLine());
            String inputString = mapToken.nextToken();
            for (int j = 0; j < n; j++) {
                map[i][j] = Character.getNumericValue(inputString.charAt(j));
            }
        }

        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{0, 0});
        for (int[] row : visited) {
            Arrays.fill(row, 999999);
        }
        visited[0][0] = 0;
        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowCol = now[0];
            int nowRow = now[1];

            for (int i = 0; i < 4; i++) {
                int nextCol = nowCol + dCol[i];
                int nextRow = nowRow + dRow[i];

                if (nextCol < 0 || nextCol >= n || nextRow < 0 || nextRow >= n) continue;
                if (visited[nowCol][nowRow] >= visited[nextCol][nextRow]) continue;

                if (map[nextCol][nextRow] == 0) {
                    visited[nextCol][nextRow] = visited[nowCol][nowRow] + 1;
                } else {
                    visited[nextCol][nextRow] = visited[nowCol][nowRow];
                }

                toVisit.add(new int[]{nextCol, nextRow});
            }
        }
        System.out.println(visited[n - 1][n - 1]);
    }

    public static void main(String[] args) throws IOException {
        new four2665().solution();
    }
}
