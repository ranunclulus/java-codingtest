package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class five9655 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println((n % 2 == 1) ? "SK" : "CY");
    }
    
    public static void main(String[] args) throws IOException {
        new five9655().solution();
    }
}
