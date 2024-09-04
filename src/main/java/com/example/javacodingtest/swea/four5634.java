package com.example.javacodingtest.swea;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.04
 @link
 @timecomplex
 @performance 94068 kb 4107 ms
 @category bfs
 @note
 */
public class four5634 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum;
    static int n, m;
    static int[][] edges;
    static int count;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            n = Integer.parseInt(reader.readLine());
            m = Integer.parseInt(reader.readLine());
            edges = new int[n][n];
            for (int i = 0; i < m; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                edges[Integer.parseInt(tokenizer.nextToken()) - 1][Integer.parseInt(tokenizer.nextToken()) - 1]++;
            }
            count = 0;
            for (int i = 0; i < n; i++) {
                int smaller = findSmall(i);
                int taller = findTall(i);
                if (smaller + taller == n - 1) count++;
            }
            builder.append("#").append(test).append(" ").append(count).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private int findTall(int i) {
        int taller = 0;
        Deque<Integer> toVisit = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        toVisit.add(i);
        visited[i] = true;
        while(!toVisit.isEmpty()) {
            int now = toVisit.poll();
            taller++;
            for (int j = 0; j < n; j++) {
                if (edges[now][j] == 1 && !visited[j]) {
                    toVisit.add(j);
                    visited[j] = true;
                }
            }
        }
        return taller - 1;
    }

    private int findSmall(int i) {
        int smaller = 0;
        Deque<Integer> toVisit = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        toVisit.add(i);
        visited[i] = true;
        while(!toVisit.isEmpty()) {
            int now = toVisit.poll();
            smaller++;
            for (int j = 0; j < n; j++) {
                if (edges[j][now] == 1 && !visited[j]) {
                    toVisit.add(j);
                    visited[j] = true;
                }
            }
        }
        return smaller - 1;
    }

    public static void main(String[] args) throws IOException {
        new four5634().solution();
    }
}


/*
1
6
6
1 5
3 4
5 4
4 2
4 6
5 2
 */
