package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.09
 @link https://www.acmicpc.net/problem/1240
 @timecomplex
 @performance 54136kb 352ms
 @category
 @note
 */
public class five1240 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static List<List<int[]>> adjList;


    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        adjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            adjList.get(start).add(new int[] {end, cost});
            adjList.get(end).add(new int[] {start, cost});
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            Deque<int[]> toVisit = new ArrayDeque<>();
            boolean[] visited = new boolean[n + 1];
            int result = 0;

            toVisit.add(new int[] {start, 0});
            visited[start] = true;

            while (!toVisit.isEmpty()) {
                int[] now = toVisit.poll();
                boolean find = false;
                for(int[] next : adjList.get(now[0])) {
                    if (visited[next[0]]) continue;
                    if (next[0] == end) {
                        result = now[1] + next[1];
                        builder.append(result + "\n");
                        find = true;
                        break;
                    }
                    toVisit.add(new int[] {next[0], now[1] + next[1]});
                    visited[next[0]] = true;
                }
                if (find){
                    break;
                }
            }
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five1240().solution();
    }
}
