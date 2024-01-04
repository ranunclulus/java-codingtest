package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class two10799 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Stack<Character> stack = new Stack<>();
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            char item = input.charAt(i);

            if (item == '(') {
                stack.push(item);
            } else if (!stack.isEmpty()) {
                stack.pop();
                if (input.charAt(i - 1) == '(') {
                    count += stack.size();
                } else {
                    count += 1;
                }
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new two10799().solution();
    }
}
