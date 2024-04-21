package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class four4470 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {
            String input = sc.nextLine();
            System.out.printf("%d. %s\n", i, input);
        }
    }

    public static void main(String[] args) throws IOException {
        new four4470().solution();
    }
}
