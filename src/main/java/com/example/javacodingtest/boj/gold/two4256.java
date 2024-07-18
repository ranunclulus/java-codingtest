package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class two4256 {
    static int n;
    static int[] preorder;
    static int[] inorder;
    static BufferedReader reader;
    static StringTokenizer infoToken;
    static StringBuilder builder;
    public void solution() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 0; test < testNum; test++) {
            n = Integer.parseInt(reader.readLine());
            // 전위 순회
            preorder = new int[n];
            infoToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(infoToken.nextToken());
            }
            // 중위 순회
            inorder = new int[n];
            infoToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(infoToken.nextToken());
            }

            findPostOrder(0, 0, n - 1);
            builder.append('\n');
        }
        System.out.println(builder.toString());
    }

    private void findPostOrder(int root, int start, int end) {
        if (root >= n) { // 다 찾음
            return;
        }
        else {
            int rootValue = preorder[root];

            for (int i = start; i <= end ; i++) {
                if (rootValue == inorder[i]) {
                    findPostOrder(root + 1, start, i - 1);
                    findPostOrder(root + i + 1 - start, i + 1, end);
                    builder.append(rootValue + " ");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two4256().solution();
    }
}
