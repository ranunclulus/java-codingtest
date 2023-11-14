package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

public class three15758 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 1; test <= testNum; test++) {
            String a = sc.next();
            String b = sc.next();

            int aLength = a.length();
            int bLength = b.length();

            int gcf = gcf(aLength, bLength);
            boolean isPossible = false;

            String aa = a;
            for (int i = 0; i < gcf / aLength - 1; i++) {
                aa += a;
            }
            String bb = b;
            for (int i = 0; i < gcf / bLength - 1; i++) {
                bb += b;
            }

            if (aa.equals(bb)) isPossible = true;
            System.out.println("#" + test + " " + (isPossible ? "yes" : "no"));
        }
    }

    private int gcf(int aLength, int bLength) {
        int max = 0;
        for (int i = 1; i <= aLength && i <= bLength; i++) {
            if (aLength % i == 0 && bLength % i == 0) max = i;
        }
        return (aLength * bLength) / max;
    }

    public static void main(String[] args) throws IOException {
        new three15758().solution();
    }
}
