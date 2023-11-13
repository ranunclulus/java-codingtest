package com.example.javacodingtest.swea;
// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AYcllbDqUVgDFASR&categoryId=AYcllbDqUVgDFASR&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=1&&&&&&&&&&

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class three169190 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 1; test <= testNum; test++) {
            int result = 0;
            List<int[]> spots = new ArrayList<>();

            int radius = sc.nextInt();
            double powRadius = Math.pow(radius, 2);
            for (int i = 0; i <= radius; i++) {
                for (int j = 0; j <= radius; j++) {
                    if (Math.pow(i, 2) + Math.pow(j, 2) <= powRadius) {
                        spots.add(new int[]{i, j});
                    }
                }
            }

            for (int[] spot : spots) {
                if (spot[0] == 0 && spot[1] == 0) {
                    result++;
                } else if (spot[0] == 0 || spot[1] == 0) {
                    result += 2;
                } else result += 4;
            }

            System.out.printf("#%d %d\n", test, result);
        }
    }

    public static void main(String[] args) throws IOException {
        new three169190().solution();
    }
}
