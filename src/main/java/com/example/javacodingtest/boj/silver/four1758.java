package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class four1758 {
    public int n;
    public Integer[] tips;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        tips = new Integer[n];
        for (int i = 0; i < n; i++) {
            tips[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(tips, Collections.reverseOrder());
        long totalTip = tips[0];
        for (int i = 1; i < n; i++) {
            totalTip += Math.max(tips[i] - i, 0);
        }
        System.out.println(totalTip);
    }

    public static void main(String[] args) throws IOException {
        new four1758().solution();
    }
}
