package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.01
 @link https://jyunslog.tistory.com/1
 @timecomplex
 @performance 70728kb 300ms
 @category
 @note
 */
public class five7562 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = new int[][] {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    static int startCol, startRow, endCol, endRow;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());

        for (int i = 0; i < testNum; i++) {
            n = Integer.parseInt(reader.readLine());
            map = new int[n][n];
            visited = new boolean[n][n];

            tokenizer = new StringTokenizer(reader.readLine());
            startRow = Integer.parseInt(tokenizer.nextToken());
            startCol = Integer.parseInt(tokenizer.nextToken());
            tokenizer = new StringTokenizer(reader.readLine());
            endRow = Integer.parseInt(tokenizer.nextToken());
            endCol = Integer.parseInt(tokenizer.nextToken());

            bfs();

            builder.append(map[endRow][endCol]).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public void bfs() {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {startRow, startCol});
        visited[startRow][startCol] = true;

        while (!deque.isEmpty()) {
            int[] now = deque.poll();

            for (int i = 0; i < 8; i++) {
                int nextRow = now[0] + deltas[i][0];
                int nextCol = now[1] + deltas[i][1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
                if (visited[nextRow][nextCol]) continue;

                deque.add(new int[] {nextRow, nextCol});
                map[nextRow][nextCol] = map[now[0]][now[1]] + 1;
                visited[nextRow][nextCol] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five7562().solution();
    }
}
