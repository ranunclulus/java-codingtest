package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

public class three15230 {
    String correct = "abcdefghijklmnopqrstuvwxyz";

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();
        for (int test = 1; test <= testNum; test++) {
            int result = 0;
            String message = sc.next();

            int min = Math.min(message.length(), 26);

            for (int i = 0; i < min; i++) {
                if (message.charAt(i) != correct.charAt(i)) break;
                else result++;
            }

            System.out.printf("#%d %d\n", test, result);
        }
    }

    public static void main(String[] args) throws IOException {
        new three15230().solution();
    }
}
