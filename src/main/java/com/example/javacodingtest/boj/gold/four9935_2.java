package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.27
 @link https://www.acmicpc.net/problem/9935
 @timecomplex
 @performance 63216 kb, 560 ms
 @category
 @note
 */
public class four9935_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static String input;
    static char[] bomb;
    static int bombSize;
    static Stack<Character> stack;

    public void solution() throws IOException {
        input = reader.readLine();
        bomb = reader.readLine().toCharArray();
        bombSize = bomb.length;
        stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));

            if (stack.size() >= bombSize) {
                boolean signal = true;
                while (signal) {
                    for (int j = 0; j < bombSize; j++) {
                        if (stack.get(stack.size() - bombSize + j) != bomb[j]) {
                            signal = false;
                            break;
                        }
                    }

                    if (signal) {
                        for (int j = 0; j < bombSize; j++) {
                            stack.pop();
                        }
                    }
                    if (stack.size() < bombSize) break;
                }
            }
        }

        if (stack.isEmpty()) builder.append("FRULA");
        else {
            while (!stack.isEmpty()) {
                builder.append(stack.pop());
            }
            builder.reverse();
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four9935_2().solution();
    }
}
