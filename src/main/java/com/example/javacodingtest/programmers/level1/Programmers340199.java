package com.example.javacodingtest.programmers.level1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2025.01.11
 @link https://school.programmers.co.kr/learn/courses/30/lessons/340199
 @timecomplex
 @performance
 @category
 @note
 */
public class Programmers340199 {
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();

    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        while (!finish(wallet, bill)) {
            bill[1] /= 2;
            answer++;
        }
        return answer;
    }

    private boolean finish(int[] wallet, int[] bill) {
        Arrays.sort(wallet);
        Arrays.sort(bill);
        if (bill[0] > wallet[0] || bill[1] > wallet[1]) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        int[] wallet = new int[] {30, 15};
        int[] bill = new int[] {26, 17};
        builder.append(new Programmers340199().solution(wallet, bill));
        writer.write(builder.toString());
        writer.flush();
    }
}
