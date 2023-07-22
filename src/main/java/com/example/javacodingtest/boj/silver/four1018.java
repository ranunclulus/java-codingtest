package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1018
public class four1018 {
    private boolean[][] chess;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());

        chess = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String row = reader.readLine();
            for (int j = 0; j < m; j++) {
                if(row.charAt(j) == 'W') chess[i][j] = true;
                else chess[i][j] = false;
            }
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                result = Math.min(result, makeChess(i, j, true));
                result = Math.min(result, makeChess(i, j, false));
            }
        }
        System.out.println(result);
    }

    public int makeChess(int row, int col, boolean firstColor) {
        int count = 0;

        for (int i = row; i < row + 8; i++) {
            for (int j = col; j < col + 8; j++) {
                //System.out.printf("%d %d\n", i, j);
                if (chess[i][j] != firstColor) count++;
                firstColor = (!firstColor);
            }
            firstColor = (!firstColor);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        new four1018().solution();
    }
}
