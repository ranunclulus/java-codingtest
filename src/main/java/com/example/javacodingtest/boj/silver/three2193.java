package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class three2193 {
    int n;
    long[] answer;
    int cnt;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        answer = new long[91];
        answer[1] = 1;
        answer[2] = 1;
        for (int i = 3; i <= n; i++) {
            answer[i] = answer[i - 1] + answer[i - 2];
        }
        System.out.println(answer[n]);
    }


    public static void main(String[] args) throws IOException {
        new three2193().solution();
    }
}
