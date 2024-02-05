package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class one2468 {
    public int n;
    public int[][] map;
    public boolean[][] visited;
    public int max = Integer.MIN_VALUE;
    public int result = 0;
    public int[] dCol = new int[]{-1, 1, 0, 0};
    public int[] dRow = new int[]{0, 0, -1, 1};

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer mapToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(mapToken.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        for (int i = 0; i <= max; i++) {
            int count = 0;
            visited = new boolean[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (map[j][k] > i && !visited[j][k]) {
                        count++;
                        dfs(j, k, i);
                    }
                }
            }
            result = Math.max(result, count);
        }
        System.out.println(result);
    }

    private void dfs(int col, int row, int height) {
        visited[col][row] = true;

        for (int i = 0; i < 4; i++) {
            int nCol = col + dCol[i];
            int nRow = row + dRow[i];

            if (nCol < 0 || nCol >= n ||
                    nRow < 0 || nRow >= n ||
                    map[nCol][nRow] <= height ||
                    visited[nCol][nRow]) continue;

            dfs(nCol, nRow, height);
        }
    }

    public static void main(String[] args) throws IOException {
        new one2468().solution();
    }
}
