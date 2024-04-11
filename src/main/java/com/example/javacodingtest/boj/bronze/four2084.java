package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class four2084 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = reader.readLine();
            if (input.equals("# 0 0")) break;

            String[] info = input.split(" ");
            int age = Integer.parseInt(info[1]);
            int weight = Integer.parseInt(info[2]);
            if (age > 17 || weight >= 80) {
                System.out.printf("%s Senior\n", info[0]);
            } else {
                System.out.printf("%s Junior\n", info[0]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four2084().solution();
    }
}
