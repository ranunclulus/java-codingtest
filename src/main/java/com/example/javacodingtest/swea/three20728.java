package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class three20728 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(infoToken.nextToken());
            int k = Integer.parseInt(infoToken.nextToken());

            int[] candies = new int[n];
            infoToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                candies[i] = Integer.parseInt(infoToken.nextToken());
            }
            Arrays.sort(candies);
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n - k + 1; i++) {
                min = Math.min(min, candies[i + k - 1] - candies[i]);
            }
            System.out.printf("#%d %d\n", test, min);
        }
    }
}
