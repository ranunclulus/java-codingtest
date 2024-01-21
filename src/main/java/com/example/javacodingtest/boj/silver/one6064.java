package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class one6064 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        for (int test = 0; test < testCase; test++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            boolean find = false;
            for (int i = 0; i < n + 1; i++) {
                int mTemp = (m * i) + x;
                if (mTemp > m * n) {
                    System.out.println(-1);
                    break;
                }
                for (int j = (mTemp / n) - 1; (n * j) + y <= mTemp; j++) {
                    if (mTemp == (n * j) + y) {
                        System.out.println(mTemp);
                        find = true;
                        break;
                    }
                }
                if (find) break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new one6064().solution();
    }
}
