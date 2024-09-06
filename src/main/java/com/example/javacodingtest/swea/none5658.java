package com.example.javacodingtest.swea;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.06
 @link
 @timecomplex
 @performance 21112 kb 122 ms
 @category
 @note
 */
public class none5658 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum;
    static int n, k, slice;
    static char[] treasure;
    static List<Integer> numbers;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            k = Integer.parseInt(tokenizer.nextToken());
            slice = n / 4;
            treasure = reader.readLine().toCharArray();
            numbers = new LinkedList<>();

            for (int i = 0; i < slice; i++) {
                for (int j = 0; j < 4; j++) {
                    int start = (i + slice * j) % n;
                    String stringNum = "";
                    for (int s = 0; s < slice; s++) {
                        stringNum += treasure[(start + s) % n];
                    }
                    int number = Integer.parseInt(stringNum, 16);
                    if (!numbers.contains(number)) {
                        numbers.add(number);;
                    }
                }
            }
            Collections.sort(numbers, Collections.reverseOrder());
            builder.append("#").append(test).append(" ").append(numbers.get(k - 1)).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new none5658().solution();
    }

}
