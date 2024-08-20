package com.example.javacodingtest.swea;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.20
 @link
 @timecomplex
 @performance 104352KB, 1973MS
 @category
 @note
 */
public class three4796 {
    static Scanner sc = new Scanner(System.in);
    static int testNum;
    static int n;
    static int[] nums;
    static long count;
    public void solution() throws IOException {
        testNum = sc.nextInt();
        for (int test = 1; test <= testNum; test++) {
            // 입력
            n = sc.nextInt();
            nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            count = 0;

            for (int i = 0; i < n; i++) {
                int first = 0;
                int second = 0;
                int top = 0;
                for (int j = i; j < n - 1; j++) {
                    if (nums[j + 1] > nums[j]) {
                        first++;
                        top = j + 1;
                    }
                    else break;
                }
                for (int j = top; j < n - 1; j++) {
                    if (nums[j + 1] < nums[j]) {
                        second++;
                    }
                    else break;
                }
                if (first != 0 && second != 0)  {
                    i += (first + second - 1);
                    count += (first * second);
                }
            }
            System.out.printf("#%d %d\n", test, count);
        }
    }

    public static void main(String[] args) throws IOException {
        new three4796().solution();
    }
}

