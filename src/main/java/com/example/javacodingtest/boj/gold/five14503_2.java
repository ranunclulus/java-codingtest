package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.20
 @link https://www.acmicpc.net/problem/14503
 @timecomplex
 @performance 14400kb, 108ms
 @category
 @note
 */
public class five14503_2 {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, answer;
    static int[][] map;
    static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        int robotRow = Integer.parseInt(tokenizer.nextToken());
        int robotCol = Integer.parseInt(tokenizer.nextToken());
        int robotDirection = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        answer = 1;
        clean(robotRow, robotCol, robotDirection);

        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    private void clean(int robotRow, int robotCol, int robotDirection) {
        map[robotRow][robotCol] = -1;

        for (int i = 0; i < 4; i++) {
            robotDirection = (robotDirection + 3) % 4;
            int nextRow = robotRow + deltas[robotDirection][0];
            int nextCol = robotCol + deltas[robotDirection][1];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;

            if (map[nextRow][nextCol] == 0) {
                answer++;
                clean(nextRow, nextCol, robotDirection);
                return;
            }
        }

        int backDirection = (robotDirection + 2) % 4;
        int backRow = robotRow + deltas[backDirection][0];
        int backCol = robotCol + deltas[backDirection][1];

        if (backRow >= 0 && backRow < n && backCol >= 0 && backCol < m && map[backRow][backCol] != 1) {
            clean(backRow, backCol, robotDirection);
        }
    }


    public static void main(String[] args) throws IOException {
        new five14503_2().solution();
    }
}

