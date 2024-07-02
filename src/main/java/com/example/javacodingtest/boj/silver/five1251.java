package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class five1251 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        int size = input.length();
        List<String> answerList = new ArrayList<>();

        for (int i = 0; i < size - 2; i++) {
            String one = input.substring(0, i + 1);
            for (int j = i + 1; j < size - 1; j++) {
                String two = input.substring(i + 1, j + 1);
                String three = input.substring(j + 1, size);

                String answer = "";
                for (int k = one.length() - 1; k >= 0; k--) {
                    answer += one.charAt(k);
                }
                for (int k = two.length() - 1; k >= 0; k--) {
                    answer += two.charAt(k);
                }
                for (int k = three.length() - 1; k >= 0; k--) {
                    answer += three.charAt(k);
                }
                answerList.add(answer);
            }
        }
        Collections.sort(answerList);
        System.out.println(answerList.get(0));
    }

    public static void main(String[] args) throws IOException {
        new five1251().solution();
    }
}
