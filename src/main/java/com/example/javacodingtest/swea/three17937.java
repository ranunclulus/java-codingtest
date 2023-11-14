package com.example.javacodingtest.swea;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class three17937 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 1; test <= testNum; test++) {
            BigInteger result = BigInteger.ONE;

            BigInteger a = sc.nextBigInteger();
            BigInteger b = sc.nextBigInteger();

            if (a.equals(b)) result = a;

            System.out.printf("#%d %d\n", test, result);
        }
    }

    public static void main(String[] args) throws IOException {
        new three17937().solution();
    }
}
