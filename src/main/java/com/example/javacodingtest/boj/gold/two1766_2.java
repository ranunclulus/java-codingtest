package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.20
 @link https://www.acmicpc.net/problem/1766
 @timecomplex
 @performance 45632kb 3544ms
 @category
 @note
 */
public class two1766_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, start, end;
    static int[] degrees;
    static List<List<Integer>> edges;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        degrees = new int[n + 1];
        edges = new LinkedList<>();

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

        topologySort();

        writer.write(builder.toString());
        writer.flush();
    }

    private void topologySort() {
        PriorityQueue<Integer> toVisit = new PriorityQueue<>();

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
    }

    public static void main(String[] args) throws IOException {
        new two1766_2().solution();
    }
}
