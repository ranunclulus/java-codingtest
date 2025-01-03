package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.01.03
 @link https://www.acmicpc.net/problem/17281
 @timecomplex
 @performance 64904kb 612ms
 @category
 @note
 */
public class four17281 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, answer;
    static int[][] players;
    static int[] lineup;
    static boolean[] selected;


    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        players = new int[n][9];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 9; j++) {
                players[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        selected = new boolean[9];
        lineup = new int[9];
        selected[3] = true;
        lineup[3] = 0;

        makePermutation(1);

        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    private void makePermutation(int number) {
        if (number == 9) {
            game();
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (selected[i]) continue;
            selected[i] = true;
            lineup[i] = number;
            makePermutation(number + 1);
            selected[i] = false;
        }
    }

    private void game() {
        int score = 0;
        int startPlayer = 0;
        boolean[] baseFull;

        for (int i = 0; i < n; i++) {
            int outCount = 0;
            baseFull = new boolean[3];

            outer: while(true) {
                for (int j = startPlayer; j < 9; j++) {
                    int nowPlayer = players[i][lineup[j]];

                    if (nowPlayer == 0) {
                        outCount++;
                    } else if (nowPlayer == 1) {
                        for (int k = 2; k >= 0; k--) {
                            if (baseFull[k]) {
                                if (k == 2) {
                                    score++;
                                    baseFull[k] = false;
                                    continue;
                                }
                                baseFull[k] = false;
                                baseFull[k + 1] = true;
                            }
                        }
                        baseFull[0] = true;
                    } else if (nowPlayer == 2) {
                        for (int k = 2; k >= 0; k--) {
                            if (baseFull[k]) {
                                if (k == 2 || k == 1) {
                                    score++;
                                    baseFull[k] = false;
                                    continue;
                                }
                                baseFull[k] = false;
                                baseFull[k + 2] = true;
                            }
                        }
                        baseFull[1] = true;
                    } else if (nowPlayer == 3){
                        for (int k = 2; k >= 0; k--) {
                            if (baseFull[k]) {
                                score++;
                                baseFull[k] = false;
                            }
                        }
                        baseFull[2] = true;
                    } else {
                        for (int k = 2; k >= 0; k--) {
                            if (baseFull[k]) {
                                score++;
                                baseFull[k] = false;
                            }
                        }
                        score++;
                    }

                    if (outCount == 3) {
                        startPlayer = j + 1;
                        if (startPlayer == 9) startPlayer = 0;
                        break outer;
                    }
                }
                startPlayer = 0;
            }
        }
        answer = Math.max(answer, score);
    }

    public static void main(String[] args) throws IOException {
        new four17281().solution();
    }
}
