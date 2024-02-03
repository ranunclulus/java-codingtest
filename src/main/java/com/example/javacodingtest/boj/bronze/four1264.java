package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class four1264 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int count = 0;
            String input = sc.nextLine();
            if (input.equals("#"))
                break;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    count++;
                }
                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    public static void main(String[] args) throws IOException {
        new four1264().solution();
    }
}
