package com.example.javacodingtest.boj.gold;

import java.io.*;

// https://www.acmicpc.net/problem/2042
public class one2042 {
    static private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]); // 변경이 일어나는 횟수
        int k = Integer.parseInt(line[2]); // 구간합을 구하는 횟수
        m += k;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(reader.readLine());
        }
        // 트리 만들기
        int height = (int) Math.ceil(Math.log(n) / Math.log(2));
        int size = (1 << (height + 1));
        long[] tree = new long[size];
        init(arr, tree, 1, 0, n - 1);

        for (int i = 0; i < m; i++) {
            line = reader.readLine().split(" ");
            int type = Integer.parseInt(line[0]);
            if (type == 1) { // 수 변경
                int index = Integer.parseInt(line[1]) - 1;
                long val = Long.parseLong(line[2]);
                update(arr, tree, 1, index, val, 0, n - 1);

            } else { // 구간합
                int left = Integer.parseInt(line[1]) - 1;
                int right = Integer.parseInt(line[2]) - 1;
                writer.write(query(tree, 1, 0, n - 1, left, right) + "\n");
            }
        }
        writer.flush();
    }

    private void update(long[] arr, long[] tree, int node, int index, long val, int start, int end) {
        if (index < start || index > end) return;
        if (start == end) {
            arr[index] = val;
            tree[node] = val;
            return;
        } else {
            update(arr, tree, node * 2, index, val, start, (start + end) / 2);
            update(arr, tree, node * 2 + 1, index, val, (start + end) / 2 + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private long query(long[] tree, int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0;
        } else if (left <= start && end <= right) {
            return tree[node];
        } else {
            long leftSum = query(tree, node * 2, start, (start + end) / 2, left, right);
            long rightSum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            return leftSum + rightSum;
        }
    }

    private void init(long[] arr, long[] tree, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            init(arr, tree, 2 * node, start, (start + end) / 2); // 오른쪽
            init(arr, tree, 2 * node + 1, (start + end) / 2 + 1, end); // 왼쪽
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        new one2042().solution();
    }
}
