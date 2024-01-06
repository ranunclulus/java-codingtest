package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

public class four2448 {
    public int n;
    public char[][] tree;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        n = sc.nextInt();
        tree = new char[n][n * 2 - 1];

        for (int i = 0; i < tree.length; i++) {
            for (int j = 0; j < tree[0].length; j++) {
                tree[i][j] = ' ';
            }
        }

        drawWall(0, n - 1); // 꼭대기 좌표

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n * 2 - 1; j++) {
                sb.append(tree[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    private void drawWall(int col, int row) {
        drawOneTree(col, row);
        int length = 3;

        while (length <= n / 2) {
            copy(col, row, length, col + length, row - length);
            copy(col, row, length, col + length, row + length);
            length *= 2;
        }
    }

    private void copy(int col, int row, int length, int newCol, int newRow) {
        for (int i = 0; i < length; i++) {
            for (int j = -(length - 1); j < length; j++) {
                tree[newCol + i][newRow + j] = tree[col + i][row + j];
            }
        }
    }

    private void drawOneTree(int col, int row) {
        tree[col][row] = '*';

        tree[col + 1][row - 1] = '*';
        tree[col + 1][row + 1] = '*';

        tree[col + 2][row - 2] = '*';
        tree[col + 2][row - 1] = '*';
        tree[col + 2][row] = '*';
        tree[col + 2][row + 1] = '*';
        tree[col + 2][row + 2] = '*';
    }

    public static void main(String[] args) throws IOException {
        new four2448().solution();
    }
}
