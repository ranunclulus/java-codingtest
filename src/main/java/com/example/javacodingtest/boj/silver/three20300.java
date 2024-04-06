package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class three20300 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long[] machines = new long[n];

        StringTokenizer machineToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            machines[i] = Long.parseLong(machineToken.nextToken());
        }

        Arrays.sort(machines);
        long max = 0;
        if (n % 2 == 0) {
            for (int i = 0; i < n / 2; i++) {
                max = Math.max(max, machines[i] + machines[n - 1 - i]);
            }
        } else {
            max = machines[n - 1];
            for (int i = 0; i < (n - 1) / 2; i++) {
                max = Math.max(max, machines[i] + machines[n - 2 - i]);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        new three20300().solution();
    }
}
