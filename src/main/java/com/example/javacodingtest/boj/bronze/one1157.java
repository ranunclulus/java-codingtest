package com.example.javacodingtest.boj.bronze;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class one1157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        input = input.toLowerCase();
        int[] alpha = new int[26];
        for (int i = 0; i < input.length(); i++) {
            int index = (int)(input.charAt(i)) - 97;
            alpha[index]++;
        }
        int count = 0;
        int max_val = 0;
        int max_alpha = 0;

        for (int i = 0; i < 26; i++) {
            if(alpha[i] >= max_val) {
                max_val = alpha[i];
            }
        }

        for (int i = 0; i < 26; i++) {
            if (max_val == alpha[i]){
                count++;
                max_alpha = i;
            }
        }

        if(count > 1){
            System.out.println("?");
        }
        else {
            System.out.println((char)(max_alpha + 65));
        }
    }
}
