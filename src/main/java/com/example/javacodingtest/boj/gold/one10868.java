package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10868
public class one10868 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(reader.readLine());
        }

        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int size = (1 << (h + 1));
        long[] tree = new long[size];

        init(arr, tree, 1, 0, n - 1);

        for (int i = 0; i < m; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            int left = Integer.parseInt(infoToken.nextToken()) - 1;
            int right = Integer.parseInt(infoToken.nextToken()) - 1;
            System.out.println(query(tree, 1, 0, n - 1, left, right));
        }
    }

    private long query(long[] tree, int node, int start, int end, int left, int right) {
        if (right < start || left > end) {
            return Long.MAX_VALUE;
        } else if (left <= start && end <= right) {
            return tree[node];
        } else {
            long leftMin = query(tree, node * 2, start, (start + end) / 2, left, right);
            long rightMin = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            return Math.min(leftMin, rightMin);
        }
    }

    private void init(long[] arr, long[] tree, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            init(arr, tree, node * 2, start, (start + end) / 2);
            init(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        new one10868().solution();
    }
}
