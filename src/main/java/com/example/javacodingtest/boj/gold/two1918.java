package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class two1918 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> calculation = new Stack<>();

        String input = reader.readLine();

        for (int i = 0; i < input.length(); i++) {
            char now = input.charAt(i);

            switch (now) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!calculation.isEmpty() && priority(calculation.peek()) >= priority(now)) {
                        sb.append(calculation.pop());
                    }
                    calculation.add(now);
                    break;
                case '(':
                    calculation.add(now);
                    break;
                case ')':
                    while (!calculation.isEmpty() && calculation.peek() != '(') {
                        sb.append(calculation.pop());
                    }
                    calculation.pop();
                    break;
                default:
                    sb.append(now);
            }
        }

        while (!calculation.isEmpty()) {
            sb.append(calculation.pop());
        }
        System.out.println(sb.toString());
    }

    private int priority(Character peek) {
        if (peek == '(' || peek == ')')
            return 0;
        else if (peek == '+' || peek == '-')
            return 1;
        else if (peek == '*' || peek == '/')
            return 2;
        else return -1;
    }

    public static void main(String[] args) throws IOException {
        new two1918().solution();
    }
}
