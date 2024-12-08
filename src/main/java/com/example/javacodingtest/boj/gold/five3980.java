package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.08
 @link https://www.acmicpc.net/problem/3980
 @timecomplex
 @performance 15740kb 144ms
 @category
 @note
 */
public class five3980 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, maxValue;
    static int[][] abilities;
    static boolean[] finished;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());

        for (int test = 0; test < testNum; test++) {
            maxValue = Integer.MIN_VALUE;
            abilities = new int[11][11];
            for (int i = 0; i < 11; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < 11; j++) {
                    abilities[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            
            finished = new boolean[11];
            backtracking(0, 0);
            builder.append(maxValue).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void backtracking(int depth, int value) {
        if (depth == 11) {
            maxValue = Integer.max(maxValue, value);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (finished[i]) continue;
            if (abilities[depth][i] == 0) continue;

            value += abilities[depth][i];
            finished[i] = true;
            backtracking(depth + 1, value);
            value -= abilities[depth][i];
            finished[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        new five3980().solution();
    }
}
