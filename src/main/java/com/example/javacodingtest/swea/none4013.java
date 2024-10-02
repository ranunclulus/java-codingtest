package com.example.javacodingtest.swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since
 @link
 @timecomplex
 @performance 19724 kb 105 ms
 @category
 @note
 */
public class none4013 {
    class Magnet {
        int[] numbers;

        public Magnet(StringTokenizer token) {
            this.numbers = new int[8];
            for (int i = 0; i < 8; i++) {
                numbers[i] = Integer.parseInt(token.nextToken());
            }
        }

        public void spinRight() {
            int temp = numbers[7];
            for (int i = 7; i > 0; i--) {
                numbers[i] = numbers[i - 1];
            }
            numbers[0] = temp;
        }

        public void spinLeft() {
            int temp = numbers[0];
            for (int i = 1; i < 8; i++) {
                numbers[i - 1] = numbers[i];
            }
            numbers[7] = temp;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, k, spinNum, spinDirection, point;
    static Magnet[] magnets;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());

        for (int test = 1; test <= testNum; test++) {
            k = Integer.parseInt(reader.readLine());
            magnets = new Magnet[4];

            for (int i = 0; i < 4; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                magnets[i] = new Magnet(tokenizer);
            }

            while (k --> 0) {
                tokenizer = new StringTokenizer(reader.readLine());
                spinNum = Integer.parseInt(tokenizer.nextToken()) - 1;
                spinDirection = Integer.parseInt(tokenizer.nextToken()); //1 시계, -1 반시계

                dfs(spinNum, spinDirection, true, true);
            }
            point = 0;
            for (int i = 0; i < 4; i++) {
                if (magnets[i].numbers[0] == 1) {
                    point += (int) Math.pow(2, i);
                }
            }

            builder.append("#").append(test).append(" ").append(point).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void dfs(int magnetNum, int direction, boolean left, boolean right) {
        if (magnetNum < -1 || magnetNum >= 4) return;

        int leftNum = magnets[magnetNum].numbers[6];
        int rightNum = magnets[magnetNum].numbers[2];

        if (direction == 1) {
            magnets[magnetNum].spinRight();
        } else {
            magnets[magnetNum].spinLeft();
        }

        if (left && magnetNum > 0) {
            if (leftNum != magnets[magnetNum - 1].numbers[2]) {// 극이 다름
                dfs(magnetNum - 1, (-1) * direction, true, false);
            }
        }
        if (right && magnetNum < 3) {
            if (rightNum != magnets[magnetNum + 1].numbers[6]) {// 극이 다름
                dfs(magnetNum + 1, (-1) * direction, false, true);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new none4013().solution();
    }
}

