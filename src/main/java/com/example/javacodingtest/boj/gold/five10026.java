package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/10026
public class five10026 {
    String[][] grid;
    int[][] cowArt;
    int[][] notCowArt;
    boolean[][] visited;
    int n;
    int cowArtNum = 0;
    int notCowArtNum = 0;
    int[] dr = new int[] {0, 0, 1, -1};
    int[] dc = new int[] {1, -1, 0, 0};
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        grid = new String[n][n];
        visited = new boolean[n][n];
        cowArt = new int[n][n];
        notCowArt = new int[n][n];

        for (int i = 0; i < n; i++) {
            grid[i] = reader.readLine().split("");
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j].equals("R")) {
                    notCowArt[i][j] = 1;
                    cowArt[i][j] = 1;
                }
                else if (grid[i][j].equals("G")) {
                    notCowArt[i][j] = 2;
                    cowArt[i][j] = 1;
                }
                else {
                    notCowArt[i][j] = 3;
                    cowArt[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j])
                    cowArtBfs(i, j);
            }
        }

        for (boolean[] row: visited) {
            Arrays.fill(row, false);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j])
                    notCowArtBfs(i, j);
            }
        }
        System.out.printf("%d %d", notCowArtNum, cowArtNum);
    }

    private void cowArtBfs(int row, int col) {
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.offer(new int[] {row, col});
        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowRow = now[0];
            int nowCol = now[1];
            visited[nowRow][nowCol] = true;
            int currentColor = cowArt[nowRow][nowCol];
            for (int i = 0; i < 4; i++) {
                int nextRow = nowRow + dr[i];
                int nextCol = nowCol + dc[i];
                if (checkBoundary(nextRow, nextCol) &&
                    cowArt[nextRow][nextCol] == currentColor &&
                    !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    toVisit.offer(new int[] {nextRow, nextCol});
                }
            }
        }
        cowArtNum++;
    }

    private void notCowArtBfs(int row, int col) {
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.offer(new int[] {row, col});
        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowRow = now[0];
            int nowCol = now[1];
            visited[nowRow][nowCol] = true;
            int currentColor = notCowArt[nowRow][nowCol];
            for (int i = 0; i < 4; i++) {
                int nextRow = nowRow + dr[i];
                int nextCol = nowCol + dc[i];
                if (checkBoundary(nextRow, nextCol) &&
                        notCowArt[nextRow][nextCol] == currentColor &&
                        !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    toVisit.offer(new int[] {nextRow, nextCol});
                }
            }
        }
        notCowArtNum++;
    }

    private boolean checkBoundary(int nextRow, int nextCol) {
        return -1 < nextRow && nextRow < n
                && -1 < nextCol && nextCol < n;
    }

    public static void main(String[] args) throws IOException {
        new five10026().solution();
    }
}