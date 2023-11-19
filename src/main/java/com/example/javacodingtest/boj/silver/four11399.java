package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//https://www.acmicpc.net/problem/11399
public class four11399 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] time = new int[n];

        for (int i = 0; i < n; i++) {
            time[i] = sc.nextInt();
        }

        Arrays.sort(time);
        int result = 0;

        for (int i = 0; i < n; i++) {
            int spendTime = 0;
            for (int j = 0; j <= i; j++) {
                spendTime += time[j];
            }
            result += spendTime;
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new four11399().solution();
    }
}
