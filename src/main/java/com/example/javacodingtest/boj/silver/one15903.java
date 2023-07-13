package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15903
public class one15903 {
    public long solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int cardCount = Integer.parseInt(infoToken.nextToken());
        int actions = Integer.parseInt(infoToken.nextToken());
        StringTokenizer cardToken = new StringTokenizer(reader.readLine());
        // 우선 순위 큐에 넣어 주기
        PriorityQueue<Long> smallestCard = new PriorityQueue<>();
        for (int i = 0; i < cardCount; i++) {
            smallestCard.offer(Long.parseLong(cardToken.nextToken()));
        }

        // 두 개의 숫자를 뽑아서 합한 뒤
        // 다시 우선 순위 큐에 두 번 넣기
        for (int i = 0; i < actions; i++) {
            // TODO
            long x = smallestCard.poll();
            long y = smallestCard.poll();
            smallestCard.offer(x + y);
            smallestCard.offer(x + y);
        }
        long answer = 0;
        for (int i = 0; i < cardCount; i++) {
            answer += smallestCard.poll();
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new one15903().solution());
    }
}
