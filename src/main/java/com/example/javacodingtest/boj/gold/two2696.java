package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.04.02
 @link https://www.acmicpc.net/problem/2696
 @timecomplex
 @performance 16488KB 180MS
 @category
 @note
 */
public class two2696 {


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int T, M;
    
    public void solution() throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int test = 0; test < T; test++) {
            M = Integer.parseInt(reader.readLine());
            builder.append((M + 1) / 2).append('\n');

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o1 -o2);
            PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

            int temp = 0;
            int count = 0;
            for (int i = 0; i < (M / 10) + 1; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                while (tokenizer.hasMoreTokens()) {
                    int value = Integer.parseInt(tokenizer.nextToken());

                    if (minHeap.isEmpty() || value <= minHeap.peek()) {
                        minHeap.add(value);
                    } else {
                        maxHeap.add(value);
                    }

                    if (minHeap.size() > maxHeap.size() + 1) {
                        maxHeap.add(minHeap.poll());
                    } else if (maxHeap.size() > minHeap.size()) {
                        minHeap.add(maxHeap.poll());
                    }

                    temp++;
                    if (temp % 2 == 1) {
                        builder.append(minHeap.peek()).append(' ');
                        count++;
                        if (count % 10 == 0) builder.append('\n');
                    }
                }
            }
            builder.append('\n');
        }

        writer.write(builder.toString());
        writer.flush();

    }


    public static void main(String[] args) throws IOException {
        new two2696().solution();
    }
}
