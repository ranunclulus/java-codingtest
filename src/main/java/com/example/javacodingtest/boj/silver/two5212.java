package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.27
 @link https://www.acmicpc.net/problem/5212
 @timecomplex
 @performance 14332kb 100ms
 @category
 @note
 */
public class two5212 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int rowNum, colNum;
    static char[][] map;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] checked;
    static int startRow, endRow, startCol, endCol;

    public void  solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        rowNum = Integer.parseInt(tokenizer.nextToken());
        colNum = Integer.parseInt(tokenizer.nextToken());
        map = new char[rowNum][colNum];
        checked = new boolean[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            map[i] = reader.readLine().toCharArray();
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (map[i][j] == 'X') {
                    check(i, j);
                }
            }
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (checked[i][j]) {
                    map[i][j] = '.';
                }
            }
        }

        startRow = rowNum;
        startCol = colNum;
        endRow = 0;
        endCol = 0;

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (map[i][j] == 'X') {
                    startRow = Math.min(startRow, i);
                    startCol = Math.min(startCol, j);
                    endRow = Math.max(endRow, i);
                    endCol = Math.max(endCol, j);
                }
            }
        }

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                builder.append(map[i][j]);
            }
            builder.append('\n');
        }

        writer.write(builder.toString());
        writer.flush();
    }

    private void check(int row, int col) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + deltas[i][0];
            int nextCol = col + deltas[i][1];

            if (nextRow < 0 || nextRow >= rowNum || nextCol < 0 || nextCol >= colNum || map[nextRow][nextCol] == '.')
                count++;
        }
        if (count > 2) checked[row][col] = true;
    }

    public static void main(String[] args) throws IOException {
        new two5212().solution();
    }
}
