package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.10
 @link
 @timecomplex
 @performance 18552kb 140ms
 @category
 @note
 */
public class none4014 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testCase, n, x, answer;
    static int[][] map;
    static int[] row;

    public void solution() throws IOException {
        testCase = Integer.parseInt(reader.readLine());

        for (int test = 1; test <= testCase; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            x = Integer.parseInt(tokenizer.nextToken());
            map = new int[n][n];
            answer = 0;

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                if (checkPossible(map[i])) {
                    //System.out.printf("%d행\n", i);
                    answer++;
                }
            }
            row = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    row[j] = map[j][i];
                }
                if (checkPossible(row)) {
                    //System.out.printf("%d열\n", i);
                    answer++;
                }
            }
            builder.append("#" + test + " " + answer + "\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private boolean checkPossible(int[] row) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            if (row[i] == row[i + 1]) continue;

            if (row[i] > row[i + 1]) { // 3 2 이런 식이라 오른쪽에 경사로 건설
                if (row[i] - row[i + 1] > 1) return false; // 높이가 1 차이 이상이면
                if (i + x >= n) return false; // 경계 밖이면
                for (int j = i + 1; j < i + x; j++) {
                    if (row[j] != row[j + 1]) return false; // 높이가 일정하지 않으면
                }
                for (int j = i + 1; j <= i + x; j++) {
                    if (visited[j]) return false;
                    visited[j] = true;
                }
            } else if (row[i] < row[i + 1]) { // 2 3 이런 식이라 왼쪽에 경사로 건설
                if (row[i + 1] - row[i] > 1) return false; // 높이가 1 차이 이상이면
                if (i - x + 1 < 0) return false; // 경계 밖이면
                for (int j = i - x + 1; j <= i - 1; j++) {
                    if (row[j] != row[j + 1]) return false; // 높이가 일정하지 않으면
                }
                for (int j = i - x + 1; j <= i; j++) {
                    if (visited[j]) return false;
                    visited[j] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new none4014().solution();
    }
}

