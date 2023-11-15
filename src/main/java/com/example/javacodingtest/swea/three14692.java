package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

public class three14692 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 1; test <= testNum; test++) {
            int length = sc.nextInt();
            String result = (length % 2 == 0) ? "Alice" : "Bob";
            System.out.printf("#%d %s\n", test, result);
        }
    }

    public static void main(String[] args) throws IOException {
        new three14692().solution();
    }
}
