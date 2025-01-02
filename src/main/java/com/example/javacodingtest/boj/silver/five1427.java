package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.02
 @link https://st-lab.tistory.com/109
 @timecomplex
 @performance 14236kb 100ms
 @category
 @note
 */
public class five1427 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[] counting;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        counting = new int[10];

        while (n != 0) {
            counting[n % 10]++;
            n /= 10;
        }

        for (int i = 9; i >= 0 ; i--) {
            while (counting[i] --> 0) {
                builder.append(i);
            }
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five1427().solution();
    }
}
