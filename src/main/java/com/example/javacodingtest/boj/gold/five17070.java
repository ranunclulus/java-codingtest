package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.05
 @link https://www.acmicpc.net/problem/17070
 @timecomplex
 @performance 280608kb, 512ms
 @category
 @note
 */
public class five17070 {
    static class Pipe {
        int x;
        int y;
        int direction; // 가로 0, 세로 1, 대각선 2


        public Pipe(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    static int[][] board;
    static int n;
    static int count = 0;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        if (board[n - 1][n - 1] == 0) {

            Queue<Pipe> toVisit = new LinkedList<>();
            toVisit.add(new Pipe(0, 1, 0));
            while (!toVisit.isEmpty()) {
                Pipe pipe = toVisit.poll();
                int x = pipe.x;
                int y = pipe.y;
                if (x == n - 1 && y == n - 1) {
                    count++;
                    continue;
                }

                if (pipe.direction == 0) {
                    if (x < n && y + 1 < n && board[x][y + 1] == 0) {
                        toVisit.add(new Pipe(x, y + 1, 0));
                    }
                    if (x + 1 < n && y + 1 < n && canCross(x, y)) {
                        toVisit.add(new Pipe(x + 1, y + 1, 2));
                    }
                } else if (pipe.direction == 1) {
                    if (x + 1 < n && y < n && board[x + 1][y] == 0) {
                        toVisit.add(new Pipe(x + 1, y, 1));
                    }
                    if (x + 1 < n && y + 1 < n && canCross(x, y)) {
                        toVisit.add(new Pipe(x + 1, y + 1, 2));
                    }
                } else if (pipe.direction == 2) {
                    if (x < n && y + 1 < n && board[x][y + 1] == 0) {
                        toVisit.add(new Pipe(x, y + 1, 0));
                    }
                    if (x + 1 < n && y < n && board[x + 1][y] == 0) {
                        toVisit.add(new Pipe(x + 1, y, 1));
                    }
                    if (x + 1 < n && y + 1 < n && canCross(x, y)) {
                        toVisit.add(new Pipe(x + 1, y + 1, 2));
                    }
                }
            }
        }

        builder.append(count);
        writer.write(builder.toString());
        writer.flush();

    }

    private boolean canCross(int x, int y) {
        return board[x + 1][y] == 0 && board[x][y + 1] == 0 && board[x + 1][y + 1] == 0;
    }

    public static void main(String[] args) throws IOException {
        new five17070().solution();
    }
}
