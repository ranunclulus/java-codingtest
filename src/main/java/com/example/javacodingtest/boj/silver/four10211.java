package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class four10211 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 0; test < testNum; test++) {
            int n = Integer.parseInt(reader.readLine());
            int[] numbers = new int[n];
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(infoToken.nextToken());
            }

            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int temp = 0;
                for (int j = i; j < n; j++) {
                    temp += numbers[j];
                    maxValue = Math.max(maxValue, temp);
                }
            }
            System.out.println(maxValue);
        }
    }

    public static void main(String[] args) throws IOException {
        new four10211().solution();
    }
}
