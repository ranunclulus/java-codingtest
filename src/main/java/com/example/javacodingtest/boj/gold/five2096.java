package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

public class five2096 {
    public int n;
    public int[][] board;
    public int[] maxDp;
    public int[] minDp;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        board = new int[n][3];
        maxDp = new int[3];
        minDp = new int[3];

        for (int i = 0; i < n; i++) {
            int x1 = sc.nextInt();
            int x2 = sc.nextInt();
            int x3 = sc.nextInt();

            if (i == 0) {
                maxDp[0] = minDp[0] = x1;
                maxDp[1] = minDp[1] = x2;
                maxDp[2] = minDp[2] = x3;
            } else {
                int beforeX1 = maxDp[0];
                int beforeX2 = maxDp[2];
                maxDp[0] = Math.max(maxDp[0], maxDp[1]) + x1;
                maxDp[2] = Math.max(maxDp[2], maxDp[1]) + x3;
                maxDp[1] = Math.max(Math.max(beforeX1, maxDp[1]), beforeX2) + x2;

                beforeX1 = minDp[0];
                beforeX2 = minDp[2];
                minDp[0] = Math.min(minDp[0], minDp[1]) + x1;
                minDp[2] = Math.min(minDp[2], minDp[1]) + x3;
                minDp[1] = Math.min(Math.min(beforeX1, minDp[1]), beforeX2) + x2;
            }
        }
        System.out.printf("%d %d",
                Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]),
                Math.min(Math.min(minDp[0], minDp[1]), minDp[2]));

    }


    public static void main(String[] args) throws IOException {
        new five2096().solution();
    }
}
