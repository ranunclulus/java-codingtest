package com.example.javacodingtest.boj.bronze;

import java.util.HashSet;
import java.util.Scanner;

public class two3052 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashSet<Integer> h = new HashSet<Integer>();

        for (int i = 0; i < 10; i++) {
            h.add(in.nextInt() % 42);
        }

        in.close();
        System.out.print(h.size());
    }
}
