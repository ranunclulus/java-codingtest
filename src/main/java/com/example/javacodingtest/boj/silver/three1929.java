package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1929
public class three1929 {
    private boolean[] prime;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int m = Integer.parseInt(infoToken.nextToken());
        int n = Integer.parseInt(infoToken.nextToken());
        prime = new boolean[n + 1];
        prime[0] = true;
        prime[1] = true;
        isPrime(n);
        for (int i = m; i <= n; i++) {
            if (prime[i] == false) System.out.println(i);
        }
    }

    private void isPrime(int number) {
        for (int i = 0; i < number; i++) {
            if (prime[i] == true) continue;
            for (int j = 2 * i; j <= number; j += i) prime[j] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        new three1929().solution();
    }
}
