package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.23
 @link https://www.acmicpc.net/problem/14427
 @timecomplex
 @performance 52900kb 532ms
 @category
 @note
 */
public class three14427 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, type;
    static int[] numbers, tree;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());

        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tree = new int[n * 4];
        init(1, 0, n - 1);

        m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            type = Integer.parseInt(tokenizer.nextToken());
            if (type == 1) {
                int index = Integer.parseInt(tokenizer.nextToken()) - 1;
                int value = Integer.parseInt(tokenizer.nextToken());
                update(1, index, value, 0, n - 1);
            } else {
                builder.append(tree[1] + 1).append('\n');
            }
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private int minIndex(int i, int j) {
        if (numbers[i] == numbers[j]) {
            return Math.min(i, j);
        } return numbers[i] < numbers[j] ? i : j;
    }

    private int init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = start;
        } else {
            return tree[node] = minIndex(
                    init(2 * node, start, (start + end) / 2),
                    init(2 * node + 1, (start + end) / 2 + 1, end));
        }
    }

    private int update(int node, int index, int value, int start, int end) {
        if (index < start || index > end) return tree[node];
        if (start == end) {
            numbers[index] = value;
            return tree[node] = index;
        } else {
            return tree[node] = minIndex(
                    update(node * 2, index, value, start, (start + end) / 2),
                    update(node * 2 + 1, index, value, (start + end) / 2 + 1, end));
        }
    }

    private int query(int index, int start, int end) {
        return 0;
    }

    public static void main(String[] args) throws IOException {
        new three14427().solution();
    }
}
