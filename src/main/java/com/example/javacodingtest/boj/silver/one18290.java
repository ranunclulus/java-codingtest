package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.14
 @link https://www.acmicpc.net/problem/18290
 @timecomplex
 @performance 23040 KB 3420 MS
 @category #combination
 @note
 */
public class one18290 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int k;
    static int[][] map;
    static int[][] pop;
    static boolean[][] visited;
    static int[][] delta = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int maxValue = Integer.MIN_VALUE;


    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        pop = new int[k][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        sb.append(maxValue);
        bw.write(sb.toString());
        bw.flush();
    }

    private void dfs(int depth, int index) throws IOException {
        if (depth == k) {
            maxValue = Math.max(maxValue, calValue());
            return;
        }
        for (int i = index; i < n * m; i++) {
            int row = i / m;
            int col = i % m;
            if (!visited[row][col]) {
                if (!isBoundary(row, col, depth)) {
                    visited[row][col] = true;
                    pop[depth][0] = row;
                    pop[depth][1] = col;
                    dfs(depth + 1, index + 1);
                    visited[row][col] = false;
                }
            }
        }
    }

    private boolean isBoundary(int row, int col, int depth) {
        for (int i = 0; i < depth; i++) {
            int[] point = pop[i];
            for (int j = 0; j < 4; j++) {
                int newRow = point[0] + delta[j][0];
                int newCol = point[1] + delta[j][1];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {
                    if (newRow == row && newCol == col) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int calValue() {
        int sum = 0;
        for (int[] row : pop) {
            sum += map[row[0]][row[1]];
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        new one18290().solution();
    }
}
