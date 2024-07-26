package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class one2029 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum ; test++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(infoToken.nextToken());
            int b = Integer.parseInt(infoToken.nextToken());
            System.out.printf("#%d %d %d\n", test, a / b, a % b);
        }
    }

    public static void main(String[] args) throws IOException {
        new one2029().solution();
    }
}
