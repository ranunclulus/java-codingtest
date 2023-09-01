package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14502
public class four14502 {
    private int[] drow = new int[] {0, 0, 1, -1};
    private int[] dcol = new int[] {1, -1, 0, 0};
    private int rowNum;
    private int colNum;
    private int[][] map;
    private List<int[]> canWall;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        rowNum = Integer.parseInt(infoToken.nextToken());
        colNum = Integer.parseInt(infoToken.nextToken());
        map = new int[rowNum][colNum];

        canWall = new ArrayList<>();

        for (int i = 0; i < rowNum; i++) {
            StringTokenizer mapToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < colNum; j++) {
                map[i][j] = Integer.parseInt(mapToken.nextToken());
                if (map[i][j] == 0) canWall.add(new int[] {i, j});
            }
        }

        int wallSize = canWall.size();
        int maxSafe = 0;

        int[] makeWall = new int[3];

        for (int i = 0; i < wallSize - 2; i++) {
            makeWall[0] = i;
            for (int j = i + 1; j < wallSize - 1; j++) {
                makeWall[1] = j;
                for (int k = j + 1; k < wallSize; k++) {
                    makeWall[2] = k;
                    maxSafe = Math.max(maxSafe, bfs(makeWall));
                }
            }
        }
        System.out.println(maxSafe);
    }

    private int bfs(int[] makeWall) {
        int[][] copyMap = new int[rowNum][colNum];
        Queue<int[]> virus = new LinkedList<>();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                copyMap[i][j] = map[i][j];
                if (copyMap[i][j] == 2) virus.add(new int[] {i, j});
            }
        }
        boolean[][] visited = new boolean[rowNum][colNum];
        int safeZone = 0;
        // 벽 만들기
        for (int wall : makeWall) {
            int[] position = canWall.get(wall);
            copyMap[position[0]][position[1]] = 1;
        }

        while(!virus.isEmpty()) {
            int[] current = virus.poll();
            visited[current[0]][current[1]] = true;
            for (int i = 0; i < 4; i++) {
                int nextRow = current[0] + drow[i];
                int nextCol = current[1] + dcol[i];
                if (
                        -1 < nextRow && nextRow < rowNum &&
                        -1 < nextCol && nextCol < colNum &&
                        !visited[nextRow][nextCol] &&
                        copyMap[nextRow][nextCol] != 1
                ) {
                    if (copyMap[nextRow][nextCol] == 0)
                        copyMap[nextRow][nextCol] = 2;
                    virus.offer(new int[] {nextRow, nextCol});
                }
            }
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (copyMap[i][j] == 0) safeZone++;
            }
        }
        return safeZone;
    }


    public static void main(String[] args) throws IOException {
        new four14502().solution();
    }
}
