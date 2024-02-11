package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class two1297 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        double d = sc.nextDouble();
        double h = sc.nextDouble();
        double w = sc.nextDouble();

        double pow = Math.pow(d, 2) / (Math.pow(h, 2) + Math.pow(w, 2));
        double sqrt = Math.sqrt(pow);

        System.out.printf("%d %d", (int) (sqrt * h), (int) (sqrt * w));

    }

    public static void main(String[] args) throws IOException {
        new two1297().solution();
    }
}
