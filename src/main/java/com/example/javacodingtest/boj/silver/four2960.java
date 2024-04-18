package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class four2960 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean[] check = new boolean[n + 1];

        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                if (!check[j]) {
                    cnt++;
                    check[j] = true;
                }

                if (cnt == k) {
                    System.out.println(j);
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four2960().solution();
    }
}
