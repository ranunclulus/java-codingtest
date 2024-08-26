package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.26
 @link
 @timecomplex
 @performance 89820KB, 364MS
 @category
 @note
 */
public class four7733 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int[][] map;
    static int maxCheese = Integer.MIN_VALUE;
    static int maxGroup;
    static boolean[][] visited;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            maxGroup = 1;
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxCheese = Math.max(maxCheese, map[i][j]);
                }
            }

            for (int cheese = 2; cheese <= maxCheese; cheese++) {
                visited = new boolean[n][n];
                int groupCnt = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (!visited[i][j] && map[i][j] > cheese) {
                            bfs(i, j, cheese);
                            groupCnt++;
                        }
                    }
                }
                maxGroup = Math.max(maxGroup, groupCnt);
            }

            sb.append("#").append(test).append(" ").append(maxGroup).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void bfs(int row, int col, int cheese) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + deltas[i][0];
                int nextCol = cur[1] + deltas[i][1];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
                if (map[nextRow][nextCol] <= cheese) continue;
                if (visited[nextRow][nextCol]) continue;
                queue.add(new int[]{nextRow, nextCol});
                visited[nextRow][nextCol] = true;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new four7733().solution();
    }
}

