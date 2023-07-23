package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class five1676 {
    int twoCount = 0;
    int fiveCount = 0;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        for (int i = 2; i <= n ; i++) {
            search(i);
        }
        System.out.println(Math.min(twoCount, fiveCount));
    }

    public void search(int num) {

        while (num > 1) {
            if (num % 2 == 0) {
                twoCount++;
                num /= 2;
            }
            else if (num % 5 == 0) {
                fiveCount++;
                num /= 5;
            }
            else break;
        }
    }

    public static void main(String[] args) throws IOException {
        new five1676().solution();
    }
}
