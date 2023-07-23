package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/1436
public class five1436 {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int titleInt = 666;
        int cnt = 0;

        while(true) {
            String title = Integer.toString(titleInt);
            if (title.contains("666")) cnt++;
            if(cnt == n) return titleInt;
            titleInt++;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new five1436().solution());
    }
}
