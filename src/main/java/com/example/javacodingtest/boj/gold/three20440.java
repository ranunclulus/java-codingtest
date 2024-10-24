package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.24
 @link https://www.acmicpc.net/problem/20440
 @timecomplex
 @performance 387452kb, 1512ms
 @category
 @note
 */
public class three20440 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, sum, max;
    static long startIndex, endIndex;
    static boolean signal;
    static HashMap<Long, Integer> mosquitoes;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        mosquitoes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            long start = Long.parseLong(tokenizer.nextToken());
            long end = Long.parseLong(tokenizer.nextToken());

            mosquitoes.put(start, mosquitoes.getOrDefault(start, 0) + 1);
            mosquitoes.put(end, mosquitoes.getOrDefault(end, 0) - 1);
        }

        List<Long> keySet = new LinkedList<>(mosquitoes.keySet());
        Collections.sort(keySet, Long::compare);

        for(long key : keySet) {
            sum += mosquitoes.get(key);

            if (sum > max) {
                max = sum;
                startIndex = key;
                signal = true;
            } else if (sum < max && signal) {
                endIndex = key;
                signal = false;
            }
        }

        builder.append(max).append('\n');
        builder.append(startIndex).append(' ').append(endIndex);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three20440().solution();
    }
}
