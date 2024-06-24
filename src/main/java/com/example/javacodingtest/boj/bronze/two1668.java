package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class two1668 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int leftCnt = 0;
        int rightCnt = 0;
        int leftMax = 0;
        int rightMax = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] > leftMax) {
                leftMax = nums[i];
                leftCnt++;
            }
            if (nums[n - i - 1] > rightMax) {
                rightMax = nums[n - i - 1];
                rightCnt++;
            }
        }
        System.out.println(leftCnt);
        System.out.println(rightCnt);
    }

    public static void main(String[] args) throws IOException {
        new two1668().solution();
    }
}
