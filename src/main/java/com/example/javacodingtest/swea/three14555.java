package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class three14555 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());

        for (int test = 1; test <= testNum; test++) {
            int ballNum = 0;
            String input = reader.readLine();

            for (int i = 0; i < input.length() - 1; i++) {
                if (input.charAt(i) == '(' && input.charAt(i + 1) == '|') {
                    ballNum++;
                }
                if (input.charAt(i) == '|' && input.charAt(i + 1) == ')') {
                    ballNum++;
                }
                if (input.charAt(i) == '(' && input.charAt(i + 1) == ')') {
                    ballNum++;
                }

            }
            System.out.printf("#%d %d\n", test, ballNum);
        }
    }
}
