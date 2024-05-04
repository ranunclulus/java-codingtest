package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class three1284 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String n = br.readLine();
            int length = 1;
            if (n.equals("0")) {
                break;
            }
            for (int i = 0; i < n.length(); i++) {
                if (n.charAt(i) == '1') {
                    length += 3;
                } else if (n.charAt(i) == '0') {
                    length += 5;
                } else {
                    length += 4;
                }
            }
            System.out.println(length);
        }
    }
}
