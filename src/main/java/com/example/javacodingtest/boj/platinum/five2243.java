package com.example.javacodingtest.boj.platinum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.24
 @link https://www.acmicpc.net/problem/2243
 @timecomplex
 @performance 56500kb 468ms
 @category
 @note
 */
public class five2243 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static final int MAX = 1000001;
    static int m, type, a, b;
    static int[] tree;

    public void solution() throws IOException {
        m = Integer.parseInt(reader.readLine());
        tree = new int[4 * MAX];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            type = Integer.parseInt(tokenizer.nextToken());
            a = Integer.parseInt(tokenizer.nextToken());

            if (type == 1) { //사탕상자에서 사탕을 꺼내는 경우
                builder.append(query(1, MAX, 1, a)).append('\n');
            }

            else { // 사탕을 넣는 경우
                b = Integer.parseInt(tokenizer.nextToken());
                update(1, MAX, 1, a, b);
            }
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private int query(int start, int end, int node, int target) {
        if (start == end) {
            update(1, MAX, 1, start, -1);
            return start;
        }
        int mid = (start + end) / 2;
        if (target <= tree[node * 2]) return query(start, mid, node * 2, target);
        else return query(mid + 1, end, node * 2 + 1, target - tree[node * 2]);
    }

    private void update(int start, int end, int node, int target, int diff) {
        if (target < start || end < target) return;

        tree[node] += diff;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, node * 2, target, diff);
        update(mid + 1, end, node * 2 + 1, target, diff);
    }

    public static void main(String[] args) throws IOException {
        new five2243().solution();
    }
}
