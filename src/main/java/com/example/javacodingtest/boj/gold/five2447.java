package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2447
public class five2447 {
    private char[][] starboard;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        starboard = new char[n][n];
        for (char[] row: starboard) {
            Arrays.fill(row, ' ');
        }
        setStar(n, 0, 0);
        StringBuilder drawStar = new StringBuilder();
        for (int i = 0; i < n; i++) {
            drawStar.append(starboard[i]).append('\n');
        }
        System.out.println(drawStar.toString());
    }

    public void setStar(int n, int x, int y) {
        // n = 3이라면 실제로 별을 그리기 시작
        if (n == 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // 가운데면 스킵
                    if (i == 1 && j == 1) continue;
                    // 아니라면 별 그리기
                    starboard[y + i][x + j] = '*';
                }
            }
        }
        // n = 3일 때까지 재귀 호출
        else {
            int offset = n / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // 가운데 사각형이면 굳이 재귀호출 안해도 된다.
                    if (i == 1 && j == 1) continue;
                    setStar(offset, x + offset * i, y + offset * j);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new five2447().solution();
    }
}
