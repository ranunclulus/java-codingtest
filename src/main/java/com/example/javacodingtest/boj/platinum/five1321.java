package com.example.javacodingtest.boj.platinum;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.16
 @link https://www.acmicpc.net/problem/1321
 @timecomplex
 @performance 68232kb, 444ms
 @category
 @note
 */
public class five1321 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static long[] segmentTree;
    static long[] soldiers;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        soldiers = new long[n + 1];
        segmentTree = new long[n * 4];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            soldiers[i] = Long.parseLong(tokenizer.nextToken());
        }

        makeTree(1, 1, n);

        m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int operation = Integer.parseInt(tokenizer.nextToken());
            int index = Integer.parseInt(tokenizer.nextToken());

            if (operation == 1) {
                int value = Integer.parseInt(tokenizer.nextToken());
                updateTree(1, 1, n, index, value);
            } else {
                getTree(1, 1, n, index);
            }
        }

        writer.write(builder.toString());
        writer.flush();
    }

    public static void makeTree(int node, int start, int end) {
        if (start == end) {
            segmentTree[node] = soldiers[start];
            return;
        }

        makeTree(node * 2, start, (start + end) / 2);
        makeTree(node * 2 + 1, (start + end) / 2 + 1, end);

        segmentTree[node] = segmentTree[node * 2] + segmentTree[node * 2 + 1];
    }

    public static void updateTree(int node, int start, int end, int index, int value) {
        if (index < start || end < index) return;
        segmentTree[node] += value;

        if (start != end) {
            updateTree(node * 2, start, (start + end) / 2, index, value);
            updateTree(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
        }
    }

    public static void getTree(int node, int start, int end, long index) {
        if (start == end) {
            builder.append(start).append('\n');
            return;
        }

        long left = segmentTree[node * 2];

        if (left >= index) {
            getTree(node * 2, start, (start + end) / 2, index);
        } else {
            getTree(node * 2 + 1, (start + end) / 2 + 1, end, index - left);
        }
    }

    public static void main(String[] args) throws IOException {
        new five1321().solution();
    }
}
