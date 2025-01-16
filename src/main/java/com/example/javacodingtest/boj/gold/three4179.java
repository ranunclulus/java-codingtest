package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.01.15
 @link https://www.acmicpc.net/problem/4179
 @timecomplex
 @performance 72216kb 548ms
 @category
 @note
 */
public class three4179 {
    class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, answer;
    static char[][] map;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] fires, times;
    static Queue<Point> jihunQueue;
    static Queue<Point> fireQueue;


    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        answer = -1;

        map = new char[n][m];
        fires = new int[n][m];
        times = new int[n][m];
        fireQueue = new LinkedList<>();
        jihunQueue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String s = reader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                fires[i][j] = -1;
                times[i][j] = -1;
                if (map[i][j] == 'F') {
                    fireQueue.offer(new Point(i, j));
                    fires[i][j] = 0;
                }
                if (map[i][j] == 'J') {
                    jihunQueue.offer(new Point(i, j));
                    times[i][j] = 0;
                }
            }
        }

        while (!fireQueue.isEmpty()) {
            Point now = fireQueue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nextRow = now.row + deltas[dir][0];
                int nextCol = now.col + deltas[dir][1];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
                if (fires[nextRow][nextCol] >= 0 || map[nextRow][nextCol] == '#') continue;
                fires[nextRow][nextCol] = fires[now.row][now.col] + 1;
                fireQueue.offer(new Point(nextRow, nextCol));
            }
        }

        while (!jihunQueue.isEmpty()) {
            Point now = jihunQueue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nextRow = now.row + deltas[dir][0];
                int nextCol = now.col + deltas[dir][1];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                    answer = times[now.row][now.col] + 1;
                    break;
                }
                if (times[nextRow][nextCol] >= 0 || map[nextRow][nextCol] == '#') continue;
                if (fires[nextRow][nextCol] <= times[now.row][now.col] + 1) continue;
                times[nextRow][nextCol] = times[now.row][now.col] + 1;
                jihunQueue.offer(new Point(nextRow, nextCol));
            }
        }

        builder.append(answer == -1 ? "IMPOSSIBLE" : answer);
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new three4179().solution();
    }
}


/*
5 5
....F
.J...
.....
.....
F...F
 */