package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class three2547 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            br.readLine();
            int n = Integer.parseInt(br.readLine());

            BigInteger sum = new BigInteger("0");

            for (int j = 0; j < n; j++) {
                sum = sum.add(BigInteger.valueOf(Long.parseLong(br.readLine())));
            }
            System.out.println((String.valueOf(sum.remainder(BigInteger.valueOf(n))).equals("0")) ? "YES" : "NO");
        }
    }
}
