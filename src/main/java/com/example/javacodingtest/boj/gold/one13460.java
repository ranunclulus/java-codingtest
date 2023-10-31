package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Marble {
    int redRow;
    int redCol;
    int blueRow;
    int blueCol;
    int count;
    Marble(int redCol, int redRow, int blueCol, int blueRow, int count) {
        this.redCol = redCol;
        this.redRow = redRow;
        this.blueCol = blueCol;
        this.blueRow = blueRow;
        this.count = count;
    }
}

public class one13460 {
    private static int n;
    private static int m;
    private static char[][] map;
    private static boolean[][][][] visited;
    private static int holeCol, holeRow;
    private static Marble red, blue;
    private static int MAX_COUNT = 10;
    private static int[] dCol = new int[] {-1, 0, 1, 0};
    private static int[] dRow = new int[] {0, -1, 0, 1};
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());

        visited = new boolean[n][m][n][m];
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') {
                    red = new Marble(i, j, 0, 0, 0);
                } else if (map[i][j] == 'B') {
                    blue = new Marble(0, 0, i, j, 0);
                } else if (map[i][j] == 'O') {
                    holeCol = i;
                    holeRow = j;
                }
            }
        }
        System.out.println(bfs());
        reader.close();
    }

    private static int bfs() {
        Queue<Marble> toVisit = new LinkedList<>();
        toVisit.add(new Marble(red.redCol, red.redRow, blue.blueCol, blue.blueRow, 1));
        visited[red.redCol][red.redRow][blue.blueCol][blue.blueRow] = true;

        while (!toVisit.isEmpty()) {
            Marble marble = toVisit.poll();

            int nowRedCol = marble.redCol;
            int nowRedRow = marble.redRow;
            int nowBlueCol = marble.blueCol;
            int nowBlueRow = marble.blueRow;
            int nowCount = marble.count;

            if (nowCount > MAX_COUNT) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int nextRedCol = nowRedCol;
                int nextRedRow = nowRedRow;
                int nextBlueCol = nowBlueCol;
                int nextBlueRow = nowBlueRow;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                while (map[nextRedCol + dCol[i]][nextRedRow + dRow[i]] != '#') {
                    nextRedCol += dCol[i];
                    nextRedRow += dRow[i];

                    if (nextRedCol == holeCol && nextRedRow == holeRow) {
                        isRedHole = true;
                        break;
                    }
                }

                while (map[nextBlueCol + dCol[i]][nextBlueRow + dRow[i]] != '#') {
                    nextBlueCol += dCol[i];
                    nextBlueRow += dRow[i];

                    if (nextBlueCol == holeCol && nextBlueRow == holeRow) {
                        isBlueHole = true;
                        break;
                    }
                }

                if (isBlueHole) continue;
                if (isRedHole && !isBlueHole) return nowCount;

                if (nextRedCol == nextBlueCol && nextRedRow == nextBlueRow) {
                    if (i == 0) {
                        if (nowRedCol < nowBlueCol) nextBlueCol -= dCol[i];
                        else nextRedCol -= dCol[i];
                    } else if (i == 1) {
                        if (nowRedRow < nowBlueRow) nextBlueRow -= dRow[i];
                        else nextRedRow -= dRow[i];
                    } else if (i == 2) {
                        if (nowRedCol < nowBlueCol) nextRedCol -= dCol[i];
                        else nextBlueCol -= dCol[i];
                    } else {
                        if (nowRedRow < nowBlueRow) nextRedRow -= dRow[i];
                        else nextBlueRow -= dRow[i];
                    }
                }
                if (!visited[nextRedCol][nextRedRow][nextBlueCol][nextBlueRow]) {
                    visited[nextRedCol][nextRedRow][nextBlueCol][nextBlueRow] = true;
                    toVisit.add(new Marble(nextRedCol, nextRedRow, nextBlueCol, nextBlueRow, nowCount + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        new one13460().solution();
    }
}