package com.example.javacodingtest.swea;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class four4193 {
    public int n;
    public int[][] pool;
    public StringTokenizer infoToken;
    public int[] dRow = new int[] {0, 0, 1, -1};
    public int[] dCol = new int[] {1, -1, 0, 0};
    public int[] start = new int[2];
    public int[] end = new int[2];

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int testNum = Integer.parseInt(reader.readLine());

        for (int test = 1; test <= testNum; test++) {
            // 입력부
            n = Integer.parseInt(reader.readLine());
            pool = new int[n][n];
            for (int i = 0; i < n; i++) {
                infoToken = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    pool[i][j] = Integer.parseInt(infoToken.nextToken());
                }
            }
            infoToken = new StringTokenizer(reader.readLine());
            start[0] = Integer.parseInt(infoToken.nextToken());
            start[1] = Integer.parseInt(infoToken.nextToken());
            infoToken = new StringTokenizer(reader.readLine());
            end[0] = Integer.parseInt(infoToken.nextToken());
            end[1] = Integer.parseInt(infoToken.nextToken());
            boolean[][] visited = new boolean[n][n];
            int[][] distance = new int[n][n];
            for (int[] row : distance) {
                Arrays.fill(row, -1);
            }

            // 구현부
            int time = Integer.MAX_VALUE;
            Queue<int[]> toVisit = new LinkedList<>();
            toVisit.add(new int[] {start[0], start[1], 0});
            distance[start[0]][start[1]] = 0;
            while (!toVisit.isEmpty()) {
                int[] now = toVisit.poll();
                int nowTime = now[2];

                for (int i = 0; i < 4; i++) {
                    int nextRow = now[0] + dRow[i];
                    int nextCol = now[1] + dCol[i];
                    int nextTime = nowTime;

                    if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
                    if (pool[nextRow][nextCol] == 1) continue;

                    if (pool[nextRow][nextCol] == 0) {
                        nextTime++;
                    } if (pool[nextRow][nextCol] == 2) {
                        nextTime += 3 - (nowTime % 3);
                    }

                    if (distance[nextRow][nextCol] != -1 && distance[nextRow][nextCol] <= nextTime) continue;

                    toVisit.add(new int[] {nextRow, nextCol, nextTime});
                    distance[nextRow][nextCol] = nextTime;
                }
            }

            // 출력부
            if (!visited[end[0]][end[1]]) time = -1;
            String answer = String.format("#%d %d\n", test, distance[end[0]][end[1]]);
            writer.write(answer);
            writer.flush();
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        new four4193().solution();
    }
}
