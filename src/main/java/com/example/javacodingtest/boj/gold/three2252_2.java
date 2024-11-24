package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.20
 @link https://www.acmicpc.net/problem/2252
 @timecomplex
 @performance 45156kb 3492ms
 @category
 @note
 */
public class three2252_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, start, end;
    static List<List<Integer>> edges;
    static int[] degrees;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        edges = new LinkedList<>();
        degrees = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            edges.add(new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            start = Integer.parseInt(tokenizer.nextToken());
            end = Integer.parseInt(tokenizer.nextToken());

            edges.get(start).add(end);
            degrees[end]++;
        }

        Deque<Integer> toVisit = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (degrees[i] == 0) {
                toVisit.add(i);
                degrees[i] = -1;
            }
        }

        while (!toVisit.isEmpty()) {
            int now = toVisit.poll();
            builder.append(now).append(" ");

            for(int next : edges.get(now)) {
                degrees[next]--;
            }

            for (int i = 1; i <= n; i++) {
                if (degrees[i] == 0) {
                    toVisit.add(i);
                    degrees[i] = -1;
                }
            }
        }

        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new three2252_2().solution();
    }
}
