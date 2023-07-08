package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class two1978 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numbers = Integer.parseInt(reader.readLine());
        StringTokenizer numToken = new StringTokenizer(reader.readLine());

        int answer = 0;

        for (int i = 0; i < numbers; i++) {
            int number = Integer.parseInt(numToken.nextToken());
            boolean primary = true;
            if(number == 1) continue;
            for (int j = 2; j < number; j++) {

                if (number % j == 0) {
                    primary = false;
                    break;
                }
            }
            if(primary) answer++;
        }
        System.out.println(answer);
    }
}
