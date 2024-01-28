package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class four3046 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int r1 = sc.nextInt();
        int s = sc.nextInt();
        System.out.println((s * 2) - r1);
    }

    public static void main(String[] args) throws IOException {
        new four3046().solution();
    }
}
