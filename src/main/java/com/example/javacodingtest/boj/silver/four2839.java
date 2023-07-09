package com.example.javacodingtest.boj.silver;
// https://www.acmicpc.net/problem/2839
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class four2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int weight = Integer.parseInt(reader.readLine());
        int bagCount = 0;

        while (true) {
            if (weight % 5 == 0) {
                System.out.println(weight / 5 + bagCount);
                break;
            }
            else if (weight < 0) {
                System.out.println(-1);
                break;
            }
            weight -= 3;
            bagCount++;
        }
    }
}
