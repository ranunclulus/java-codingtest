package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class one11505 {
    static BufferedReader reader;
    static BufferedWriter writer;
    static StringTokenizer infoToken;
    static StringBuilder builder;
    public void solution() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        builder = new StringBuilder();
        infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());
        int k = Integer.parseInt(infoToken.nextToken());

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int size = (1 << (h + 1));
        long[] tree = new long[size];
        init(arr, tree, 1, 0, n - 1);

        for (int i = 0; i < m + k; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            int type = Integer.parseInt(infoToken.nextToken());
            if (type == 1) { // 바꾸기
                int index = Integer.parseInt(infoToken.nextToken()) - 1;
                long val = Long.parseLong(infoToken.nextToken());
                update(arr, tree, 1, 0, n - 1, index, val);
            } else { // 구간 곱 구하기
                int left = Integer.parseInt(infoToken.nextToken()) - 1;
                int right = Integer.parseInt(infoToken.nextToken()) - 1;
                builder.append(query(tree, 1, 0, n - 1, left, right) % 1000000007);
                builder.append('\n');
            }
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private long query(long[] tree, int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 1;
        else if (left <= start && end <= right) return tree[node];
        else {
            long leftVal = query(tree, node * 2, start, (start + end) / 2, left, right);
            long rightVal = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            return (leftVal * rightVal) % 1000000007;
        }
    }

    private void update(long[] arr, long[] tree, int node, int start, int end, int index, long val) {
        if (index < start || end < index) return;
        else if (start == end) {
            arr[index] = val;
            tree[node] = val;
        } else {
            update(arr, tree, node * 2, start, (start + end) / 2, index, val);
            update(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end, index, val);
            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1000000007;
        }
    }

    private void init(long[] arr, long[] tree, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            init(arr, tree, node * 2, start, (start + end) / 2);
            init(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1000000007;
        }
    }

    public static void main(String[] args) throws IOException {
        new one11505().solution();
    }
}
