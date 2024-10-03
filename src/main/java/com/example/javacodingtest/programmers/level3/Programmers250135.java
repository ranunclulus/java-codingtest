package com.example.javacodingtest.programmers.level3;

import java.util.*;

class Programmers250135 {
    class Point{
        int row;
        int col;

        Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    int[][] map;
    int rowSize;
    int colSize;
    int answer = Integer.MAX_VALUE;
    boolean redEnd;
    boolean blueEnd;

    boolean[][] redVisited;
    boolean[][] blueVisited;
    int[] dRow = new int[] {0, 0, -1, 1};
    int[] dCol = new int[] {-1, 1, 0, 0};
    //int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int solution(int[][] maze) {
        rowSize = maze.length;
        colSize = maze[0].length;
        //System.out.printf("rowSize: %d, colSize: %d\n", rowSize, colSize);

        Point red = null;
        Point blue = null;
        map = new int[rowSize][colSize];
        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < colSize; j++) {
                map[i][j] = maze[i][j];
                if (map[i][j] == 1) { // 빨간 수레 시작
                    red = new Point(i, j);
                }
                else if (map[i][j] == 2) { // 파란 수레 시작
                    blue = new Point(i, j);
                }
            }
        }
        //System.out.printf("red row: %d, red col: %d\n", red.row, red.col);
        //System.out.printf("blue row: %d, blue col: %d\n", blue.row, blue.col);

        redVisited = new boolean[rowSize][colSize];
        blueVisited = new boolean[rowSize][colSize];

        redVisited[red.row][red.col] = true;
        blueVisited[blue.row][blue.col] = true;

        backTracking(red, blue, 0);
        return (answer == Integer.MAX_VALUE) ? 0 : answer;
    }

    public void backTracking(Point red, Point blue, int count) {
        if (redEnd && blueEnd) {
            answer = Math.min(answer, count);
            return;
        }

        for(int r = 0; r < 4; r++) {
            for(int b = 0; b < 4; b++) {
                Point newRed = (redEnd) ? red : stepNext(red, r);
                Point newBlue = (blueEnd) ? blue : stepNext(blue, b);

                if (!isPossible(newRed, newBlue, red, blue)) continue;
                redVisited[newRed.row][newRed.col] = true;
                blueVisited[newBlue.row][newBlue.col] = true;

                if (map[newRed.row][newRed.col] == 3) redEnd = true;
                if (map[newBlue.row][newBlue.col] == 4) blueEnd = true;

                backTracking(newRed, newBlue, count + 1);

                redEnd = false;
                blueEnd = false;
                redVisited[newRed.row][newRed.col] = false;
                blueVisited[newBlue.row][newBlue.col] = false;
            }
        }
    }

    public Point stepNext(Point point, int direction) {
        int row = point.row + dRow[direction];
        int col = point.col + dCol[direction];
        return new Point(row, col);
    }

    public boolean isPossible(Point red, Point blue,
                              Point currentRed, Point currentBlue) {
        if (!isBoundary(red) || !isBoundary(blue))
            return false; // 범위 밖이거나 벽

        if (red.row == currentBlue.row && red.col == currentBlue.col &&
                blue.row == currentRed.row && blue.col == currentRed.col)
            return false; // 두 포인트 교차

        if (!redEnd && redVisited[red.row][red.col])
            return false; // 레드 중복 방문
        if (!blueEnd && blueVisited[blue.row][blue.col])
            return false; // 블루 중복 방문

        if (red.row == blue.row && red.col == blue.col)
            return false; // 두 수레 동일 위치

        return true;
    }

    public boolean isBoundary(Point point) {
        if (point.row < 0 || point.row >= rowSize ||
                point.col < 0 || point.col >= colSize ||
                map[point.row][point.col] == 5) return false;
        return true;
    }
}
