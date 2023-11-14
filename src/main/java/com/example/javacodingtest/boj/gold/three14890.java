package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

public class three14890 {
    private int n;
    private int[][] map;
    private int l;
    private boolean[][] isPlaced;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();

        isPlaced = new boolean[n][n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (calRow(i)) count++;
            if (calCol(i)) count++;
        }
        System.out.println(count);
    }

    private boolean calCol(int col) {
        boolean[] isIncline = new boolean[n]; // 경사면 설치 여부 확인

        for (int i = 0; i < n - 1; i++) {
            int diff = map[i][col] - map[i + 1][col];

            // 차이가 1 초과
            if (diff > 1 || diff < -1) return false;

            else if (diff == -1) { // 다음 계단이 한 계단 높아서 올라가는 계단 설치
                for (int j = 0; j < l; j++) {
                    if (i - j < 0 || isIncline[i - j]) return false; // 범위
                    if (map[i][col] != map[i - j][col]) return false; // 바닥면이 일정하지 않음
                    isIncline[i - j] = true;
                }
            } else if (diff == 1) { // 다음 게단이 한 계단 낮아서 내려가는 계단 설치
                for (int j = 1; j <= l; j++) {
                    if (i + j >= n || isIncline[i + j]) return false; // 범위나 이미 계단이 설치됐을 때
                    if (map[i][col] - 1 != map[i + j][col]) return false; // 바닥면이 일정하지 않을 때
                    isIncline[i + j] = true;
                }
            }
        }
        return true;
    }

    private boolean calRow(int row) {
        boolean[] isInclined = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            int diff = map[row][i] - map[row][i + 1];
            if (diff > 1 || diff < -1) return false;

            else if (diff == -1) { // 올라가는 경사로
                for (int j = 0; j < l; j++) {
                    if (i - j < 0 || isInclined[i - j]) return false;
                    if (map[row][i] != map[row][i - j]) return false;
                    isInclined[i - j] = true;
                }
            } else if (diff == 1) { // 내려가는 경사로
                for (int j = 1; j <= l; j++) {
                    if (i + j >= n || isInclined[i + j]) return false;
                    if (map[row][i] - 1 != map[row][i + j]) return false;
                    isInclined[i + j] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new three14890().solution();
    }
}
