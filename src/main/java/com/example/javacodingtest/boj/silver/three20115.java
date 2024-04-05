package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class three20115 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer drinkToken = new StringTokenizer(reader.readLine());
        double[] drinks = new double[n];

        double max = 0;

        for (int i = 0; i < n; i++) {
            drinks[i] = Double.parseDouble(drinkToken.nextToken());
            if (drinks[i] > max) max = drinks[i];
        }

        double ans = max;
        for (int i = 0; i < n; i++) {
            if (max != drinks[i]) {
                ans += (drinks[i] / 2);
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        new three20115().solution();
    }
}
