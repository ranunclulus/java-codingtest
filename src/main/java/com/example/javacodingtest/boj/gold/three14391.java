package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.03.17
 @link https://www.acmicpc.net/problem/14391
 @timecomplex
 @performance 17292MS 124KB
 @category
 @note
 */
public class three14391 {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int N, M, answer;
    static int[][] numbers;
    static boolean[][] visited;
    
    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        numbers = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < M; j++) {
                numbers[i][j] = line.charAt(j) - '0';
            }
        }

        dfs(0, 0);

        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();

    }

    public void dfs(int depth, int sum) {
        if (depth == N * M) {
            answer = Math.max(answer, sum);
            return;
        }

        int row = depth / M;
        int col = depth % M;

        // 이미 방문한 곳이면 패스
        if (visited[row][col]) {
            dfs(depth + 1, sum);
        }
        else {
            // 이번 한 칸으로 끝내는 경우
            visited[row][col] = true;
            int num = numbers[row][col];
            dfs(depth + 1, sum + num);

            // 세로로 종이를 자르는 경우
            int temp = num;
            int i = 0;
            for(i = row + 1; i < N; i++) {
                if (visited[i][col]) break;
                visited[i][col] = true;
                temp = temp * 10 + numbers[i][col];
                dfs(depth + 1, sum + temp);
            }
            for(int j = row + 1; j < i; j++) {
                visited[j][col] = false;
            }

            // 가로로 종이를 자르는 경우
            temp = num;
            for(i = col + 1; i < M; i++) {
                if (visited[row][i]) break;
                visited[row][i] = true;
                temp = temp * 10 + numbers[row][i];
                dfs(depth + i - col + 1, sum + temp);
            }
            for(int j = col + 1; j < i; j++) {
                visited[row][j] = false;
            }

            visited[row][col] = false;
        }

    }


    public static void main(String[] args) throws IOException {
        new three14391().solution();
    }
}
