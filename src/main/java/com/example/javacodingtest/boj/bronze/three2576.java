package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class three2576 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int min = Integer.MAX_VALUE;
        int sum = 0;
        boolean flag = false;

        for (int i = 0; i < 7; i++) {
            int num = sc.nextInt();
            if (num % 2 == 1) {
                flag = true;
                sum += num;
                min = Math.min(min, num);
            }
        }

        if (flag) {
            System.out.println(sum);
            System.out.println(min);
        } else System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        new three2576().solution();
    }

}
