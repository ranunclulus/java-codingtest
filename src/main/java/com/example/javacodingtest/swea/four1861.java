package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.22
 @link
 @timecomplex
 @performance 114416KB, 1373MS
 @category
 @note
 */
public class four1861 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int[][] map;
    static PriorityQueue<int[]> pq;
    static int[][] deltas = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
                                        // 상 하 좌 우
    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            pq = new PriorityQueue<>((o1, o2) -> (o1[0] == o2[0]) ? o1[1] - o2[1] : -(o1[0] - o2[0]));
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bfs(i, j, new boolean[n][n]);
                }
            }

            int[] result = pq.poll();
            sb.append("#").append(test).append(" ").append(result[1]).append(" ").append(result[0]).append("\n");

        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void bfs(int row, int col, boolean[][] visited) {

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;
        int count = 1;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newRow = cur[0] + deltas[i][0];
                int newCol = cur[1] + deltas[i][1];

                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n) continue;
                if (visited[newRow][newCol]) continue;
                if (map[newRow][newCol] != (map[cur[0]][cur[1]] + 1)) continue;

                queue.add(new int[]{newRow, newCol});
                visited[newRow][newCol] = true;
                count++;
            }
        }

        pq.add(new int[] {count, map[row][col]});
    }

    public static void main(String[] args) throws IOException {
        new four1861().solution();
    }
}
