package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class four1868 {
    static int size;
    static int[][] map;
    static int answer;
    static int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};
    static int dot;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());

        for (int test = 1; test <= testNum; test++) {
            dot = 0;
            answer = 0;
            // 입력부
            size = Integer.parseInt(reader.readLine());
            map = new int[size][size];
            for (int i = 0; i < size; i++) {
                String input = reader.readLine();
                for (int j = 0; j < size; j++) {
                    if (input.charAt(j) == '.') {
                        map[i][j] = -1;
                        dot++;
                    } else {
                        map[i][j] = -2;
                    }
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (map[i][j] == -2) continue;
                    if (count(i, j) == 0) {
                        bfs(i, j);
                        answer++;
                    }
                }
            }
            System.out.printf("#%d %d\n", test, answer + dot);
        }
    }

    private int count(int row, int col) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nextCol = col + dy[i];
            int nextRow = row + dx[i];

            if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
            if (map[nextRow][nextCol] == -2) cnt++;
        }
        return cnt;
    }

    private void bfs(int row, int col) {
        int count = count(row, col);
        dot--;
        if (count == 0) {
            map[row][col] = 0;
            for (int i = 0; i < 8; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];
                if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
                if (map[nextRow][nextCol] != -1) continue;
                bfs(nextRow, nextCol);
            }
        } else map[row][col] = 0;
    }

    public static void main(String[] args) throws IOException {
        new four1868().solution();
    }
}
