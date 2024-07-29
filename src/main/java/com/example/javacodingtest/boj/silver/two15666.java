package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.*;

public class two15666 {
    private StringBuilder builder;
    private int n;
    private int m;
    private List<Integer> nums;
    private int[] result;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        builder = new StringBuilder();
        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());

        nums = new ArrayList<>();
        infoToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(infoToken.nextToken());
            if (!nums.contains(val)) nums.add(val);
        }
        Collections.sort(nums);

        result = new int[m];
        dfs(0);
        writer.write(builder.toString());
        writer.flush();
    }

    private void dfs(int depth) {
        if (depth == m) {
            for (int val : result) {
                builder.append(val).append(" ");
            }
            builder.append('\n');
            return;
        }

        if (depth == 0) {
            for (int val : nums) {
                result[depth] = val;
                dfs(depth + 1);
            }
        } else {
            for (int val : nums) {
                if (result[depth - 1] > val) continue;
                result[depth] = val;
                dfs(depth + 1);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new two15666().solution();
    }
}
