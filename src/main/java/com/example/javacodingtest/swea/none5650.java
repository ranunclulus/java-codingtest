package com.example.javacodingtest.swea;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.02
 @link
 @timecomplex
 @performance 42016 kb, 1311 ms
 @category
 @note
 */
public class none5650 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static List<int[]>[] warmholes;
    static int testNum, n, maxCount;
    static int[][] map;
    static int[][] deltas = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //상 우 하 좌

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine().trim());
        for (int test = 1; test <= testNum; test++) {
            n = Integer.parseInt(reader.readLine().trim());
            map = new int[n][n];
            maxCount = 0;
            warmholes = new List[11];
            for (int i = 6; i <= 10; i++) {
                warmholes[i] = new LinkedList<>();
            }

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine().trim());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                    if (map[i][j] >= 6) {
                        warmholes[map[i][j]].add(new int[] {i, j});
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != 0) continue;
                    for (int d = 0; d < 4; d++) {
                        game(i, j, d);
                    }
                }
            }

            builder.append("#" + test + ' ' + maxCount + '\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void game(int initRow, int initCol, int d) {
        int row = initRow;
        int col = initCol;
        int initDirection = d;
        int count = 0;
        boolean flag = false;
        while (true) {
            int blockNum = map[row][col];
            int nextRow = 0;
            int nextCol = 0;
            if (1 <= blockNum && blockNum <= 5) { // 블록인 경우
                count++;
                switch (blockNum) {
                    case 1: // ◣
                        switch (d) {
                            //상 우 하 좌
                            case 0:
                                d = (d + 2) % 4; // 방향만 반대로 바꾼다
                                break;
                            case 1:
                                d = (d + 2) % 4; // 방향만 반대로 바꾼다
                                break;
                            case 2:
                                d = 1;
                                break;
                            case 3:
                                d = 0;
                                break;
                        }
                        break;
                    case 2: // ◤
                        switch (d) {
                            //상 우 하 좌
                            case 0:
                                d = 1;
                                break;
                            case 1:
                                d = (d + 2) % 4; // 방향만 반대로 바꾼다
                                break;
                            case 2:
                                d = (d + 2) % 4; // 방향만 반대로 바꾼다
                                break;
                            case 3:
                                d = 2;
                                break;
                        }
                        break;
                    case 3: // ◥
                        switch (d) {
                            //상 우 하 좌
                            case 0:
                                d = 3;
                                break;
                            case 1:
                                d = 2;
                                break;
                            case 2:
                                d = (d + 2) % 4; // 방향만 반대로 바꾼다
                                break;
                            case 3:
                                d = (d + 2) % 4; // 방향만 반대로 바꾼다
                                break;
                        }
                        break;
                    case 4: // ◢
                        switch (d) {
                            //상 우 하 좌
                            case 0:
                                d = (d + 2) % 4; // 방향만 반대로 바꾼다
                                break;
                            case 1:
                                d = 0;
                                break;
                            case 2:
                                d = 3;
                                break;
                            case 3:
                                d = (d + 2) % 4; // 방향만 반대로 바꾼다
                                break;
                        }
                        break;
                    case 5: //◼︎
                        d = (d + 2) % 4; // 방향만 반대로 바꾼다
                        break;
                }
            }

            nextRow = row + deltas[d][0];
            nextCol = col + deltas[d][1];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) { // 벽에 부딪히는 경우
                count++;
                d = (d + 2) % 4; // 방향만 반대로 바꾼다
                nextRow = row;
                nextCol = col;
            }

            if ((map[nextRow][nextCol] == -1) || (nextRow == initRow && nextCol == initCol)) { // 블랙홀
                maxCount = Math.max(maxCount, count);
                break;
            }

            if (map[nextRow][nextCol] >= 6 && map[nextRow][nextCol] <= 10) { // 홀로 이동하기
                int warmNum = map[nextRow][nextCol];
                for (int i = 0; i < 2; i++) {
                    if (warmholes[warmNum].get(i)[0] == nextRow && warmholes[warmNum].get(i)[1] == nextCol) {
                        nextRow = (i == 0) ? warmholes[warmNum].get(1)[0] : warmholes[warmNum].get(0)[0];
                        nextCol = (i == 0) ? warmholes[warmNum].get(1)[1] : warmholes[warmNum].get(0)[1];
                        break;
                    }
                }
            }
            row = nextRow;
            col = nextCol;
        }

    }

    public static void main(String[] args) throws IOException {
        new none5650().solution();
    }
}

