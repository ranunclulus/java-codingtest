package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/4485
 @timecomplex
 @performance 19356 KB, 188 MS
 @category
 @note
 */
public class four4485 {
    class Node implements Comparable<Node>{
        int row;
        int col;
        int cost;

        public Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[][] distance;
    static int[][] deltas = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int answer;
    public void solution() throws IOException {
        int testNum = 0;
        while(true) {
            testNum++;
            n = Integer.parseInt(reader.readLine());
            if (n == 0) break;

            map = new int[n][n];
            distance = new int[n][n];
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
            distance[0][0] = map[0][0];

            PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
            priorityQueue.offer(new Node(0, 0, distance[0][0]));
            visited[0][0] = true;

            while(!priorityQueue.isEmpty()) {
                Node node = priorityQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextRow = node.row + deltas[i][1];
                    int nextCol = node.col + deltas[i][0];

                    if (nextCol < 0 || nextCol >= n || nextRow < 0 || nextRow >= n) continue;
                    if (visited[nextRow][nextCol]) continue;

                    visited[nextRow][nextCol] = true;
                    distance[nextRow][nextCol] = node.cost + map[nextRow][nextCol];
                    priorityQueue.add(new Node(nextRow, nextCol, distance[nextRow][nextCol]));
                }
            }
            builder.append("Problem ").append(testNum).append(": ").append(distance[n - 1][n - 1]).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four4485().solution();
    }
}
