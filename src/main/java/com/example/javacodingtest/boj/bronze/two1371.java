package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class two1371 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int[] alpha = new int[26];
        String input = "";

        while (sc.hasNextLine()) {
            String add = sc.nextLine();
            input += add;
        }

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == ' ') continue;
            else {
                int asciiCh = ((int) ch) - 97;
                alpha[asciiCh]++;
            }
        }

        int max = 0;
        for (int i = 0; i < 26; i++) {
            max = Math.max(max, alpha[i]);
        }

        for (int i = 0; i < 26; i++) {
            if (alpha[i] == max) {
                System.out.print((char) (i + 97));
            }
        }
    }

}
