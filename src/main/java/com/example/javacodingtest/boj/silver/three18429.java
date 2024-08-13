package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.13
 @link https://www.acmicpc.net/problem/18429
 @timecomplex
 @performance 14220KB 132MS
 @category #permutation
 @note
 */
public class three18429 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;
    static int n;
    static int k;
    static int[] weights;
    static boolean[] visited;
    static int count;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        weights = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        perm(0, 500);
        sb = new StringBuilder ();
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
    }

    private void perm(int depth, int totalWeight) {
        if (depth == n) {
            if (totalWeight >= 500) {
                count++;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && (totalWeight + weights[i] - k) >= 500) {
                visited[i] = true;
                perm(depth + 1, totalWeight + weights[i] - k);
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new three18429().solution();
    }
}
