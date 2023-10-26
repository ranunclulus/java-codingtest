package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class two11053 {
    private static int sequenceLength;
    private static int[] sequence;
    private static int[] dp;
    private static int result;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        sequenceLength = Integer.parseInt(reader.readLine());
        sequence = new int[sequenceLength];
        dp = new int[sequenceLength];

        StringTokenizer sequenceToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < sequenceLength; i++) {
            sequence[i] = Integer.parseInt(sequenceToken.nextToken());
        }

        dp[0] = 1;
        result = 1;
        for (int i = 1; i < sequenceLength; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0 ; j--) {
                if (sequence[i] > sequence[j] && dp[j] > max) {
                    max = dp[j];
                    dp[i] = max + 1;
                    result = Math.max(result, dp[i]);
                }
            }
            if (max == 0) {
                dp[i] = 1;
            }
            result = Math.max(result, max);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new two11053().solution();
    }
}
