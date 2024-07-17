package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

public class one14438 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        long[] arr = new long[n];
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(infoToken.nextToken());
        }
        int m = Integer.parseInt(reader.readLine());

        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int size = (1 << (h + 1));
        long[] tree = new long[size];
        init(arr, tree, 1, 0, n - 1);
        for (int i = 0; i < m; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            int type = Integer.parseInt(infoToken.nextToken());
            if (type == 1) { // 수 변경
                int index = Integer.parseInt(infoToken.nextToken()) - 1;
                long val = Long.parseLong(infoToken.nextToken());
                update(arr, tree, 1, 0, n - 1, index, val);
            }
            else { // 최소값 출력
                int left = Integer.parseInt(infoToken.nextToken()) - 1;
                int right = Integer.parseInt(infoToken.nextToken()) - 1;
                writer.write(query(tree, 1, 0, n - 1, left, right) + "\n");
            }
        }
        writer.flush();
    }

    private long query(long[] tree, int node, int start, int end, int left, int right) {
        if (right < start || end < left) return Long.MAX_VALUE;
        else if (left <= start && end <= right) return tree[node];
        else {
            long leftMin = query(tree, node * 2, start, (start + end) / 2, left, right);
            long rightMin = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            return Math.min(leftMin, rightMin);
        }
    }

    private void update(long[] arr, long[] tree, int node, int start, int end, int index, long val) {
        if (start > index || index > end) {
            return;
        }
        else if (start == end) {
            arr[index] = val;
            tree[node] = val;
        } else {
            update(arr, tree, node * 2, start, (start + end) / 2, index, val);
            update(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end, index, val);
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
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
        new one14438().solution();
    }
}
