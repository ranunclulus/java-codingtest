package com.example.javacodingtest.swea;
// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV134DPqAA8CFAYh&categoryId=AV134DPqAA8CFAYh&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=1
import java.util.Scanner;

public class three1206 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int testCase = 1; testCase < 11; testCase++) {
            int buildingCnt = sc.nextInt();
            int[] buildings = new int[buildingCnt];

            for (int i = 0; i < buildingCnt; i++) {
                buildings[i] = sc.nextInt();
            }

            int result = 0;
            for (int i = 2; i < buildingCnt - 2; i++) {
                int max = Math.max(buildings[i - 2],
                        Math.max(buildings[i - 1],
                        Math.max(buildings[i + 1], buildings[i + 2])));
                if (buildings[i] > max) {
                    result += buildings[i] - max;
                }
            }
            System.out.printf("#%d %d\n", testCase, result);
        }
    }
}