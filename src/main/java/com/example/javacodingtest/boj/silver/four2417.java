package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class four2417 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        long left = 0;
        long right = n;
        long result = 0;

        while (left <= right) {
            long middle = (left + right) / 2;

            if (Math.pow(middle, 2) >= n) {
                right = middle - 1;
                result = middle;
            } else {
                left = middle + 1;
            }
        }
        if (n == 0) result = 1;
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new four2417().solution();
    }
}
