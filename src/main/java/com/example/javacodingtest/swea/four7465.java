package com.example.javacodingtest.swea;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.28
 @link
 @timecomplex
 @performance 25920 KB, 232 MS
 @category
 @note
 */
public class four7465 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int m;
    static int[] parent;
    static int operation;
    static int first;
    static int second;
    static int count;
    static Set<Integer> groups;

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            parent = new int[n + 1];
            count = 1;

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            sb.append("#").append(test).append(" ");
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                first = Integer.parseInt(st.nextToken());
                second = Integer.parseInt(st.nextToken());
                union(first, second);
            }
            groups = new HashSet<>();
            for (int i = 1; i < n + 1; i++) {
                groups.add(parent[i]);
            }
            sb.append(groups.size());
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private int find(int target) {
        if (parent[target] == target) {
            return target;
        }
        return find(parent[target]);
    }

    private void union(int first, int second) {
        int firstParent = find(first);
        int secondParent = find(second);

        if (firstParent > secondParent) {
            int change = parent[firstParent];
            for (int i = 1; i <= n; i++) {
                if (parent[i] == change) {
                    parent[i] = secondParent;
                }
            }
        } else {
            int change = parent[secondParent];
            for (int i = 1; i <= n; i++) {
                if (parent[i] == change) {
                    parent[i] = firstParent;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four7465().solution();
    }
}
