package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1012
public class two1012 {
    static int colNum, rowNum;
    static int[][] farm;
    static boolean[][] visit;
    static int count;
    static int[] drow = { 0, -1, 0, 1 };
    static int[] dcol = { 1, 0, -1, 0 };

    public void dfs(int col, int row) {
        visit[col][row] = true;
        for (int i = 0; i < 4; i++) {
            int ncol = col + dcol[i];
            int nrow = row + drow[i];
            if(ncol >= 0 && nrow >= 0 && ncol < colNum && nrow < rowNum) {
                if (farm[ncol][nrow] == 1 && !visit[ncol][nrow]) {
                    dfs(ncol, nrow);
                }
            }
        }
    }
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testCase; i++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            colNum = Integer.parseInt(infoToken.nextToken());
            rowNum = Integer.parseInt(infoToken.nextToken());
            int pestNum = Integer.parseInt(infoToken.nextToken());
            farm = new int[colNum][rowNum];
            visit = new boolean[colNum][rowNum];
            for (int[] row: farm) {
                Arrays.fill(row, 0);
            }
            for (int j = 0; j < pestNum; j++) {
                StringTokenizer pestToken = new StringTokenizer(reader.readLine());
                int col = Integer.parseInt(pestToken.nextToken());
                int row = Integer.parseInt(pestToken.nextToken());
                farm[col][row] = 1;
            }

            for (int j = 0; j < colNum; j++) {
                for (int k = 0; k < rowNum; k++) {
                    if (farm[j][k] == 1 && !visit[j][k]) {
                        dfs(j, k);
                        count++;
                    }
                }
            }
            System.out.println(count);
            count = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        new two1012().solution();
    }
}
