package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class one1629 {
    private int a;
    private int b;
    private int c;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        a = Integer.parseInt(infoToken.nextToken());
        b = Integer.parseInt(infoToken.nextToken());
        c = Integer.parseInt(infoToken.nextToken());

        System.out.println(calculation(b));
    }

    // a % c 연산이 b 번 반복되어야 한다는 점을 기억
    public long calculation(int b) {
        if (b == 1) {
            return a % c;
        }

        long half = calculation(b / 2);
        if (b % 2 == 1) {
            return half * half % c * a % c;
        }
        else {
            return half * half % c;
        }
    }

    public static void main(String[] args) throws IOException {
        new one1629().solution();
    }
}
