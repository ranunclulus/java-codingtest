package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.23
 @link
 @timecomplex
 @performance 18332KB, 123MS
 @category
 @note
 */
public class three1873 {
    class Tank {
        int row;
        int col;
        int direction;

        public Tank(int row, int col, char direction) {
            this.row = row;
            this.col = col;
            switch(direction) {
                case '^':
                    this.direction = 0;
                    break;
                case 'v':
                    this.direction = 1;
                    break;
                case '<':
                    this.direction = 2;
                    break;
                case '>':
                    this.direction = 3;
                    break;
            }
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int testNum;
    static int h;
    static int w;
    static char[][] map;
    static int n;
    static char[] operations;
    static Tank tank;
    static int[][] deltas = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
            }
            
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '^' ||
                        map[i][j] == 'v' ||
                        map[i][j] == '<' ||
                        map[i][j] == '>') {
                        tank = new Tank(i, j, map[i][j]);
                    }
                }
            }
            n = Integer.parseInt(br.readLine());
            operations = br.readLine().toCharArray();
            
            for(char operation : operations) {
                switch(operation) {
                    case 'U':
                        tank.direction = 0;
                        map[tank.row][tank.col] = '^';
                        moveOne();
                        break;
                    case 'D':
                        tank.direction = 1;
                        map[tank.row][tank.col] = 'v';
                        moveOne();
                        break;
                    case 'L':
                        tank.direction = 2;
                        map[tank.row][tank.col] = '<';
                        moveOne();
                        break;
                    case 'R':
                        tank.direction = 3;
                        map[tank.row][tank.col] = '>';
                        moveOne();
                        break;
                    case 'S':
                        shooting();
                        break;
                }
            }

            sb.append("#").append(test).append(" ");
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void shooting() {
        int nextRow = tank.row + deltas[tank.direction][0];
        int nextCol = tank.col + deltas[tank.direction][1];
        while (nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
            if (map[nextRow][nextCol] != '.' && map[nextRow][nextCol] != '-') break;
            nextRow += deltas[tank.direction][0];
            nextCol += deltas[tank.direction][1];
        }

        if (nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) return;
        if (map[nextRow][nextCol] == '#') return;
        if (map[nextRow][nextCol] == '*') map[nextRow][nextCol] = '.';

    }

    private void moveOne() {
        int nextRow = tank.row + deltas[tank.direction][0];
        int nextCol = tank.col + deltas[tank.direction][1];
        
        if (nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) return;
        if (map[nextRow][nextCol] != '.') return;
        map[nextRow][nextCol] = map[tank.row][tank.col];
        map[tank.row][tank.col] = '.';
        tank.row = nextRow;
        tank.col = nextCol;
    }


    public static void main(String[] args) throws IOException {
        new three1873().solution();
    }
}
