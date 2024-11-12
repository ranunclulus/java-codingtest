package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.12
 @link https://www.acmicpc.net/problem/16719
 @timecomplex
 @performance 14448kb 108ms
 @category
 @note
 */
public class five16719 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static String input;
    static int length;
    static boolean[] visited;

    public void solution() throws IOException {
        input = reader.readLine();
        length = input.length();
        visited = new boolean[length];

        backTracking(0, length - 1);
        writer.write(builder.toString());
        writer.flush();
    }

    private void backTracking(int left, int right) {
        if (left > right) return;

        int index = left;

        for (int i = left; i <= right; i++) {
            if (input.charAt(index) > input.charAt(i)) index = i;
        }
        visited[index] = true;

        for (int i = 0; i < length; i++) {
            if (visited[i]) builder.append(input.charAt(i));
        }
        builder.append('\n');
        backTracking(index + 1, right);
        backTracking(left, index - 1);
    }

    public static void main(String[] args) throws IOException {
        new five16719().solution();
    }
}
