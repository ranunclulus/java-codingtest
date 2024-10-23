package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.23
 @link https://www.acmicpc.net/problem/20440
 @timecomplex
 @performance
 @category
 @note
 */
public class three20440 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
    }

    public static void main(String[] args) throws IOException {
        new three20440().solution();
    }
}
