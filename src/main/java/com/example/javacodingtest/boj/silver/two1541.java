package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class two1541 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] sliceMinus = input.split("-");
        int result = 0;

        for (int i = 0; i < sliceMinus.length; i++) {
            String[] slicePlus = sliceMinus[i].split("\\+");
            if (i == 0) { // 더하기
                for (String value : slicePlus) {
                    result += Integer.parseInt(value);
                }
            } else { // 빼기
                for (String value : slicePlus) {
                    result -= Integer.parseInt(value);
                }
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new two1541().solution();
    }
}
