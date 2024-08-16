package com.example.javacodingtest.boj.bronze;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since
 @link
 @timecomplex https://www.acmicpc.net/problem/10870
 @performance
 @category #recursion
 @note
 */
public class two10870 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] fibonacci = new int[21];

    public void solution() throws IOException {
        fibonacci[1] = 1;
        fibonacci[2] = 1;
        for (int i = 3; i <= 20; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        n = Integer.parseInt(br.readLine());
        sb.append(fibonacci[n]);
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new two10870().solution();
    }
}
