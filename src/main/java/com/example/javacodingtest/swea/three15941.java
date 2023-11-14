package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

public class three15941 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 1; test <= testNum; test++) {
            int length = sc.nextInt();
            System.out.printf("#%d %d\n", test, length * length);
        }

    }

    public static void main(String[] args) throws IOException {
        new three15941().solution();
    }
}
