package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class one2014 {
    BufferedReader reader;
    BufferedWriter writer;
    StringTokenizer infoTokoen;
    StringBuilder builder;

    public void solution() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        builder = new StringBuilder();
        infoTokoen = new StringTokenizer(reader.readLine());
        int size = Integer.parseInt(infoTokoen.nextToken());
        int target = Integer.parseInt(infoTokoen.nextToken());

        int[] nums = new int[size];
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        infoTokoen = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            nums[i] = Integer.parseInt(infoTokoen.nextToken());
            priorityQueue.offer((long)nums[i]);
        }

        long answer = 0L;
        while (target --> 0) {
            answer = priorityQueue.poll();

            for (int i = 0; i < size; i++) {
                long mul = answer * nums[i];
                priorityQueue.offer(mul);
                if (answer % nums[i] == 0) break; // n의 제곱들을 기준으로 값이 중복 카운팅
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new one2014().solution();
    }
}
