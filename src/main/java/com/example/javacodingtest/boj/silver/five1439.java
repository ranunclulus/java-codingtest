package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class five1439 {

    private char[] charNum;
    private int length;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        char[] charNum = sc.next().toCharArray();
        length = charNum.length;
        int count = 0;

        List<Character> differ = new ArrayList<>();
        for (int i = 0; i < length - 1; i++) {
            if (charNum[i] != charNum[i + 1]) differ.add(charNum[i]);
        }
        differ.add(charNum[length - 1]);

        int oneCount = 0;
        int zeroCount = 0;
        for (char ch : differ) {
            if (ch == '0') zeroCount++;
            if (ch == '1') oneCount++;
        }

        System.out.println(Math.min(oneCount, zeroCount));
    }

    public static void main(String[] args) throws IOException {
        new five1439().solution();
    }
}
