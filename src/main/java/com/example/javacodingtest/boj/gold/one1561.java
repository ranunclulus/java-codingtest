package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.03.10
 @link https://www.acmicpc.net/problem/1561
 @timecomplex
 @performance 14996KB 144MS
 @category
 @note
 */
public class one1561 {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static long N, answer;
    static int M;
    static int[] times;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Long.parseLong(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        times = new int[M + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 1; i <= M; i++) {
            times[i] = Integer.parseInt(tokenizer.nextToken());
        }

        if (N <= M) answer = N;
        else {
            long finishTime = getFinishTime();
            long previousChildren = getChildrenNum(finishTime - 1);

            for(int i = 1; i <= M; i++) {
                if (finishTime % times[i] == 0) previousChildren++;
                if (previousChildren == N) {
                    answer = i;
                    break;
                }
            }
        }

        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();

    }

    private long getFinishTime() {
        long start = 0;
        long end = N * 30;
        while (start <= end) {
            long mid = (start + end) / 2;
            
            long childrenNum = getChildrenNum(mid);

            if (childrenNum < N) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    private long getChildrenNum(long mid) {
        long childrenNum = M;
        for(int i = 1; i <= M; i++) {
            childrenNum += mid / times[i];
        }
        return childrenNum;
    }


    public static void main(String[] args) throws IOException {
        new one1561().solution();
    }
}
