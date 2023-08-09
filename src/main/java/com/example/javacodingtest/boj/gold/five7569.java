package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7569
public class five7569 {
    private int[][][] box;
    private int[] dRow = {0, 0, 1, -1, 0, 0};
    private int[] dCol = {1, -1, 0, 0, 0, 0};
    private int[] dHeight = {0, 0, 0, 0, 1, -1};
    private int nCol;
    private int nRow;
    private int nHeight;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        nCol = Integer.parseInt(infoToken.nextToken());
        nRow = Integer.parseInt(infoToken.nextToken());
        nHeight = Integer.parseInt(infoToken.nextToken());

        box = new int[nCol][nRow][nHeight];

        for (int i = 0; i < nHeight; i++) {
            for (int j = 0; j < nRow; j++) {
                StringTokenizer boxToken = new StringTokenizer(reader.readLine());
                for (int k = 0; k < nCol; k++) {
                    box[k][j][i] = Integer.parseInt(boxToken.nextToken());
                }
            }
        }

        boolean[][][] visited = new boolean[nCol][nRow][nHeight];
        Queue<int[]> toVisit = new LinkedList<>();
        boolean complete = true;

        for (int i = 0; i < nHeight; i++) {
            for (int j = 0; j < nRow; j++) {
                for (int k = 0; k < nCol; k++) {
                    if (box[k][j][i] == 1 && !visited[k][j][i]) {
                        toVisit.offer(new int[] {k, j, i});
                    }
                    if(box[k][j][i] == 0) complete = false;
                    else visited[k][j][i] = true;
                }
            }
        }

        int time = 0;
        while(!toVisit.isEmpty()) {
            if (!moreToSearch(visited)) break;
            time++;
            int queSize = toVisit.size();
            for (int num = 0; num < queSize; num++) {
                int[] next = toVisit.poll();
                int currentCol = next[0];
                int currentRow = next[1];
                int currentHeight = next[2];

                if (box[currentCol][currentRow][currentHeight] == -1) continue;

                visited[currentCol][currentRow][currentHeight] = true;

                for (int i = 0; i < 6; i++) {
                    int nextCol = currentCol + dCol[i];
                    int nextRow = currentRow + dRow[i];
                    int nextHeight = currentHeight + dHeight[i];

                    if (
                            checkBounds(nextCol, nextRow, nextHeight) &&
                                    box[nextCol][nextRow][nextHeight] == 0 &&
                                    !visited[nextCol][nextRow][nextHeight]
                    ) {
                        toVisit.offer(new int[]{nextCol, nextRow, nextHeight});
                        visited[nextCol][nextRow][nextHeight] = true;
                        box[nextCol][nextRow][nextHeight] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < nHeight; i++) {
            for (int j = 0; j < nRow; j++) {
                for (int k = 0; k < nCol; k++) {
                    if (box[k][j][i] == 0) {
                        time = -1;
                    }
                }
            }
        }

        if(complete) time = 0;

        System.out.println(time);
    }

    private boolean moreToSearch(boolean[][][] visited) {
        for (int i = 0; i < nHeight; i++) {
            for (int j = 0; j < nRow; j++) {
                for (int k = 0; k < nCol; k++) {
                    if (visited[k][j][i] == false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkBounds(int col, int row, int height) {
        return (-1 < col && col < nCol
                && -1 < row && row < nRow
                && -1 < height && height < nHeight);
    }

    public static void main(String[] args) throws IOException {
        new five7569().solution();
    }
}
