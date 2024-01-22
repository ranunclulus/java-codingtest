package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class one5525 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        char[] input = reader.readLine().toCharArray();
        int length = 0;
        int count = 0;

        for (int i = 1; i < m - 1; i++) {
            if (input[i - 1] == 'I' && input[i] == 'O' && input[i + 1] == 'I') {
                length++;
                if (length == n) {
                    length--;
                    count++;
                }
                i++;
            } else {
                length = 0;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new one5525().solution();
    }
}
