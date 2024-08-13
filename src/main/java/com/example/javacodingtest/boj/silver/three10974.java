package com.example.javacodingtest.boj.silver;

import java.io.*;

public class three10974 {
    private static int[] ans;
    private static boolean[] visited;
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        ans = new int[n];
        visited = new boolean[n];
        perm(0, n);
    }

    private void perm(int depth, int n) throws IOException {
        if (depth == n) {
            StringBuilder builder = new StringBuilder();
            for (int val : ans) {
                builder.append(val).append(" ");
            }
            builder.append('\n');
            writer.write(builder.toString());
            writer.flush();
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans[depth] = i + 1;
                visited[i] = true;
                perm(depth + 1, n);
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new three10974().solution();
    }
}
