package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class three16002 {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());

        for (int test = 1; test <= testNum; test++) {
            int num = Integer.parseInt(reader.readLine());
            int a = num + 4;
            int b = 4;

            while (isPrime(a) || isPrime(b)) {
                a++;
                b++;
            }

            System.out.printf("#%d %d %d\n", test, a, b);
        }
    }

    private boolean isPrime(int num) {
        for (int j = 2; j < num; j++) {
            if (num % j == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new three16002().solution();
    }
}
