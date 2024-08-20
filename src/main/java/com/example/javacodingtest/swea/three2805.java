package com.example.javacodingtest.swea;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.20
 @link
 @timecomplex
 @performance KB, MS
 @category
 @note
 */
public class three2805 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int[][] map;
    static int count;
    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            count = 0;
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split("");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                }
            }

            int gap = n / 2;
            boolean flipped = false;
            for (int i = 0; i < n; i++) {
                for (int j = gap; j <= n - gap - 1; j++) {
                    count += map[j][i];
                }

                if (!flipped) gap--;
                else if (flipped) gap++;
                if (gap == 0) flipped = true;
            }

            sb.append("#").append(test).append(" ").append(count).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new three2805().solution();
    }
}

