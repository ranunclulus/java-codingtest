package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

public class three17642 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        for (int test = 1; test <= testCase; test++) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long result = maxOper(a, b);
            System.out.printf("#%d %d\n", test, result);
        }
    }

    public long maxOper(long a, long b) {
        if (a == b) {
            return 0; // 이미 같은경우
        } else if (a > b) {
            return -1; // A는 더하기 연산만 가능하므로..
        }

        long diff = Math.abs(a - b);

        if (diff == 1) {
            return -1; // 차이가 1인경우 불가능.
        } else if (diff % 2 == 1) {
            return (long) ((diff - 1) / 2);
        } else {
            return (long) (diff / 2);
        }
    }

    public static void main(String[] args) throws IOException {
        new three17642().solution();
    }
}
