package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class four18110 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        int num = (int) Math.round(n * 0.15);
        double sum = 0;
        for (int i = num; i < n - num; i++)
            sum += arr[i];
        System.out.println((int) Math.round(sum / (n - num * 2)));
    }

    public static void main(String[] args) throws IOException {
        new four18110().solution();
    }
}
