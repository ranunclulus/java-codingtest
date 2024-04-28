package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class three2460 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int max = 0;
        int current = 0;

        for (int i = 0; i < 10; i++) {
            StringTokenizer infoToken = new StringTokenizer(sc.nextLine());
            int output = Integer.parseInt(infoToken.nextToken());
            int input = Integer.parseInt(infoToken.nextToken());
            current = current + input - output;
            max = Math.max(max, current);
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        new three2460().solution();
    }
}
