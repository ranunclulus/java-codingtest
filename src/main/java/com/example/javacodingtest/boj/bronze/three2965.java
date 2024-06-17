package com.example.javacodingtest.boj.bronze;

import java.util.Scanner;

public class three2965 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();
        int diff = Math.max((num3 - num2), (num2 - num1));
        System.out.println(diff - 1);
    }
}
