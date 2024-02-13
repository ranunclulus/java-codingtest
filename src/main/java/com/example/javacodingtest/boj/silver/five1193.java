package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class five1193 {

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        int crossCount = 1;
        int preCountSum = 0;

        while (true) {
            if (num <= crossCount + preCountSum) {
                if (crossCount % 2 == 1) {
                    System.out.println((crossCount - (num - preCountSum - 1)) + "/" + (num - preCountSum));
                    break;
                } else {
                    System.out.println((num - preCountSum) + "/" + (crossCount - (num - preCountSum - 1)));
                    break;
                }
            } else {
                preCountSum += crossCount;
                crossCount++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five1193().solution();
    }
}
