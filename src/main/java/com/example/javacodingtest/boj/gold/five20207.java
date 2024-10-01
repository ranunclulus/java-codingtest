package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/20207
 @timecomplex
 @performance 15076kb, 120ms
 @category
 @note
 */
public class five20207{
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, answer;
    static int[] check = new int[366];

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            for (int j = start; j <= end; j++) {
                check[j]++;
            }
        }

        int maxHeight = 0;
        int width = 0;
        answer = 0;
        for (int i = 0; i <= 365; i++) {
            if (check[i] == 0) {
                answer += (width * maxHeight);
                width = 0;
                maxHeight = 0;
                continue;
            }
            width++;
            maxHeight = Math.max(maxHeight, check[i]);
        }
        answer += (width * maxHeight);

        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new five20207().solution();
    }
}
