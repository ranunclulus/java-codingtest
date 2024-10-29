package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.29
 @link https://www.acmicpc.net/problem/15961
 @timecomplex
 @performance 170920kb 548ms
 @category
 @note
 */
public class four15961_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, d, k, c;
    static int[] sushi;
    static int[] eated;
    static int tempCount;
    static int maxCount;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken()); // 접시 수
        d = Integer.parseInt(tokenizer.nextToken()); // 초밥의 가짓수
        k = Integer.parseInt(tokenizer.nextToken()); // 연속해서 먹는 접시 수
        c = Integer.parseInt(tokenizer.nextToken()); // 쿠폰 번호

        sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(reader.readLine());
        }

        eated = new int[d + 1];

        tempCount = 0;
        for (int i = 0; i < k; i++) {
            if (eated[sushi[i]] == 0) tempCount++;
            eated[sushi[i]]++;
        }
        maxCount = tempCount;

        for (int i = 1; i <= n; i++) {
            eated[sushi[i - 1]]--;
            if (eated[sushi[i - 1]] == 0) tempCount--;

            if (eated[sushi[(i + k - 1) % n]] == 0) tempCount++;
            eated[sushi[(i + k - 1) % n]]++;

            if (tempCount >= maxCount) {
                if (eated[c] == 0) maxCount = tempCount + 1;
                else maxCount = tempCount;
            }
        }

        builder.append(maxCount);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four15961_2().solution();
    }
}
