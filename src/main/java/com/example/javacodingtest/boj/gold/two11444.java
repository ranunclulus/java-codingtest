package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class two11444 {
    private static final long MOD = 1000000007l;

    private long[][] matrixMult(long[][] a, long[][] b) {
        long[][] arr = new long[2][2];
        arr[0][0] = (a[0][0] * b[0][0] % MOD + a[0][1] * b[1][0] % MOD) % MOD;
        arr[1][0] = (a[0][0] * b[0][1] % MOD + a[0][1] * b[1][1] % MOD) % MOD;
        arr[0][1] = (a[1][0] * b[0][0] % MOD + a[1][1] * b[1][0] % MOD) % MOD;
        arr[1][1] = (a[1][0] * b[0][1] % MOD + a[1][1] * b[1][1] % MOD) % MOD;
        return arr;
    }

    private long[][] fibo(long n) {
        if (n == 1) {
            long[][] arr = {{1, 1}, {1, 0}};
            return arr;
        }
        long[][] tmp = fibo(n / 2);
        if (n % 2 == 1) {
            return matrixMult(matrixMult(tmp, tmp), fibo(1));
        } else {
            return matrixMult(tmp, tmp);
        }
    }

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.println(fibo(n)[0][1]);
    }

    public static void main(String[] args) throws IOException {
        new two11444().solution();
    }
}
