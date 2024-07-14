package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class four2670 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        double[] numbers = new double[n + 1];
        for (int i = 0; i < n; i++) {
            numbers[i] = Double.parseDouble(reader.readLine());
        }

        double max = 0;
        for (int i = 0; i < n; i++) {
            double temp = 1;
            for (int j = i; j < n; j++) {
                temp *= numbers[j];
                max = Math.max(max, temp);
            }
        }
        System.out.printf("%.3f", max);
    }

    public static void main(String[] args) throws IOException {
        new four2670().solution();
    }
}
