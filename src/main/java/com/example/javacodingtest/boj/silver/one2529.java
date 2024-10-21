package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.21
 @link https://solved.ac/search?query=2529
 @timecomplex
 @performance 29556kb, 232ms
 @category
 @note
 */
public class one2529 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[] inequalities;
    static int[] answer;
    static boolean[] visited;
    static List<String> candidates;


    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        inequalities = new int[n];
        answer = new int[n + 1];
        visited = new boolean[10];
        candidates = new LinkedList<>();
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            if(tokenizer.nextToken().equals("<")) inequalities[i] = -1;
            else inequalities[i] = 1;
        }

        for (int i = 0; i <= 9; i++) {
            answer[0] = i;
            visited[i] = true;
            backTracking(0);
            visited[i] = false;

        }

        builder.append(candidates.get(candidates.size() - 1)).append("\n");
        builder.append(candidates.get(0));
        writer.write(builder.toString());
        writer.flush();
    }

    private void backTracking(int depth) {
        if (depth == n) {
            String candidate = "";
            for (int i = 0; i <= n; i++) {
                candidate += answer[i];
            }
            candidates.add(candidate);
            return;
        }

        int inequality = inequalities[depth];

        if (inequality < 0) { // "<"
            for (int i = 0; i <= 9; i++) {
                if (visited[i]) continue;
                if (answer[depth] < i) {
                    visited[i] = true;
                    answer[depth + 1] = i;
                    backTracking(depth + 1);
                    visited[i] = false;
                }
            }
        } else { // ">"
            for (int i = 0; i <= 9; i++) {
                if (visited[i]) continue;
                if (answer[depth] > i) {
                    visited[i] = true;
                    answer[depth + 1] = i;
                    backTracking(depth + 1);
                    visited[i] = false;
                }
            }
        }


    }


    public static void main(String[] args) throws IOException {
        new one2529().solution();
    }
}


/*

9
> < < < > > > < <
 */
