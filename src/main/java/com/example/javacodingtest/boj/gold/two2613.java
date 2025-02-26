package com.example.javacodingtest.boj.gold;

import java.util.*;
import java.io.*;

/*
 @author ranuinclulus
 @since 2025.02.26
 @link https://www.acmicpc.net/problem/2613
 @timecomplex
 @performance
 @category
 @note
 */
public class two2613 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int M, N;
    static int left, right, mid;
    static int[] marbles;
    static int[] countByGroup;


    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        marbles = new int[N];
        tokenizer = new StringTokenizer(reader.readLine());
        left = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            marbles[i] = Integer.parseInt(tokenizer.nextToken());
            left = Math.min(left, marbles[i]);
            right += marbles[i];
        }
        countByGroup = new int[M];

        while (left < right) {
            mid = (left + right) / 2;
            if (marbleCheck()) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        builder.append(left).append("\n");
        for (int i = 0; i < M; i++) {
            builder.append(countByGroup[i]).append(" ");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private boolean marbleCheck() {
        int groupCount = 1;
        int marbleCount = 0;
        int sum = 0;
        int[] tempCount = new int[M];

        for (int i = 0; i < N; i++) {
            if (groupCount > M || marbles[i] > mid) return false;

            if (sum + marbles[i] > mid || N - i == M - groupCount) {
                tempCount[groupCount - 1] = marbleCount;
                groupCount++;
                marbleCount = 1;
                sum = marbles[i];
            } else {
                marbleCount++;
                sum += marbles[i];
            }
        }

        if (groupCount > M) return false;

        tempCount[groupCount - 1] = marbleCount;
        for (int i = 0; i < M; i++) {
            countByGroup[i] = tempCount[i];
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new two2613().solution();
    }
}
