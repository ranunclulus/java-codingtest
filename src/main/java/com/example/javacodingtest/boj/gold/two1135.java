package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class two1135 {
    BufferedReader reader;
    int n;
    List<Integer>[] tree;
    int[] dp;

    public void solution() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        tree = new ArrayList[n];
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        int root = 0;
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(infoToken.nextToken());
            if (num == -1) root = i;
            else tree[num].add(i);
        }

        System.out.println(dfs(0));
    }

    private int dfs(int current) {
        for (int child : tree[current]) {
            dp[child] = 1 + dfs(child);
        }
        Collections.sort(tree[current], new DepthComparator());
        int res = 0;
        for (int i = 0; i < tree[current].size(); i++) {
            int num = tree[current].get(i);
            dp[num] += i;
            res = Math.max(res, dp[num]);
        }
        return res;
    }

    public class DepthComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return -Integer.compare(dp[o1], dp[o2]);
        }
    }

    public static void main(String[] args) throws IOException {
        new two1135().solution();
    }
}