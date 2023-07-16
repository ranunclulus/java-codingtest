package com.example.javacodingtest.boj.silver;


import java.util.Arrays;
import java.util.Scanner;

public class five11650 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] position = new int[n][2];

        for (int i = 0; i < n; i++) {
            position[i][0] = sc.nextInt();
            position[i][1] = sc.nextInt();
        }
        
        Arrays.sort(position, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            else {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < n; i++) {
            System.out.println(position[i][0] + " " + position[i][1]);
        }
        
    }
}
