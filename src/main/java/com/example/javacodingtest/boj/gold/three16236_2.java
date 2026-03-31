package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2026.03.31
 @link https://www.acmicpc.net/problem/16236
 @timecomplex
 @performance
 @category
 @note
 */
public class three16236_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int N;
    static int turn;
    static int[][] map;
    static int babyRow, babyCol;
    static int eatCount;
    static int size = 2;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    class Node implements Comparable<Node>{
        int distance;
        int row;
        int col;

        Node(int distance, int row, int col) {
            this.distance = distance;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distance == o.distance) {
                if (this.row == o.row) return Integer.compare(this.col, o.col);
                return Integer.compare(this.row, o.row);
            }
            return Integer.compare(this.distance, o.distance);
        }
    }


    public void solution() throws IOException {
        init();

        while(true) {
            Node node = getNode();
            if (node == null) break;
            else {
                map[node.row][node.col] = 0;
                eatCount++;
                if (eatCount == size) {
                    size++;
                    eatCount = 0;
                }
                babyRow = node.row;
                babyCol = node.col;
                turn += node.distance;
            }
        }

        builder.append(turn);
        writer.write(builder.toString());
        writer.flush();
        writer.close();

    }

    public Node getNode() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (map[i][j] == 0 || map[i][j] == 9) continue;
                if (map[i][j] >= size) continue;

                Deque<int[]> toVisit = new ArrayDeque<>();
                boolean[][] visited = new boolean[N][N];

                toVisit.add(new int[] {i, j, 0});
                visited[i][j] = true;

                while(!toVisit.isEmpty()) {
                    int[] now = toVisit.poll();

                    if (now[0] == babyRow && now[1] == babyCol) {
                        pq.add(new Node(now[2], i, j));
                        break;
                    }

                    for(int[] delta : deltas) {
                        int nextRow = now[0] + delta[0];
                        int nextCol = now[1] + delta[1];

                        if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
                        if (visited[nextRow][nextCol]) continue;
                        if (map[nextRow][nextCol] > size) continue;

                        toVisit.add(new int[] {nextRow, nextCol, now[2] + 1});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        return pq.poll();
    }


    public void init() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < N; j++) {
                int value = Integer.parseInt(tokenizer.nextToken());

                if (value < 9) map[i][j] = value;
                else if (value == 9) {
                    babyRow = i;
                    babyCol = j;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three16236_2().solution();
    }

}