package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class one1912 {
    int colNum;
    int rowNum;
    int[] dCol = new int[]{0, 0, 1, -1};
    int[] dRow = new int[]{1, -1, 0, 0};
    int[][] map;
    boolean[][] visited;
    int maxArea = 0;
    int areaNum = 0;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        colNum = Integer.parseInt(infoToken.nextToken());
        rowNum = Integer.parseInt(infoToken.nextToken());

        map = new int[colNum][rowNum];
        visited = new boolean[colNum][rowNum];
        for (int i = 0; i < colNum; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < rowNum; j++) {
                map[i][j] = Integer.parseInt(infoToken.nextToken());
            }
        }

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(areaNum);
        System.out.println(maxArea);
    }

    private void bfs(int col, int row) {
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{col, row});
        visited[col][row] = true;
        int size = 1;

        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowCol = now[0];
            int nowRow = now[1];
            for (int i = 0; i < 4; i++) {
                int nextCol = nowCol + dCol[i];
                int nextRow = nowRow + dRow[i];

                if (nextCol < 0 || nextCol >= colNum) continue;
                if (nextRow < 0 || nextRow >= rowNum) continue;
                if (visited[nextCol][nextRow]) continue;

                visited[nextCol][nextRow] = true;
                if (map[nextCol][nextRow] == 1) {
                    size++;
                    toVisit.add(new int[]{nextCol, nextRow});
                }
            }
        }
        maxArea = Math.max(maxArea, size);
        areaNum++;
    }

    public static void main(String[] args) throws IOException {
        new one1912().solution();
    }
}
