package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class one20529 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCnt = Integer.parseInt(reader.readLine());
        
        for (int test = 0; test < testCnt; test++) {
            int personCnt = Integer.parseInt(reader.readLine());

            String[] personMbti = new String[personCnt];
            StringTokenizer mbtiToken = new StringTokenizer(reader.readLine(), " ");

            // 입력받기
            for (int i = 0; i < personCnt; i++) {
                personMbti[i] = mbtiToken.nextToken();
            }

            if (personCnt > 32) {
                System.out.println(0);
                continue;
            }

            int minDistance = Integer.MAX_VALUE;

            for (int first = 0; first < personCnt; first++) {
                for (int second = first + 1; second < personCnt; second++) {
                    for (int third = second + 1; third < personCnt; third++) {
                        int temp = 0;
                        for (int alpha = 0; alpha < 4; alpha++) {
                            temp += (personMbti[first].charAt(alpha) != personMbti[second].charAt(alpha) ? 1 : 0);
                            temp += (personMbti[second].charAt(alpha) != personMbti[third].charAt(alpha) ? 1 : 0);
                            temp += (personMbti[first].charAt(alpha) != personMbti[third].charAt(alpha) ? 1 : 0);
                        }
                        minDistance = Math.min(temp, minDistance);
                        if (minDistance == 0) break;
                    }
                }
            }
            System.out.println(minDistance);
        }
    }

    public static void main(String[] args) throws IOException {
        new one20529().solution();
    }
}
