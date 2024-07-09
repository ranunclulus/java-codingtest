package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class two1959 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(infoToken.nextToken());
            int m = Integer.parseInt(infoToken.nextToken());
            int[] a = new int[n];
            int[] b = new int[m];
            infoToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(infoToken.nextToken());
            }
            infoToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(infoToken.nextToken());
            }

            int answer = Integer.MIN_VALUE;

            if (n < m) {
                for (int start = 0; start <= m - n; start++) {
                    int temp = 0;
                    for (int i = 0; i < n; i++) {
                        temp += (a[i] * b[start + i]);
                    }
                    answer = Math.max(answer, temp);
                }
            } else if (m < n) {
                for (int start = 0; start <= n - m; start++) {
                    int temp = 0;
                    for (int i = 0; i < m; i++) {
                        temp += (a[start + i] * b[i]);
                    }
                    answer = Math.max(answer, temp);
                }
            } else {
                int temp = 0;
                for (int i = 0; i < n; i++) {
                    temp += (a[i] * b[i]);
                }
                answer = Math.max(answer, temp);
            }

            System.out.printf("#%d %d\n", test, answer);
        }
    }

    public static void main(String[] args) throws IOException {
        new two1959().solution();
    }
}
