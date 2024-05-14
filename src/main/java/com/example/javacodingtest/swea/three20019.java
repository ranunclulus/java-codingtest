package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class three20019 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            String input = reader.readLine();
            Queue<Character> queue = new LinkedList<>();
            Stack<Character> stack = new Stack<>();

            boolean check = true;

            for (int i = 0; i < input.length(); i++) {
                queue.add(input.charAt(i));
                stack.push(input.charAt(i));
            }

            for (int i = 0; i < input.length(); i++) {
                if (queue.poll() != stack.pop()) {
                    check = false;
                    break;
                }
            }

            for (int i = 0; i < input.length() / 2; i++) {
                if (!check) break;
                queue.add(input.charAt(i));
                stack.push(input.charAt(i));
            }

            for (int i = 0; i < input.length() / 2; i++) {
                if (!check) break;
                if (queue.poll() != stack.pop()) {
                    check = false;
                    break;
                }
            }

            for (int i = input.length() / 2 + 1; i < input.length(); i++) {
                if (!check) break;
                queue.add(input.charAt(i));
                stack.push(input.charAt(i));
            }

            for (int i = input.length() / 2 + 1; i < input.length(); i++) {
                if (!check) break;
                if (queue.poll() != stack.pop()) {
                    check = false;
                    break;
                }
            }
            if (!check) System.out.printf("#%d %s\n", test, "NO");
            else System.out.printf("#%d %s\n", test, "YES");
        }
    }
}
