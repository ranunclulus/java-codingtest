package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.25
 @link https://www.acmicpc.net/problem/20057
 @timecomplex
 @performance 35080kb 456ms
 @category
 @note
 */
public class two20057 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    // 좌 하 우 상
    static int[][] deltas = new int[][] {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[] size = new int[] {1, 1, 2, 2};
    static int[] ratio = new int[] {1, 1, 2, 7, 7, 2, 10, 10, 5};
    static int[][][] spread = new int[][][] {
            // 좌
            {{-1, 1}, {1, 1}, {-2, 0}, {-1, 0}, {1, 0}, {2, 0}, {-1, -1}, {1, -1}, {0, -2}},
            // 하
            {{-1, -1}, {-1, 1}, {0, -2}, {0, -1}, {0, 1}, {0, 2}, {1, -1}, {1, 1}, {2, 0}},
            // 우
            {{1, -1}, {-1, -1}, {2, 0}, {1, 0}, {-1, 0}, {-2, 0}, {1, 1}, {-1, 1}, {0, 2}},
            // 상
            {{1, 1}, {1, -1}, {0, 2}, {0, 1}, {0, -1}, {0, -2}, {-1, 1}, {-1, -1}, {-2, 0}}};

    static int n;
    static int[][] map;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        builder.append(calculateOutSand(n / 2, n / 2));
        writer.write(builder.toString());
        writer.flush();
    }

    private int calculateOutSand(int row, int col) {
        int totalOutSand = 0;

        int currentRow = row;
        int currentCol = col;

        while(true) {
            for (int dir = 0; dir < 4; dir++) {
                for (int movement = 0; movement < size[dir]; movement++) {
                    int nextRow = currentRow + deltas[dir][0];
                    int nextCol = currentCol + deltas[dir][1];

                    if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) return totalOutSand;

                    int sand = map[nextRow][nextCol];
                    map[nextRow][nextCol] = 0;
                    int totalSpread = 0;

                    for (int spreadNum = 0; spreadNum < 9; spreadNum++) {
                        int spreadRow = nextRow + spread[dir][spreadNum][0];
                        int spreadCol = nextCol + spread[dir][spreadNum][1];
                        int spreadAmount = ((sand * ratio[spreadNum]) / 100);

                        if (spreadRow < 0 || spreadRow >= n || spreadCol < 0 || spreadCol >= n) {
                            totalOutSand += spreadAmount;
                        }
                        else {
                            map[spreadRow][spreadCol] += spreadAmount;
                        }
                        totalSpread += spreadAmount;
                    }

                    int alphaRow = nextRow + deltas[dir][0];
                    int alphaCol = nextCol + deltas[dir][1];
                    int alphaAmount = sand - totalSpread;
                    if (alphaRow < 0 || alphaRow >= n || alphaCol < 0 || alphaCol >= n) {
                        totalOutSand += alphaAmount;
                    }
                    else map[alphaRow][alphaCol] += alphaAmount;

                    currentRow = nextRow;
                    currentCol = nextCol;
                }
            }
            for (int dir = 0; dir < 4; dir++) {
                size[dir] += 2;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two20057().solution();
    }
}
