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
        int size = input.length() - 1;

        List<String> answer = new ArrayList<>();
        for (int i = 0; i < size - 1; i++) {
            String result = "";
            String one = input.substring(0, i);
            for (int j = one.length() - 1; j >= 0; j--) {
                result += one.charAt(j);
            }

            for (int j = i + 1; j <= size; j++) {
                String ans = result;
                String two = input.substring(i, j);
                for (int k = two.length() - 1; k >= 0; k--) {
                    ans += two.charAt(k);
                }
                String three = input.substring(j);
                for (int k = three.length() - 1; k >= 0; k--) {
                    ans += three.charAt(k);
                }
                answer.add(ans);
            }
        }

        Collections.sort(answer);
        System.out.println(answer.get(0));
    }

    public static void main(String[] args) throws IOException {
        new five1251().solution();
    }
}
