#코드를 입력하세요.
package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.14
 @link https://www.acmicpc.net/problem/1976
 @timecomplex
 @performance 17488kb 168ms
 @category
 @note
 */
public class four1976 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static int[] parent;
    static boolean success;
    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        m = Integer.parseInt(reader.readLine());
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                int connection = Integer.parseInt(tokenizer.nextToken());
                if (connection == 1) union(i, j);
            }
        }

        success = true;
        tokenizer = new StringTokenizer(reader.readLine());
        int start = find(Integer.parseInt(tokenizer.nextToken()) - 1);
        for (int i = 1; i < m; i++) {
            int now = Integer.parseInt(tokenizer.nextToken()) - 1;
            if (start != find(now)) {
               success = false;
               break;
            }
        }

        builder.append(success ? "YES" : "NO");

        writer.write(builder.toString());
        writer.flush();

    }

    private void union(int one, int two) {
        int oneParent = find(one);
        int twoParent = find(two);

        if (oneParent <= twoParent) {
            parent[twoParent] = oneParent;
        } else {
            parent[oneParent] = twoParent;
        }

    }

    private int find(int target) {
        if (parent[target] == target) return target;
        return parent[target] = find(parent[target]);
    }

    public static void main(String[] args) throws IOException {
        new four1976().solution();
    }
}
