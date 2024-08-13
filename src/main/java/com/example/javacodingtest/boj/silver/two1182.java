package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

public class two1182 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer token;
    static int[] nums;
    static boolean[] visited;
    static int n;
    static int s;
    static int count;

    public void solution() throws IOException {
        token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        s = Integer.parseInt(token.nextToken());
        nums = new int[n];
        visited = new boolean[n];
        token = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(token.nextToken());
        }

        perm(0, 0);

        // 합이 0인 경우 공집합이 하나 포함돼서 카운팅
        if(s == 0) count -= 1;
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
    }

    private void perm(int depth, int sum) {

        if (depth == n) {
            if(sum == s) count++;
            return;
        }

        perm(depth + 1, sum + nums[depth]);
        perm(depth + 1, sum);
    }

    public static void main(String[] args) throws IOException {
        new two1182().solution();
    }
}
