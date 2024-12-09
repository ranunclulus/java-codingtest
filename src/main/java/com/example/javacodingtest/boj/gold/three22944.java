package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.12.09
 @link https://www.acmicpc.net/problem/22944
 @timecomplex 47992kb 416ms
 @performance
 @category
 @note
 */
public class three22944 {
    class Position {
        int row;
        int col;
        int h;
        int cost;
        int count;

        public Position(int row, int col, int h, int cost, int count) {
            this.row = row;
            this.col = col;
            this.h = h;
            this.cost = cost;
            this.count = count;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, h, d, minDistance;
    static char[][] map;
    static int[][] visited;
    static int startRow = -1, startCol = -1;
    static int[][] deltas = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


    public void solution() throws IOException {
        minDistance = Integer.MAX_VALUE;
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        h = Integer.parseInt(tokenizer.nextToken());
        d = Integer.parseInt(tokenizer.nextToken());

        map = new char[n][n];
        visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                }
            }
        }

        bfs(startRow, startCol);

        builder.append(minDistance);
        writer.write(builder.toString());
        writer.flush();
    }

    private void bfs(int startRow, int startCol) {
        Deque<Position> toVisit = new ArrayDeque<>();
        toVisit.add(new Position(startRow, startCol, h, 0, 0));
        visited[startRow][startCol] = h;

        minDistance = Integer.MAX_VALUE;

        while (!toVisit.isEmpty()) {
            Position now = toVisit.poll();

            for (int i = 0; i < 4; i++) {
                int nowH = now.h;
                int nowCost = now.cost;
                int nowCount = now.count;

                int nextRow = now.row + deltas[i][0];
                int nextCol = now.col + deltas[i][1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;

                if (map[nextRow][nextCol] == 'E') {
                    minDistance = Math.min(minDistance, nowCount + 1);
                    continue;
                }

                if (map[nextRow][nextCol] == 'U') nowCost = d;

                if (nowCost != 0) nowCost--;
                else nowH--;

                if (nowH == 0) continue;

                if (visited[nextRow][nextCol] < nowH + nowCost) {
                    visited[nextRow][nextCol] = nowH + nowCost;
                    toVisit.add(new Position(nextRow, nextCol, nowH, nowCost, nowCount + 1));
                }
            }
        }
        if (minDistance == Integer.MAX_VALUE) minDistance = -1;
    }

    public static void main(String[] args) throws IOException {
        new three22944().solution();
    }
}
