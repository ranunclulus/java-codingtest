package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.*;

class CCTV {
    int col;
    int row;
    int num;

    CCTV(int col, int row, int num) {
        this.col = col;
        this.row = row;
        this.num = num;
    }
}

public class four15683 {
    private int colNum;
    private int rowNum;
    private int[] output;
    private int[][] map;
    private int[][] copyMap;
    private List<CCTV> cctvList = new ArrayList<>();
    private int[] dCol = new int[]{-1, 0, 1, 0};
    private int[] dRow = new int[]{0, 1, 0, -1};
    private int result = Integer.MAX_VALUE;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        colNum = sc.nextInt();
        rowNum = sc.nextInt();
        map = new int[colNum][rowNum];

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        output = new int[cctvList.size()];
        permutaion(0, cctvList.size());

        if (result == Integer.MAX_VALUE) result = 0;
        System.out.println(result);
    }

    private void permutaion(int depth, int size) {
        if (depth == size) {
            copyMap = new int[colNum][rowNum];
            for (int i = 0; i < colNum; i++) {
                for (int j = 0; j < rowNum; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < cctvList.size(); i++) {
                direction(cctvList.get(i), output[i]);
            }

            getBlindSpot();
            return;
        }

        for (int i = 0; i < 4; i++) {
            output[depth] = i;
            permutaion(depth + 1, size);
        }
    }

    private void getBlindSpot() {
        int count = 0;
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                if (copyMap[i][j] == 0) count++;
            }
        }
        result = Math.min(result, count);
    }

    private void direction(CCTV cctv, int d) {
        int cctvNum = cctv.num;

        if (cctvNum == 1) {
            watch(cctv, d);
        } else if (cctvNum == 2) {
            watch(cctv, d);
            watch(cctv, (d + 2) % 4);
        } else if (cctvNum == 3) {
            watch(cctv, d);
            watch(cctv, (d + 1) % 4);
        } else if (cctvNum == 4) {
            watch(cctv, d);
            watch(cctv, (d + 1) % 4);
            watch(cctv, (d + 3) % 4);
        } else if (cctvNum == 5) {
            watch(cctv, 0);
            watch(cctv, 1);
            watch(cctv, 2);
            watch(cctv, 3);
        }
    }

    private void watch(CCTV cctv, int d) {
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{cctv.col, cctv.row});

        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nextCol = now[0] + dCol[d];
            int nextRow = now[1] + dRow[d];

            if (nextCol < 0 || nextCol >= colNum
                    || nextRow < 0 || nextRow >= rowNum
                    || copyMap[nextCol][nextRow] == 6) {
                break;
            }

            toVisit.add(new int[]{nextCol, nextRow});

            if (copyMap[nextCol][nextRow] == 0) {
                copyMap[nextCol][nextRow] = 7;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four15683().solution();
    }
}
