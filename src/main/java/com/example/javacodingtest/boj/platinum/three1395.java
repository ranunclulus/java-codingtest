package com.example.javacodingtest.boj.platinum;

import java.util.*;
import java.io.*;

/*
 @author ranuinclulus
 @since 2025.02.26
 @link https://www.acmicpc.net/problem/1395
 @timecomplex 47312KB 532MS
 @performance
 @category
 @note
 */
public class three1395 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int M, N;
    static int[] tree, lazy;
    static int operation, a, b;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        tree = new int[4 * N];
        lazy = new int[4 * N];

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            operation = Integer.parseInt(tokenizer.nextToken());
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());

            if (operation == 0) {
                update(1, N, 1, a, b);
            } else {
                builder.append(calculate(1, N, 1, a, b)).append("\n");
            }
        }

        writer.write(builder.toString());
        writer.flush();
    }

    public void propagate(int start, int end, int node) {
        if (lazy[node] % 2 == 1) {
            if (start != end) { // 리프 노드가 아니라면
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            tree[node] = (end - start + 1) - tree[node];
            lazy[node] = 0;
        }
    }

    public void update(int start, int end, int node, int left, int right) {
        propagate(start, end, node);
        if (end < left || right < start) return;
        if (left <= start && end <= right) {
            lazy[node] = 1;
            propagate(start, end, node);
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, left, right);
        update(mid + 1, end, node * 2 + 1, left, right);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public int calculate(int start, int end, int node, int left, int right) {
        propagate(start, end, node);
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return calculate(start, mid, node * 2, left, right) + calculate(mid + 1, end, node * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        new three1395().solution();
    }
}
