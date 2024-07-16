package com.example.javacodingtest.boj.platinum;

import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1777
public class five1777 {
    public void solution() throws IOException {
        // 입력부
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        long[] arr = new long[n];
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(infoToken.nextToken());
        }

        // 트리 만들기
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int size = (1 << (h + 1));
        long[] tree = new long[size];
        init(arr, tree, 1, 0, n - 1);

        // 뒤에서부터 접근
        long[] ans = new long[n];
        for (int i = n - 1; i >= 0 ; i--) {
            // 뒤에 남은 칸의 수로 접근하기
            int idx = query(tree, 1, 0, n - 1, arr[i]);
            ans[idx] = i + 1;
            // 채워진 칸 업데이트
            update(tree, 1, 0, n - 1, idx);
        }

        for(long val : ans) {
            writer.write(val + " ");
        }
        writer.flush();
    }

    private void update(long[] tree, int node, int start, int end, int idx) {
        if (start > idx || idx > end) {
            return;
        }
        tree[node]--;
        if (start != end) {
            update(tree, node * 2, start, (start + end) / 2, idx);
            update(tree, node * 2 + 1, (start + end) / 2 + 1, end, idx);
        }
    }

    private int query(long[] tree, int node, int start, int end, long val) {
        if (start == end) {
            return start;
        } else {
            if (val < tree[node * 2 + 1]) { // 뒤에 위치하는 작은 수가 오른쪽 노드보다 작을 때
                return query(tree, node * 2 + 1, (start + end) / 2 + 1,  end, val);
            } else {
                return query(tree, node * 2, start, (start + end) / 2, val - tree[node * 2 + 1]);
            }
        }
    }

    // 트리 초기화
    private void init(long[] arr, long[] tree, int node, int start, int end) {
        if (start == end) {
            tree[node] = 1;
        } else {
            init(arr, tree, node * 2, start, (start + end) / 2);
            init(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        new five1777().solution();
    }
}
