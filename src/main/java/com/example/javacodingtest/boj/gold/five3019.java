package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.24
 @link https://www.acmicpc.net/problem/3019
 @timecomplex
 @performance
 @category
 @note
 */
public class five3019 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int c, p;
    static int[][] map;
    static int[][][] deltas = new int[][][] {
            {{0, 0}, {-1, 0}, {-2, 0}, {-3, 0}},
            {{0, 0}, {-1, 0}, {0, 1}, {-1, 1}},
            {{0, 0}, {0, 1}, {-1, 1}, {-1, 2}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            {{0, 0}, {0, 1}, {0, 2}, {1, -1}},
            {{0, 0}, {0, 1}, {0, 2}, {-1, 2}},
            {{0, 0}, {-1, 0}, {0, 1}, {0, 2}}
    };

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        c = Integer.parseInt(tokenizer.nextToken());
        p = Integer.parseInt(tokenizer.nextToken());
        map = new int[111][c];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < c; i++) {
            int number = Integer.parseInt(tokenizer.nextToken());
            for (int j = 110; j > 110 - number; j--) {
                map[j][i] = 1;
            }
        }

        for(int[] row : map) {
            System.out.println(Arrays.toString(row));
        }


    }

    public static void main(String[] args) throws IOException {
        new five3019().solution();
    }
}
