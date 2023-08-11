package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//https://www.acmicpc.net/problem/1874
public class two1874 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        Stack<Integer> stack = new Stack<>();
        int maxInput = 0;
        boolean possible = true;
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < size; i++) {
            int num = Integer.parseInt(reader.readLine());
            if (num >= maxInput) {
                for (int j = maxInput + 1; j <= num; j++) {
                    maxInput = j;
                    stack.push(j);
                    answer.append("+\n");
                }
                stack.pop();
                answer.append("-\n");
            } else if (stack.peek() == num) {
                stack.pop();
                answer.append("-\n");
            } else {
                possible = false;
            }
        }
        if (!possible) System.out.println("NO");
        else System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new two1874().solution();
    }
}
