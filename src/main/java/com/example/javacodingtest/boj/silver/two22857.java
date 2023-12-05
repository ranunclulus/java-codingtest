package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class two22857 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        int rightPoint = 0, leftPoint = 0;
        int odd = 0, even = 0;

        if (numbers[0] % 2 == 0) even++;
        else odd++;

        int result = even;

        while (rightPoint >= leftPoint) {
            if (odd > k) {
                if (numbers[leftPoint] % 2 == 0) even--;
                else odd--;
                leftPoint++;
            } else {
                rightPoint++;
                if (rightPoint >= n) break;
                if (numbers[rightPoint] % 2 == 0) even++;
                else odd++;
                result = Math.max(result, even);
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new two22857().solution();
    }
}
