package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

// https://www.acmicpc.net/problem/14500
public class four14500 {
    private int colNum;
    private int rowNum;
    private int[][] map;
    private int[] dCol = new int[] {0, 0, 1, -1};
    private int[] dRow = new int[] {1, -1, 0, 0};
    private int result;
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        colNum = sc.nextInt();
        rowNum = sc.nextInt();
        map = new int[colNum][rowNum];

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        result = 0;
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                tetromino(i, j);
            }
        }

        System.out.println(result);
    }

    private void tetromino(int col, int row) {
        int value = map[col][row];
        for (int i = 0; i < 4; i++) {
            int secondCol = col + dCol[i];
            int secondRow = row + dRow[i];
            if (!validate(secondCol, secondRow)) continue;
            value += map[secondCol][secondRow];
            for (int j = 0; j < 4; j++) {
                int thirdCol = secondCol + dCol[j];
                int thirdRow = secondRow + dRow[j];
                if (!validate(thirdCol, thirdRow)) continue;
                if (thirdCol == col && thirdRow == row) continue;
                value += map[thirdCol][thirdRow];
                for (int k = 0; k < 4; k++) {
                    int fourthCol = thirdCol + dCol[k];
                    int fourthRow = thirdRow + dRow[k];
                    if (!validate(fourthCol, fourthRow)) continue;
                    if (fourthCol == secondCol && fourthRow == secondRow) continue;
                    if (fourthCol == thirdCol && fourthRow == thirdRow) continue;
                    if (fourthCol == col && fourthRow == row) continue;
                    value += map[fourthCol][fourthRow];
                    result = Math.max(result, value);
                    value -= map[fourthCol][fourthRow];
                }
                for (int k = 0; k < 4; k++) {
                    int fourthCol = secondCol + dCol[k];
                    int fourthRow = secondRow + dRow[k];
                    if (!validate(fourthCol, fourthRow)) continue;
                    if (fourthCol == secondCol && fourthRow == secondRow) continue;
                    if (fourthCol == thirdCol && fourthRow == thirdRow) continue;
                    if (fourthCol == col && fourthRow == row) continue;
                    value += map[fourthCol][fourthRow];
                    result = Math.max(result, value);
                    value -= map[fourthCol][fourthRow];
                }
                value -= map[thirdCol][thirdRow];
            }
            value -= map[secondCol][secondRow];
        }
    }

    private boolean validate(int col, int row) {
        if (col < 0 || col >= colNum || row < 0 || row >= rowNum)
            return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        new four14500().solution();
    }
}
