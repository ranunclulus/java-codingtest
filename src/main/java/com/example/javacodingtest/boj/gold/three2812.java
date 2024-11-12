package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.12
 @link https://www.acmicpc.net/problem/2812
 @timecomplex
 @performance 33964kb 328ms
 @category
 @note
 */
public class three2812 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k;
    static char[] numbers;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        numbers = reader.readLine().toCharArray();

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (k > 0 && !stack.isEmpty() && stack.getLast() < numbers[i]) {
                stack.removeLast();
                k--;
            }
            stack.addLast(numbers[i]);
        }

        while (stack.size() > k) {
            builder.append(stack.removeFirst());
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three2812().solution();
    }
}
