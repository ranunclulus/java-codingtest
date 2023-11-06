package com.example.javacodingtest.swea;

import java.util.*;

public class two1859 {
    public static void main(String args[]) throws Exception
    {

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int[] price = new int[n];
            for (int i = 0; i < n; i++) {
                price[i] = sc.nextInt();
            }

            long result = 0;
            int maxPrice = price[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                if (price[i] < maxPrice) {
                    result += (maxPrice - price[i]);
                }
                else {
                    maxPrice = price[i];
                }
            }
            sb.append('#').append(test_case).append(" ").append(result).append('\n');
        }
        System.out.println(sb.toString());
    }
}