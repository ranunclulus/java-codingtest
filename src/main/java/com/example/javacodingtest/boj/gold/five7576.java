package com.example.javacodingtest.boj.gold;
// https://www.acmicpc.net/problem/7576

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class five7576 {
    int[] dRow = new int[]{0, 0, 1, -1};
    int[] dCol = new int[]{1, -1, 0, 0};

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int colNum = Integer.parseInt(infoToken.nextToken());
        int rowNum = Integer.parseInt(infoToken.nextToken());
        int[][] tomato = new int[rowNum][colNum];
        Queue<int[]> toVisit = new LinkedList<>();

        for (int i = 0; i < rowNum; i++) {
            StringTokenizer tomatoToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < colNum; j++) {
                tomato[i][j] = Integer.parseInt(tomatoToken.nextToken());
                if (tomato[i][j] == 1) toVisit.offer(new int[]{i, j});
            }
        }

        int time = 0;
        boolean[][] visited = new boolean[rowNum][colNum];

        while (!toVisit.isEmpty()) {
            time++;
            int repeatNum = toVisit.size();
            for (int index = 0; index < repeatNum; index++) {
                int[] current = toVisit.poll();
                int currentRow = current[0];
                int currentCol = current[1];
                visited[currentRow][currentCol] = true;

                for (int i = 0; i < 4; i++) {
                    int nextRow = currentRow + dRow[i];
                    int nextCol = currentCol + dCol[i];
                    if (-1 < nextRow && nextRow < rowNum
                            && -1 < nextCol && nextCol < colNum
                            && !visited[nextRow][nextCol]
                            && tomato[nextRow][nextCol] == 0) {
                        tomato[nextRow][nextCol] = 1;
                        toVisit.offer(new int[]{nextRow, nextCol});
                    }
                }
            }
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (tomato[i][j] == 0) time = 0;
            }
        }

        System.out.println(time - 1); // 맨 마지막에 익는 토마토 제외
    }

    public static void main(String[] args) throws IOException {
        new five7576().solution();
    }
}
