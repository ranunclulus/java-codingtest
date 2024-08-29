package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.30
 @link https://www.acmicpc.net/problem/2178
 @timecomplex
 @performance 16708 KB, 164 MS
 @category
 @note
 */
public class one2178_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int rowNum;
    static int colNum;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int result;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        rowNum = Integer.parseInt(st.nextToken());
        colNum = Integer.parseInt(st.nextToken());

        map = new int[rowNum][colNum];
        visited = new boolean[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < colNum; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        Deque<int[]> toVisit = new ArrayDeque<>();
        toVisit.add(new int[] {0, 0, 1});
        visited[0][0] = true;
        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();

            if (now[0] == rowNum - 1 && now[1] == colNum - 1) {
                result = now[2];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = now[0] + deltas[i][0];
                int nextCol = now[1] + deltas[i][1];

                if (nextRow < 0 || nextRow >= rowNum || nextCol < 0 || nextCol >= colNum) continue;
                if (visited[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] == 0) continue;

                toVisit.add(new int[] {nextRow, nextCol, now[2] + 1});
                visited[nextRow][nextCol] = true;
            }
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new one2178_2().solution();
    }
}
