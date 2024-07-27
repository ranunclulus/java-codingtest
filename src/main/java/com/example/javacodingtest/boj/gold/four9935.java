package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Stack;

public class four9935 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        String regex = reader.readLine();
        int regexSize = regex.length();

        Stack<Character> stack = new Stack<>();


        for (int i = 0; i < line.length(); i++) {
            stack.push(line.charAt(i));

            if (stack.size() >= regexSize) {
                boolean flag = true;
                for (int j = 0; j < regexSize; j++) {
                    if (stack.get(stack.size() - regexSize + j) != regex.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int j = 0; j < regexSize; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        if (stack.isEmpty()) builder.append("FRULA");
        else {
            for(char ch : stack) {
                builder.append(ch);
            }
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four9935().solution();
    }
}
