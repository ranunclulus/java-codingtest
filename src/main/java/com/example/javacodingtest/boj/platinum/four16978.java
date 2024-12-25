package com.example.javacodingtest.boj.platinum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.23
 @link https://www.acmicpc.net/problem/16978
 @timecomplex
 @performance 69876kb 952ms
 @category segment tree
 @note
 */
public class four16978 {
    class OneQuery {
        int count;
        int index;
        int value;

        public OneQuery(int count, int index, int value) {
            this.count = count;
            this.index = index;
            this.value = value;
        }
    }

    class TwoQuery {
        int index;
        int sequence;
        int left;
        int right;
        long sum;

        public TwoQuery(int index, int sequence, int left, int right) {
            this.index = index;
            this.sequence = sequence;
            this.left = left;
            this.right = right;
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, type, oneCount;
    static long[] numbers;
    static long[] tree;
    static int index, value;
    static int mCount, left, right;
    static List<OneQuery> oneQueryList;
    static List<TwoQuery> twoQueryList;


    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        numbers = new long[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }
        m = Integer.parseInt(reader.readLine());
        tree = new long[4 * n];
        init(1, 0, n - 1);


        oneQueryList = new ArrayList<>();
        twoQueryList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            type = Integer.parseInt(tokenizer.nextToken());
            if (type == 1) {
                oneCount++;
                index = Integer.parseInt(tokenizer.nextToken()) - 1;
                value = Integer.parseInt(tokenizer.nextToken());
                oneQueryList.add(new OneQuery(oneCount, index, value));
            } else {
                mCount = Integer.parseInt(tokenizer.nextToken());
                left = Integer.parseInt(tokenizer.nextToken()) - 1;
                right = Integer.parseInt(tokenizer.nextToken()) - 1;
                twoQueryList.add(new TwoQuery(i, mCount, left, right));
            }
        }

        Collections.sort(twoQueryList, new Comparator<TwoQuery>() {
            @Override
            public int compare(TwoQuery o1, TwoQuery o2) {
                return Integer.compare(o1.sequence, o2.sequence);
            }
        });

        oneCount = 0;
        for (TwoQuery twoQuery : twoQueryList) {
            int sequence = twoQuery.sequence;
            if (sequence == oneCount) {
                twoQuery.sum = query(1, twoQuery.left, twoQuery.right, 0, n - 1);
            } else {
                while (sequence != oneCount) {
                    OneQuery oneQuery = oneQueryList.get(oneCount);
                    update(1, oneQuery.index, oneQuery.value, 0, n - 1);
                    oneCount++;
                }
                twoQuery.sum = query(1, twoQuery.left, twoQuery.right, 0, n - 1);
            }
        }

        Collections.sort(twoQueryList, new Comparator<TwoQuery>() {
            @Override
            public int compare(TwoQuery o1, TwoQuery o2) {
                return Integer.compare(o1.index, o2.index);
            }
        });

        for (TwoQuery twoQuery : twoQueryList) {
            builder.append(twoQuery.sum).append('\n');
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

    private void update(int node, int index, int value, int start, int end) {
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

    private long query(int node, int left, int right, int start, int end) {
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[node];
        long leftSum = query(node * 2, left, right, start, (start + end) / 2);
        long rightSum = query(node * 2 + 1, left, right, (start + end) / 2 + 1, end);
        return leftSum + rightSum;
    }

    public static void main(String[] args) throws IOException {
        new four16978().solution();
    }
}
