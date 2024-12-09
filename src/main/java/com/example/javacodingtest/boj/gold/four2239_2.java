package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.09
 @link https://www.acmicpc.net/problem/2239
 @timecomplex
 @performance 19668kb 620ms
 @category
 @note
 */
public class four2239_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static int[][] sudoku;
    static boolean flag;


    public void solution() throws IOException {
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String[] input = reader.readLine().split("");
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(input[j]);
            }
        }

        backtracking(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(sudoku[i][j]);
            }
            builder.append("\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void backtracking(int depth) {
        if (depth == 81) {
            flag = true;
            return;
        }

        int row = depth / 9;
        int col = depth % 9;

        if (sudoku[row][col] != 0) backtracking(depth + 1);
        else {
            for (int i = 1; i <= 9; i++) {
                if (!isValid(row, col, i)) continue;
                sudoku[row][col] = i;
                backtracking(depth + 1);
                if (flag) return;
                sudoku[row][col] = 0;
            }
        }
    }

    private boolean isValid(int row, int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == number) return false;
            if (sudoku[i][col] == number) return false;
        }

        int squareRow = (row / 3) * 3;
        int squareCol = col - (col % 3);

        for (int i = squareRow; i < squareRow + 3; i++) {
            for (int j = squareCol; j < squareCol + 3; j++) {
                if (sudoku[i][j] == number) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        new four2239_2().solution();
    }
}
