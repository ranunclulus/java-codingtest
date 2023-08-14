package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class one20529 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCnt = Integer.parseInt(reader.readLine());
        
        for (int test = 0; test < testCnt; test++) {
            
            int personCnt = Integer.parseInt(reader.readLine());
            String[][] personMbti = new String[personCnt][4];
            StringTokenizer mbtiToken = new StringTokenizer(reader.readLine());
            

        }
    }

    public static void main(String[] args) throws IOException {
        new one20529().solution();
    }
}
