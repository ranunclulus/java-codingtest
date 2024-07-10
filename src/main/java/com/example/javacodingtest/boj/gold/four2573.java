package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class four2573 {
    int rowSize, colSize;
    int[][] map;
    int[] dRow = new int[] {0, 0, 1, -1};
    int[] dCol = new int[] {1, -1, 0, 0};
    int time = 0;
     public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        rowSize = Integer.parseInt(infoToken.nextToken());
        colSize = Integer.parseInt(infoToken.nextToken());
        map = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < colSize; j++) {
                map[i][j] = Integer.parseInt(infoToken.nextToken());
            }
        }

        while (!threePart()) {
            // 모두 녹았다면 0 출력하고 끝
            if (allClear()) {
                System.out.println(0);
                return;
            }

            // 모두 녹지 않았다면
            else {
                time++;
                melting();
            }
        }
        System.out.println(time);
    }

    private boolean threePart() {
        int sum = 0;
        boolean[][] visited = new boolean[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (visited[i][j]) continue;
                // 다 녹았으면 패스
                if (map[i][j] == 0) {
                    continue;
                }
                // 땅 덩어리 개수 재기
                sum++;
                if (sum >= 2) return true;

                // 안 녹았으면 땅덩어리 넓이 재기
                Queue<int[]> toVisit = new LinkedList<>();
                toVisit.add(new int[] {i, j});
                visited[i][j] = true;
                while (!toVisit.isEmpty()) {
                    int[] now = toVisit.poll();
                    for (int k = 0; k < 4; k++) {
                        int nextRow = now[0] + dRow[k];
                        int nextCol = now[1] + dCol[k];
                        if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) continue;
                        if (visited[nextRow][nextCol]) continue;
                        if (map[nextRow][nextCol] == 0) continue;
                        toVisit.add(new int[] {nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
        if (sum < 2) return false;
        return false;
    }

    // 빙하 녹이기
    private void melting() {
        // 배열 복사
        int[][] temp = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            temp[i] = map[i].clone();
        }
        // 빙하 녹이기
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (map[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nextRow = i + dRow[k];
                    int nextCol = j + dCol[k];
                    if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) continue;
                    if (temp[i][j] == 0) continue;
                    if (map[nextRow][nextCol] == 0) temp[i][j]--;
                }
            }
        }
        // 배열 복사
        for (int i = 0; i < rowSize; i++) {
            map[i] = temp[i].clone();
        }
    }

    // 빙산이 모두 녹았는지 확인하는 메소드
    private boolean allClear() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (map[i][j] != 0) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new four2573().solution();
    }
}
