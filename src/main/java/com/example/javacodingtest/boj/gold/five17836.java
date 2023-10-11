package com.example.javacodingtest.boj.gold;
// https://www.acmicpc.net/problem/17836

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class five17836 {
    public static int n;
    public static int m;
    public static int[][] map;
    public static boolean[][][] visited;
    public static int[][] distance;
    public static int[] dRow = {0, 0, -1, 1};
    public static int[] dCol = {-1, 1, 0, 0};

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());
        int time = Integer.parseInt(infoToken.nextToken());
        boolean fail = false;

        map = new int[n][m];
        visited = new boolean[2][n][m];
        distance = new int[n][m];

        for (int i = 0; i < n; i++) {
            StringTokenizer mapToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(mapToken.nextToken());
            }
        }

        Queue<int[]> toVisit = new LinkedList<>();
        // col, row, gram
        toVisit.add(new int[] {0, 0, 0});
        visited[0][0][0] = true;
        distance[0][0] = 0;

        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowCol = now[0];
            int nowRow = now[1];
            int isPass = now[2];

            if (distance[nowCol][nowRow] > time) break; // 시간 초과
            if (nowCol == n - 1 && nowRow == m - 1) break; // 임무 완료

            for (int i = 0; i < 4; i++) {
                int nextCol = nowCol + dCol[i];
                int nextRow = nowRow + dRow[i];

                // 범위 검사
                if (nextCol < 0 || nextCol >= n || nextRow < 0 || nextRow >= m) continue;
                if (visited[isPass][nextCol][nextRow]) continue;

                // 검이 없고 벽
                if (isPass == 0 && map[nextCol][nextRow] == 1) continue;

                visited[isPass][nextCol][nextRow] = true;

                // 검 발견
                if (map[nextCol][nextRow] == 2) {
                    toVisit.add(new int[] {nextCol, nextRow, 1});
                } else {
                    toVisit.add(new int[] {nextCol, nextRow, isPass});
                }
                distance[nextCol][nextRow] = distance[nowCol][nowRow] + 1;
            }
        }

        if (fail || distance[n - 1][m - 1] == 0) {
            System.out.println("Fail");
        } else {
            System.out.println(distance[n - 1][m - 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        new five17836().solution();
    }
}
