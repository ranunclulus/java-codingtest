package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.25
 @link https://www.acmicpc.net/problem/22862
 @timecomplex
 @performance 88340kb, 436ms
 @category
 @note
 */
public class five22862 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k, maxLength;
    static boolean[] isEven;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        isEven = new boolean[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(tokenizer.nextToken());
            isEven[i] = (number % 2 == 0);
        }

        int start = 0;
        int end = 0;
        int remove = 0;
        while (end < n) {
            if (remove < k) { //k보다 덜 삭제했는데 현재 조사하는 값이 홀수면
                if (!isEven[end]) remove++;
                end++;
                maxLength = Math.max(end - start - remove, maxLength);
            } else if (isEven[end]) {
                end++;
                maxLength = Math.max(end - start - remove, maxLength);
            } else {
                if (!isEven[start]) {
                    remove--;
                }
                start++;
            }
        }

        builder.append(maxLength);
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new five22862().solution();
    }
}
