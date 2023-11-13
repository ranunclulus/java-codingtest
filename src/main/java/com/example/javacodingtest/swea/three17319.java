package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AYgEiwbKy48DFARP&categoryId=AYgEiwbKy48DFARP&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=1
public class three17319 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();
        for (int test = 1; test <= testNum; test++) {
            boolean possible = false;
            int stringNum = sc.nextInt();
            String text = sc.next();

            if (stringNum > 1 && stringNum % 2 != 1) {
                String first = text.substring(0, stringNum / 2);
                String second = text.substring(stringNum / 2, stringNum);
                if (first.equals(second)) possible = true;
            }
            System.out.printf("#%d %s\n", test, possible ? "Yes" : "No");
        }
    }

    public static void main(String[] args) throws IOException {
        new three17319().solution();
    }
}
