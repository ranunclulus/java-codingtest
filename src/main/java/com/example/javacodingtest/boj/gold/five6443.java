package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.12.08
 @link https://www.acmicpc.net/problem/6443
 @timecomplex
 @performance 172256ms 724kb
 @category
 @note
 */
public class five6443 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, length;
    static String word;
    static boolean[] visited;
    static int[] output;
    static int[] charCount;

    static Set<String> result;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        result = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            result = new TreeSet<>();
            charCount = new int[26];
            word = reader.readLine();
            length = word.length();

            for (int j = 0; j < length; j++) {
                charCount[word.charAt(j) - 97]++;
            }

            visited = new boolean[length];
            output = new int[length];
            backtracking(0, length);

            for(String answer : result) {
                builder.append(answer).append('\n');
            }
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void backtracking(int depth, int length) {
        if (depth == length) {
            String candidate = "";
            for(int index : output) {
                candidate += (char) (index + 97);
            }
            result.add(candidate);
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (charCount[i] == 0) continue;
            charCount[i]--;
            output[depth] = i;
            backtracking(depth + 1, length);
            charCount[i]++;
        }
    }


    public static void main(String[] args) throws IOException {
        new five6443().solution();
    }
}
