package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.05
 @link https://www.acmicpc.net/problem/2477
 @timecomplex
 @performance 14388kb, 104ms
 @category
 @note
 */
public class two2477_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int k;
    static int[] length;
    static int maxArea = Integer.MIN_VALUE;
    static int minArea = Integer.MAX_VALUE;
    static int index;

    public void solution() throws IOException {
        k = Integer.parseInt(reader.readLine());
        length = new int[6];
        for (int i = 0; i < 6; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int direction = Integer.parseInt(tokenizer.nextToken());
            length[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < 6; i++) {
            int area = length[i] * length[(i + 1) % 6];
            if (area > maxArea) {
                maxArea = area;
                index = i;
            }
        }
        minArea = (length[(index + 3) % 6] * length[(index + 4) % 6]);
        builder.append((maxArea - minArea) * k);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new two2477_2().solution();
    }
}
