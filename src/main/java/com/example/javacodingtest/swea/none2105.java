package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.27
 @link
 @timecomplex
 @performance 22764KB, 182MS
 @category
 @note DFS
 */
public class none2105 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] dessert;
    static int[][] deltas = new int[][] {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};
    static int max;

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            n = Integer.parseInt(br.readLine());
            max = -1;
            map = new int[n][n];
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            for (int i = 0; i + 2 < n; i++) {
                for (int j = 1; j < n - 1; j++) {
                    dessert = new boolean[101];
                    dessert[map[i][j]] = true;
                    dfs(i, j, i, j, 1, 0);
                }
            }
            sb.append("#" + test + " " + max + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void dfs(int startRow, int startCol, int row, int col, int cnt, int dir) {
        for (int i = dir; i < 4; i++) {
            int nextRow = row + deltas[i][0];
            int nextCol = col + deltas[i][1];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
            if (nextRow == startRow && nextCol == startCol && cnt >= 3) {
                max = Math.max(max, cnt);
                return;
            }

            if (visited[nextRow][nextCol]) continue;
            if (dessert[map[nextRow][nextCol]]) continue;


            visited[row][col] = true;
            dessert[map[nextRow][nextCol]] = true;
            dfs(startRow, startCol, nextRow, nextCol, cnt + 1, i);
            visited[row][col] = false;
            dessert[map[nextRow][nextCol]] = false;
        }
    }


    public static void main(String[] args) throws IOException {
        new none2105().solution();
    }
}

