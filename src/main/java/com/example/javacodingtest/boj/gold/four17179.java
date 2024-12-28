package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.28
 @link https://www.acmicpc.net/problem/17179
 @timecomplex
 @performance 14720kb 136ms
 @category
 @note
 */
public class four17179 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, l, answer;
    static int[] cakes;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        l = Integer.parseInt(tokenizer.nextToken());

        cakes = new int[m + 1];
        for (int i = 0; i < m; i++) {
            cakes[i] = Integer.parseInt(reader.readLine());
        }
        cakes[m] = l;

        for (int i = 0; i < n; i++) {
            answer = 0;
            int q = Integer.parseInt(reader.readLine());

            int low = 0;
            int high = l;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (checked(mid, q)) {
                    low = mid + 1;
                    answer = Math.max(answer, mid);
                }
                else {
                    high = mid - 1;
                }
            }
            builder.append(answer).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private boolean checked(int mid, int q) {
        int previous = 0;
        for (int i = 0; i < m + 1; i++) {
            if (cakes[i] - previous >= mid) {
                q--;
                previous = cakes[i];
            }
        }
        return q < 0 ? true : false;
    }

    public static void main(String[] args) throws IOException {
        new four17179().solution();
    }
}
