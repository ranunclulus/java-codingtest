package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class two2075 {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        // 우선 순위 큐 만들기
        // 최소값이 먼저 나오는 우선 순위 큐
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                priorityQueue.offer(Integer.parseInt(token.nextToken()));
                // 우선 순위 큐의 크기를 일정하게 유지
                if(priorityQueue.size() > n) {
                    // n개의 숫자만 남기고 작은 숫자를 우선 순위 큐에서 빼고 있기 때문에
                    // 최종적으로 남은 숫자는
                    // 큰 숫자 n 개
                    priorityQueue.poll();
                }
            }
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new two2075().solution());
    }
}
