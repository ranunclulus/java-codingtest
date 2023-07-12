package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class five10814 {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        String[][] people = new String[number][2];

        for (int i = 0; i < number; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            people[i][0] = tokenizer.nextToken();
            people[i][1] = tokenizer.nextToken();
        }


    }

    public static void main(String[] args) throws IOException {
        new five10814().solution();
    }
}
