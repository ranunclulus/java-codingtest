package com.example.javacodingtest.boj.platinum;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.23
 @link https://www.acmicpc.net/problem/19235
 @timecomplex
 @performance 23652kb 348ms
 @category
 @note
 */
public class five19235 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static char[][] map;
    static int n, row, col, block, score;
    static List<int[][]> blocks;

    public void solution() throws IOException {
        initMap();

        n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            block = Integer.parseInt(tokenizer.nextToken()) - 1;
            row = Integer.parseInt(tokenizer.nextToken());
            col = Integer.parseInt(tokenizer.nextToken());

            placeBlock();
            removeBlock();
            shiftBlocks();

            printMap();
        }

        builder.append(score).append('\n');
        builder.append(countBlock()).append('\n');
        writer.write(builder.toString());
        writer.flush();
    }

    private void placeBlock() {
        moveBlue();
        moveGreen();
    }

    private void moveGreen() {
        int nowRow = row;
        int nowCol = col;
        switch (block) {
            case 0:
                while (nowRow + 1 < 10 && map[nowRow + 1][nowCol] != 'P') {
                    nowRow++;
                }
                break;
            case 1:
                while (nowRow + 1 < 10 && nowCol + 1 < 10 &&
                        map[nowRow + 1][nowCol] != 'P' && map[nowRow + 1][nowCol + 1] != 'P') {
                    nowRow++;
                }
                break;
            case 2:
                while (nowRow + 2 < 10 && map[nowRow + 1][nowCol] != 'P' && map[nowRow + 2][nowCol] != 'P') {
                    nowRow++;
                }
                break;
        }
        for (int[] dir : blocks.get(block)) {
            int nextRow = nowRow + dir[0];
            int nextCol = nowCol + dir[1];
            map[nextRow][nextCol] = 'P';
        }
    }

    private void moveBlue() {
        int nowRow = row;
        int nowCol = col;
        switch (block) {
            case 0:
                while (nowCol + 1 < 10 && map[nowRow][nowCol + 1] != 'P') {
                    nowCol++;
                }
                break;
            case 1:
                while (nowCol + 2 < 10 &&
                        map[nowRow][nowCol + 1] != 'P' && map[nowRow][nowCol + 2] != 'P') {
                    nowCol++;
                }
                break;
            case 2:
                while (nowRow + 1 < 10 && nowCol + 1 < 10 &&
                        map[nowRow + 1][nowCol] != 'P' && map[nowRow + 1][nowCol + 1] != 'P') {
                    nowCol++;
                }
                break;
        }
        for (int[] dir : blocks.get(block)) {
            int nextRow = nowRow + dir[0];
            int nextCol = nowCol + dir[1];
            map[nextRow][nextCol] = 'P';
        }
    }

    public int countBlock() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i < 4 && j < 4) continue;
                if (map[i][j] == 'P') count++;
            }
        }
        return count;
    }

    private void removeBlock() {
        for (int i = 6; i < 10; i++) {
            boolean isRemoved = true;
            for (int j = 0; j < 4; j++) {
                if (map[j][i] != 'P') {
                    isRemoved = false;
                    break;
                }
            }

            if (isRemoved) {
                score++;
                for (int j = 0; j < 4; j++) {
                    map[j][i] = 'B';
                }
            }
        }

        for (int i = 6; i < 10; i++) {
            boolean isRemoved = true;
            for (int j = 0; j < 4; j++) {
                if (map[i][j] != 'P') {
                    isRemoved = false;
                    break;
                }
            }

            if (isRemoved) {
                score++;
                for (int j = 0; j < 4; j++) {
                    map[i][j] = 'G';
                }
            }
        }
    }

    private boolean needShift(char color) {
        if (color == 'B') {
            for (int i = 0; i < 4; i++) {
                for (int j = 4; j < 6; j++) {
                    if (map[i][j] == 'P') return true;
                }
            }
            return false;
        }
        else {
            for (int i = 4; i < 6; i++) {
                for (int j = 0; j < 4; j++) {
                    if (map[i][j] == 'P') return true;
                }
            }
            return false;
        }
    }

    private void shiftBlocks() {
        while (needShift('B')) {
            for (int i = 9; i >= 6; i--) {
                for (int j = 0; j < 4; j++) {
                    if (map[j][i - 1] == 'b') map[j][i] = 'B';
                    else map[j][i] = map[j][i - 1];
                }
            }
            for (int i = 0; i < 4; i++) {
                map[i][5] = 'b';
            }
        }

        while (needShift('G')) {
            for (int i = 9; i >= 6; i--) {
                for (int j = 0; j < 4; j++) {
                    if (map[i - 1][j] == 'g') map[i][j] = 'G';
                    else map[i][j] = map[i - 1][j];
                }
            }
            for (int i = 0; i < 4; i++) {
                map[5][i] = 'g';
            }
        }
    }

    public void printMap() {
        for(char[] row : map) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public void initMap() {
        map = new char[10][10];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = 'R';
            }
        }

        for (int i = 4; i < 10; i++) {
            for (int j = 4; j < 10; j++) {
                map[i][j] = 'X';
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 6; j < 10; j++) {
                map[i][j] = 'B';
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 4; j < 6; j++) {
                map[i][j] = 'b';
            }
        }

        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = 'G';
            }
        }

        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = 'g';
            }
        }

        blocks = new ArrayList<>();
        blocks.add(new int[][] {{0, 0}});
        blocks.add(new int[][] {{0, 0}, {0, 1}});
        blocks.add(new int[][] {{0, 0}, {1, 0}});
    }

    public static void main(String[] args) throws IOException {
        new five19235().solution();
    }
}
