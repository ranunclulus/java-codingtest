package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class five1312 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int a = Integer.parseInt(infoToken.nextToken());
        int b = Integer.parseInt(infoToken.nextToken());
        int n = Integer.parseInt(infoToken.nextToken());

        a %= b;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            a = a * 10;
            answer = a / b;
            a %= b;
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new five1312().solution();
    }
}
