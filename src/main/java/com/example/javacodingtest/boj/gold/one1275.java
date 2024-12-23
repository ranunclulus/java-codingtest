package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.23
 @link https://www.acmicpc.net/problem/1275
 @timecomplex
 @performance 77252kb 804ms
 @category segment tree
 @note
 */
public class one1275 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, q, x, y, a;
    static long b;
    static long[] numbers, tree;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        q = Integer.parseInt(tokenizer.nextToken());

        numbers = new long[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tree = new long[4 * n];
        init(1, 0, n - 1);
        for (int i = 0; i < q; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            x = Integer.parseInt(tokenizer.nextToken()) - 1;
            y = Integer.parseInt(tokenizer.nextToken()) - 1;
            int left = Math.min(x, y);
            int right = Math.max(x, y);
            builder.append(query(1, 0, n - 1, left, right)).append('\n');
            a = Integer.parseInt(tokenizer.nextToken()) - 1;
            b = Long.parseLong(tokenizer.nextToken());
            update(1, a, b, 0, n - 1);
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = numbers[start];
        } else {
            init(node * 2, start, (start + end) / 2);
            init(node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private long query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        long leftSum = query(node * 2, start, (start + end) / 2, left, right);
        long rightSum = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return (leftSum + rightSum);
    }

    private void update(int node, int index, long value, int start, int end) {
        if (index < start || index > end) return;
        if (start == end) {
            numbers[index] = value;
            tree[node] = value;
            return;
        }
        update(node * 2, index, value, start, (start + end) / 2);
        update(node * 2 + 1, index, value, (start + end) / 2 + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static void main(String[] args) throws IOException {
        new one1275().solution();
    }

}
