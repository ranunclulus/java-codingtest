package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

class Machine {
    int col;
    int row;
    int direction;

    public Machine(int col, int row, int direction) {
        this.col = col;
        this.row = row;
        this.direction = direction;
    }
}

public class five14503 {
    private int colNum;
    private int rowNum;
    private int[][] room;
    private int[] dCol = new int[]{1, 0, -1, 0};
    private int[] dRow = new int[]{0, -1, 0, 1};
    private int result;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        colNum = sc.nextInt();
        rowNum = sc.nextInt();
        room = new int[colNum][rowNum];

        int machineCol = sc.nextInt();
        int machineRow = sc.nextInt();
        int machineDir = sc.nextInt();
        Machine machine = new Machine(machineCol, machineRow, machineDir);

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                room[i][j] = sc.nextInt();
            }
        }

        while (true) {
            // boolean isUpdated = false;
            if (room[machine.col][machine.row] == 0) {
                result++;
                room[machine.col][machine.row] = 1;
            }
            if (allClean(machine)) {
                if (machine.col == 0 || machine.col == colNum - 1
                        || machine.row == 0 || machine.row == rowNum - 1) {
                    break;
                }
                machine.col += dCol[machine.direction];
                machine.row += dRow[machine.direction];
                //isUpdated = true;
            } else {
                machine.direction = (machine.direction + 3) % 4;
                int nextCol = machine.col - dCol[machine.direction];
                int nextRow = machine.row - dRow[machine.direction];

                if (nextCol < 0 || nextCol >= colNum
                        || nextRow < 0 || nextRow >= rowNum - 1) {
                    continue;
                } else if (room[nextCol][nextRow] == 0) {
                    machine.col = nextCol;
                    machine.row = nextRow;
                }
            }
        }
        System.out.println(result);
    }

    // 청소되지 않은 빈칸이 있으면 false
    private boolean allClean(Machine machine) {
        for (int i = 0; i < 4; i++) {
            int nextCol = machine.col + dCol[i];
            int nextRow = machine.row + dRow[i];

            if (nextCol < 0 || nextCol >= colNum || nextRow < 0 || nextRow >= rowNum) {
                continue;
            }

            if (room[nextCol][nextRow] == 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        new five14503().solution();
    }
}
