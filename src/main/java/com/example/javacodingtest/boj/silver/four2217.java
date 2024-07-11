package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class four2217 {
    int n;
    Integer[] ropes;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        ropes = new Integer[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(ropes, Collections.reverseOrder());
        int max = ropes[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, ropes[i] * (i + 1));
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        new four2217().solution();
    }
}
