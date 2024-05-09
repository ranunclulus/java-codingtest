package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class three20551 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        for (int test = 0; test < testCase; test++) {
            int[] candies = new int[3];
            StringTokenizer candyToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < 3; i++) {
                candies[i] = Integer.parseInt(candyToken.nextToken());
            }

            if (candies[2] < 3 || candies[1] < 2) {
                System.out.printf("#%d %d\n", test + 1, -1);
                continue;
            }

            int oneTwo = candies[1] - candies[0];
            int twoThree = candies[2] - candies[1];
            int ate = 0;

            while (oneTwo <= 0 || twoThree <= 0) {
                if (twoThree <= 0) {
                    ate++;
                    candies[1]--;
                    oneTwo = candies[1] - candies[0];
                    twoThree = candies[2] - candies[1];
                } else if (oneTwo <= 0) {
                    ate++;
                    candies[0]--;
                    oneTwo = candies[1] - candies[0];
                    twoThree = candies[2] - candies[1];
                }
            }

            System.out.printf("#%d %d\n", test + 1, ate);
        }
    }

    public static void main(String[] args) throws IOException {
        new three20551().solution();
    }
}
