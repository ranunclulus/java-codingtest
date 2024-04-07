package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class three20365 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        String input = sc.nextLine();
        char start = input.charAt(0);
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (input.charAt(i - 1) == start && input.charAt(i) != start) {
                count++;
            }
        }

        System.out.println(count + 1);
    }

    public static void main(String[] args) throws IOException {
        new three20365().solution();
    }
}
