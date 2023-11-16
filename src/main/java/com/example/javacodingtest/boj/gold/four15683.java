package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// https://www.acmicpc.net/problem/15683
public class four15683 {
    private int suspected;
    private int colNum;
    private int rowNum;
    private int[][] map;
    private int[] dCol = new int[]{-1, 0, 1, 0};
    private int[] dRow = new int[]{0, 1, 0, -1};
    private int[][] cctvInfo = new int[][]{{0, 1, 0, 0},
            {0, 1, 0, 1},
            {1, 1, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 1, 1}};
    private boolean[][] watched;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        colNum = sc.nextInt();
        rowNum = sc.nextInt();
        map = new int[colNum][rowNum];
        watched = new boolean[colNum][rowNum];

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        suspected = 0;
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                if (map[i][j] != 0 && map[i][j] != 6) {
                    watched[i][j] = true;
                    suspected++;
                    direction(i, j);
                }
                if (map[i][j] == 6 && !watched[i][j]) {
                    watched[i][j] = true;
                    suspected++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                if (map[i][j] == 0 && !watched[i][j]) count++;
            }
        }

        System.out.println(count);
    }

    private void direction(int col, int row) {
        int[] cctv = cctvInfo[map[col][row] - 1];
        List<int[]>[] findMax = new List[4];

        // cctv 네 번 회전
        for (int i = 0; i < 4; i++) {
            findMax[i] = new ArrayList<>();

            // 네 방향 검사
            for (int j = 0; j < 4; j++) {
                int rawCol = col;
                int rawRow = row;
                // 해당 방향의 카메라가 아니라면
                if (cctv[j] == 0) continue;
                else {
                    while (true) {
                        int nextCol = rawCol + dCol[j];
                        int nextRow = rawRow + dRow[j];

                        if (nextCol < 0 || nextCol >= colNum || nextRow < 0 || nextRow >= rowNum || map[nextCol][nextRow] == 6) {
                            break;
                        }

                        if (!watched[nextCol][nextRow]) {
                            findMax[i].add(new int[]{nextCol, nextRow});
                            watched[nextCol][nextRow] = true;
                        }
                        rawCol = nextCol;
                        rawRow = nextRow;
                    }
                }
            }
            cctv = turnRight(cctv);
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < 4; i++) {
            if (findMax[i].size() > max) {
                max = findMax[i].size();
                maxIndex = i;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (i == maxIndex) continue;
            for (int j = 0; j < findMax[i].size(); j++) {
                watched[findMax[i].get(j)[0]][findMax[i].get(j)[1]] = false;
            }
        }
        suspected += max;
    }

    private int[] turnRight(int[] cctv) {
        int temp = cctv[3];
        cctv[3] = cctv[2];
        cctv[2] = cctv[1];
        cctv[1] = cctv[0];
        cctv[0] = temp;
        return cctv;
    }

    public static void main(String[] args) throws IOException {
        new four15683().solution();
    }
}
