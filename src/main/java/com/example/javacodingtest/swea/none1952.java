package com.example.javacodingtest.swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.20
 @link
 @timecomplex
 @performance 18956KB, 113MS
 @category
 @note
 */
public class none1952 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int day;
    static int month;
    static int threeMonth;
    static int year;
    static int[] plans;
    static int[] dp;
    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            day = Integer.parseInt(st.nextToken());
            month = Integer.parseInt(st.nextToken());
            threeMonth = Integer.parseInt(st.nextToken());
            year = Integer.parseInt(st.nextToken());
            plans = new int[13];
            dp = new int[13];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < 13; i++) {
                plans[i] = Integer.parseInt(st.nextToken());
            }

            dp[1] = Math.min(month, day * plans[1]);
            dp[2] = dp[1] + Math.min(month, day * plans[2]);
            for (int i = 3; i < 13; i++) {
                dp[i] = Math.min(dp[i - 3] + threeMonth, Math.min(dp[i - 1] + plans[i] * day, dp[i - 1] + month));
            }
            dp[12] = Math.min(dp[12], year);

            sb.append("#").append(test).append(" ").append(dp[12]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new none1952().solution();
    }
}

