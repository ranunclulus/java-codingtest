package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class two1961 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            int size = Integer.parseInt(reader.readLine());
            int[][] arr = new int[size][size];
            for (int i = 0; i < size; i++) {
                StringTokenizer infoToken = new StringTokenizer(reader.readLine());
                for (int j = 0; j < size; j++) {
                    arr[i][j] = Integer.parseInt(infoToken.nextToken());
                }
            }

            String[] answer = new String[size];
            for (int i = 0; i < size; i++) {
                answer[i] = "";
                for (int j = 0; j < size; j++) {
                    answer[i] += arr[size - j - 1][i];
                }
                answer[i] += " ";
                for (int j = 0; j < size; j++) {
                    answer[i] += arr[size - i - 1][size - j - 1];
                }
                answer[i] += " ";
                for (int j = 0; j < size; j++) {
                    answer[i] += arr[j][size - i - 1];
                }
            }


            System.out.printf("#%d\n", test);
            for (String row:answer) {
                System.out.println(row);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two1961().solution();
    }
}
