package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class two12100 {
    private static int n;
    private static int[][] board;
    private static int result = 2;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer boardToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(boardToken.nextToken());
            }
        }

        dfs(0);
        System.out.println(result);
    }

    private void dfs(int depth) {
        if (depth == 5) {
            findMax();
            return;
        }

        int[][] copyBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            copyBoard[i] = board[i].clone();
        }

        for (int i = 0; i < 4; i++) {
            if (i == 0) moveRight();
            else if (i == 1) moveLeft();
            else if (i == 2) moveUp();
            else moveDown();

            dfs(depth + 1);

            for (int j = 0; j < n; j++) {
                board[j] = copyBoard[j].clone();
            }
        }
    }

    private void moveRight() {
        for (int col = 0; col < n; col++) {
            int index = n - 1;
            int block = 0;
            for (int row = n - 1; row >= 0; row--) {
                if (board[col][row] == 0) continue;
                if (board[col][row] == block) {
                    board[col][index + 1] = block * 2;
                    block = 0;
                    board[col][row] = 0;
                } else {
                    block = board[col][row];
                    board[col][row] = 0;
                    board[col][index] = block;
                    index--;
                }
            }
        }
    }

    private void moveLeft() {
        for (int col = 0; col < n; col++) {
            int index = 0;
            int block = 0;
            for (int row = 0; row < n; row++) {
                if (board[col][row] == 0) continue;
                if (board[col][row] == block) {
                    board[col][index - 1] = block * 2;
                    block = 0;
                    board[col][row] = 0;
                } else {
                    block = board[col][row];
                    board[col][row] = 0;
                    board[col][index] = block;
                    index++;
                }
            }
        }
    }

    private void moveUp() {
        for (int row = 0; row < n; row++) {
            int index = 0;
            int block = 0;
            for (int col = 0; col < n; col++) {
                if (board[col][row] == 0) continue;
                if (board[col][row] == block) {
                    board[index - 1][row] = block * 2;
                    block = 0;
                    board[col][row] = 0;
                } else {
                    block = board[col][row];
                    board[col][row] = 0;
                    board[index][row] = block;
                    index++;
                }
            }
        }
    }

    private void moveDown() {
        for (int row = 0; row < n; row++) {
            int index = n - 1;
            int block = 0;
            for (int col = n - 1; col >= 0; col--) {
                if (board[col][row] == 0) continue;
                if (board[col][row] == block) {
                    board[index + 1][row] = block * 2;
                    block = 0;
                    board[col][row] = 0;
                } else {
                    block = board[col][row];
                    board[col][row] = 0;
                    board[index][row] = block;
                    index--;
                }
            }
        }
    }

    private void findMax() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, board[i][j]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two12100().solution();
    }
}
