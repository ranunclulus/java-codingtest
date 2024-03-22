package com.example.javacodingtest.programmers.level2;

import java.util.*;

public class Programmers250136 {
    public int[] dCol = {0, 0, 1, -1};
    public int[] dRow = {1, -1, 0, 0};
    public int[] oil;

    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        oil = new int[m];

        int answer = 0;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    bfs(land, i, j, n, m, visited);
                }
            }
        }

        answer = Arrays.stream(oil).max().getAsInt();
        return answer;
    }

    private void bfs(int[][] land, int col, int row, int n, int m, boolean[][] visited) {
        int area = 1;
        visited[col][row] = true;
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{col, row});
        Set<Integer> set = new HashSet<>();

        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            set.add(now[1]);
            for (int k = 0; k < 4; k++) {
                int nCol = now[0] + dCol[k];
                int nRow = now[1] + dRow[k];

                if (nCol < 0 || nCol >= n || nRow < 0 || nRow >= m) continue;
                if (visited[nCol][nRow]) continue;
                if (land[nCol][nRow] == 0) continue;

                area++;
                toVisit.add(new int[]{nCol, nRow});
                visited[nCol][nRow] = true;
            }
        }
        for (int index : set) {
            oil[index] += area;
        }
    }


    public static void main(String[] args) {
        int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 1}};
        System.out.println(new Programmers250136().solution(land));
    }
}
