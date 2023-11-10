package com.example.javacodingtest.swea;
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15Khn6AN0CFAYD&categoryId=AV15Khn6AN0CFAYD&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1

import java.io.IOException;
import java.util.Scanner;

public class three1244 {
    private String[] numbers;
    private int answer, switchNum, radix;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 1; test <= testNum; test++) {
            numbers = sc.next().split("");
            radix = numbers.length;
            switchNum = sc.nextInt();

            // 자릿수보다 교환 횟수가 클 때 -> 자릿수만큼만 옮겨도 모두 다 이동시킬 수 있음
            if (radix < switchNum) {
                switchNum = radix;
            }

            answer = 0;
            dfs(0, 0);

            System.out.printf("#%d %d\n", test, answer);
        }
    }

    private void dfs(int start, int count) {
        if (count == switchNum) {
            String result = "";
            for (String number : numbers) {
                result += number;
            }
            answer = Math.max(answer, Integer.parseInt(result));
            return;
        }

        for (int i = start; i < radix; i++) {
            for (int j = i + 1; j < radix; j++) {
                String temp = numbers[j];
                numbers[j] = numbers[i];
                numbers[i] = temp;

                dfs(i, count + 1);

                temp = numbers[j];
                numbers[j] = numbers[i];
                numbers[i] = temp;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three1244().solution();
    }
}
