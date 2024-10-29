package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.29
 @link https://www.acmicpc.net/problem/22945
 @timecomplex
 @performance 24192kb 260ms
 @category
 @note
 */
public class four22945 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, start, end, tempValue, maxValue;
    static int[] abilities;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());
        abilities = new int[n];
        for (int i = 0; i < n; i++) {
            abilities[i] = Integer.parseInt(tokenizer.nextToken());
        }

        start = 0;
        end = n - 1;

        while (start < end) {
            tempValue = (end - start - 1) * Math.min(abilities[start], abilities[end]);
            maxValue = Math.max(maxValue, tempValue);

            if (abilities[start] < abilities[end]) {
                start++;
            } else {
                end--;
            }
        }
        builder.append(maxValue);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four22945().solution();
    }
}
