package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14428
public class one14428 {

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
            if (type == 1) {
                int index = Integer.parseInt(infoToken.nextToken()) - 1;
                long val = Integer.parseInt(infoToken.nextToken());
                update(arr, tree, 1, 0, n - 1, index, val);

            } else {
                int left = Integer.parseInt(infoToken.nextToken()) - 1;
                int right = Integer.parseInt(infoToken.nextToken()) - 1;
                long minValue = query(arr, tree, 1, 0, n - 1, left, right) + 1;
                writer.write(minValue + "\n");
            }
        }
        writer.flush();
    }


    private long query(long[] arr, long[] tree, int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return -1;
        } else if (left <= start && end <= right) {
            return tree[node];
        } else {
            long leftMin = query(arr, tree, node * 2, start, (start + end) / 2, left, right);
            long rightMin = query(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            if (leftMin == -1) return rightMin;
            else if (rightMin == -1) return  leftMin;
            else {
                if (arr[(int)leftMin] <= arr[(int)rightMin]) return leftMin;
                else return rightMin;
            }
        }
    }

    private void update(long[] arr, long[] tree, int node, int start, int end, int index, long val) {
        if (index < start || end < index) {
            return;
        } else if (start == end) {
            arr[index] = val;
            tree[node] = index;
            return;
        } else {
            update(arr, tree, node * 2, start, (start + end) / 2, index, val);
            update(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end, index, val);
            if (arr[(int)tree[node * 2]] <= arr[(int)tree[node * 2 + 1]]) {
                tree[node] = tree[node * 2];
            } else tree[node] = tree[node * 2 + 1];
        }
    }

    private void init(long[] arr, long[] tree, int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
        } else {
            init(arr, tree, node * 2, start, (start + end) / 2);
            init(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end);
            if (arr[(int)tree[node * 2]] <= arr[(int)tree[node * 2 + 1]]) {
                tree[node] = tree[node * 2];
            } else tree[node] = tree[node * 2 + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        new one14428().solution();
    }
}
