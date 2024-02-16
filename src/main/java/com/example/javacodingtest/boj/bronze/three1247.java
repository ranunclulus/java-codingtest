package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class three1247 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);

        for (int test = 0; test < 3; test++) {
            int n = Integer.parseInt(sc.nextLine());
            BigInteger sum = new BigInteger("0");
            for (int i = 0; i < n; i++) {
                BigInteger value = new BigInteger(sc.nextLine());
                sum = sum.add(value);
            }
            if (sum.equals(BigInteger.ZERO)) {
                System.out.println(sum);
            } else if (sum.compareTo(BigInteger.ZERO) < 0) {
                System.out.println('-');
            } else {
                System.out.println('+');
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three1247().solution();
    }
}
