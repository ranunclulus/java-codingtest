package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

public class five14503 {
    private int colNum;
    private int rowNum;
    private int[][] room;
    private int[] dCol = new int[]{-1, 0, 1, 0};
    private int[] dRow = new int[]{0, 1, 0, -1};
    private int result = 1;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        colNum = sc.nextInt();
        rowNum = sc.nextInt();
        room = new int[colNum][rowNum];

        int machineCol = sc.nextInt();
        int machineRow = sc.nextInt();
        int machineDir = sc.nextInt();

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                room[i][j] = sc.nextInt();
            }
        }

        clean(machineCol, machineRow, machineDir);

        System.out.println(result);
    }

    private void clean(int machineCol, int machineRow, int machineDir) {
        room[machineCol][machineRow] = -1;

        for (int i = 0; i < 4; i++) {
            machineDir = (machineDir + 3) % 4;
            int nextCol = machineCol + dCol[machineDir];
            int nextRow = machineRow + dRow[machineDir];

            if (nextCol < 0 || nextCol >= colNum || nextRow < 0 || nextRow >= rowNum) {
                continue;
            }
            if (room[nextCol][nextRow] == 0) {
                result++;
                clean(nextCol, nextRow, machineDir);
                return;
            }
        }

        int backDirection = (machineDir + 2) % 4;
        int backCol = machineCol + dCol[backDirection];
        int backRow = machineRow + dRow[backDirection];
        if (backCol >= 0 && backCol < colNum && backRow >= 0 && backRow < rowNum && room[backCol][backRow] != 1) {
            clean(backCol, backRow, machineDir);
        }
    }

    public static void main(String[] args) throws IOException {
        new five14503().solution();
    }
}
