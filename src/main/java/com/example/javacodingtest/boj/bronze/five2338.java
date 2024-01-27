package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class five2338 {
    public void solution() throws IOException {
        Scanner in = new Scanner(System.in);
        BigInteger first = in.nextBigInteger();
        BigInteger second = in.nextBigInteger();

        System.out.println(first.add(second));
        System.out.println(first.subtract(second));
        System.out.print(first.multiply(second));
    }

    public static void main(String[] args) throws IOException {
        new five2338().solution();
    }
}
