package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class four2491 {
    public static int n;
    public static int[] numbers;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(infoToken.nextToken());
        }
        int[] dpPlus = new int[n];
        int[] dpMinus = new int[n];
        dpPlus[0] = 1;
        dpMinus[0] = 1;
        for (int i = 1; i < n; i++) {
            if (numbers[i - 1] <= numbers[i]) dpPlus[i] = dpPlus[i - 1] + 1;
            else dpPlus[i] = 1;

            if (numbers[i - 1] >= numbers[i]) dpMinus[i] = dpMinus[i - 1] + 1;
            else dpMinus[i] = 1;
        }
        Arrays.sort(dpPlus);
        Arrays.sort(dpMinus);
        System.out.println(Math.max(dpMinus[n - 1], dpPlus[n - 1]));
    }

    public static void main(String[] args) throws IOException {
        new four2491().solution();
    }
}
