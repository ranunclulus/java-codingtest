package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class four1969 {
    String[] nucleotides = {"A", "C", "G", "T"};

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int sNum = Integer.parseInt(infoToken.nextToken());
        int sLength = Integer.parseInt(infoToken.nextToken());

        String[] dnas = new String[sNum];
        for (int i = 0; i < sNum; i++) {
            dnas[i] = reader.readLine();
        }

        String result = "";
        int sum = 0;

        for (int i = 0; i < sLength; i++) {
            int[] alpha = new int[4];
            for (String s : dnas) {
                char ch = s.charAt(i);
                if (ch == 'A') alpha[0]++;
                else if (ch == 'C') alpha[1]++;
                else if (ch == 'G') alpha[2]++;
                else alpha[3]++;
            }

            int maxValue = Integer.MIN_VALUE;
            int index = -1;

            for (int j = 0; j < 4; j++) {
                if (alpha[j] > maxValue) {
                    maxValue = alpha[j];
                    index = j;
                }
            }

            result += nucleotides[index];

            for (int j = 0; j < 4; j++) {
                if (j != index) sum += alpha[j];
            }
        }


        System.out.println(result);
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        new four1969().solution();
    }
}
