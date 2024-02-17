package com.example.javacodingtest.boj.bronze;

import java.io.IOException;
import java.util.Scanner;

public class two1550 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(Integer.parseInt(input, 16));
    }

    public static void main(String[] args) throws IOException {
        new two1550().solution();
    }
}
