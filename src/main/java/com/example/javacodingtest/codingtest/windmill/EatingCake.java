package com.example.javacodingtest.codingtest.windmill;

import java.util.Scanner;

public class EatingCake {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        boolean[] eaten = new boolean[n];
        int totalNum = n;
        int index = 0;
        eaten[index] = true;
        totalNum--;

        while (totalNum > 2) {
            int kCount = 0;
            for (int i = 0; i < n; i++) {
                int current = (index + i) % n;
                if (eaten[current]) continue; // 먹은 건 무시

                kCount++;
                if (kCount == k) {
                    index = current;
                    eaten[current] = true;
                    totalNum--;
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!eaten[i]) System.out.printf("%d ", i + 1);
        }
    }
}
