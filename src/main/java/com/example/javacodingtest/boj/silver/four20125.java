package com.example.javacodingtest.boj.silver;
// https://www.acmicpc.net/problem/20125

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class four20125 {
    char[][] map;
    int[] col;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        map = new char[size][size];
        col = new int[size];

        for (int i = 0; i < size; i++) {
            String input = reader.readLine();
            for (int j = 0; j < size; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == '*') {
                    col[i]++;
                }
            }
        }

        int heartCol = 0, heartRow = 0;
        int maxCol = 0;
        for (int i = 0; i < size; i++) {
            if (col[i] > maxCol) {
                maxCol = col[i];
                heartCol = i;
            }
        }
        for (int i = 0; i < size; i++) {
            if (map[heartCol - 1][i] == '*') heartRow = i;
        }

        // 심장 위치
        System.out.printf("%d %d\n", heartCol + 1, heartRow + 1);

        int rightHand = 0, leftHand = 0, rightLeg = 0, leftLeg = 0, body = 0;

        for (int i = heartRow - 1; i >= 0; i--) {
            if (map[heartCol][i] == '*') leftHand++;
        }
        for (int i = heartRow + 1; i < size; i++) {
            if (map[heartCol][i] == '*') rightHand++;
        }
        for (int i = heartCol + 1; i < size; i++) {
            if (map[i][heartRow] != '*') {
                heartCol = i;
                break;
            } else body++;
        }
        for (int i = heartCol; i < size; i++) {
            if (map[i][heartRow - 1] == '*') leftLeg++;
        }
        for (int i = heartCol; i < size; i++) {
            if (map[i][heartRow + 1] == '*') rightLeg++;
        }

        System.out.printf("%d %d %d %d %d", leftHand, rightHand, body, leftLeg, rightLeg);
    }

    public static void main(String[] args) throws IOException {
        new four20125().solution();
    }
}
