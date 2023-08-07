package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2439
public class four2439 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String space = " ";
        String star = "*";
        int n = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= n ; i++) {
            System.out.printf("%s%s\n", space.repeat(n - i), star.repeat(i));
        }
    }

    public static void main(String[] args) throws IOException {
        new four2439().solution();
    }
}
