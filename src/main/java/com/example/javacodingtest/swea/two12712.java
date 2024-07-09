package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class two12712 {
    public int[] dx;
    public int[] dy;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력부
            int answer = 0;
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            int size = Integer.parseInt(infoToken.nextToken());
            int m = Integer.parseInt(infoToken.nextToken());
            int[][] arr = new int[size][size];
            for (int i = 0; i < size; i++) {
                infoToken = new StringTokenizer(reader.readLine());
                for (int j = 0; j < size; j++) {
                    arr[i][j] = Integer.parseInt(infoToken.nextToken());
                }
            }

            dx = new int[] {0, 0, 1, -1};
            dy = new int[] {1, -1, 0, 0};
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    int sum = arr[row][col];
                    for (int i = 1; i < m; i++) {
                        for (int dir = 0; dir < 4; dir++) {
                            int nextRow = row + dx[dir] * i;
                            int nextCol = col + dy[dir] * i;
                            if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
                            sum += arr[nextRow][nextCol];
                        }
                    }
                    answer = Math.max(answer, sum);
                }
            }

            dx = new int[] {1, 1, -1, -1};
            dy = new int[] {1, -1, 1, -1};
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    int sum = arr[row][col];
                    for (int i = 1; i < m; i++) {
                        for (int dir = 0; dir < 4; dir++) {
                            int nextRow = row + dx[dir] * i;
                            int nextCol = col + dy[dir] * i;
                            if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
                            sum += arr[nextRow][nextCol];
                        }
                    }
                    answer = Math.max(answer, sum);
                }
            }

            System.out.printf("#%d %d\n", test, answer);
        }
    }

    public static void main(String[] args) throws IOException {
        new two12712().solution();
    }
}
