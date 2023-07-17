package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1417
public class five1417 {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // 입력부
        int candidates = Integer.parseInt(reader.readLine());
        // 내 득표수
        int myVote = Integer.parseInt(reader.readLine());
        // 제알 많은 득표자의 표를 먼저 빼야 하니까
        PriorityQueue<Integer> otherVotes = new PriorityQueue<>(Collections.reverseOrder()); // or (Comparator.comparingInt(o -> o)
        // 다솜이 거 빼고 나머지
        for (int i = 0; i < candidates - 1; i++) {
            otherVotes.offer(Integer.parseInt(reader.readLine()));
        }
        // 정답
        int answer = 0;

        // 알고리즘
        // 단독 후보가 아닐 떄
        if (!otherVotes.isEmpty()) {
            // 나머지 후보들 득표 중 최대가 나보다 작아질 때까지
            while (otherVotes.peek() >= myVote) {
                // 최다 득표자의 득표수
                int votes = otherVotes.poll();
                // 그 사람의 지지자를 한 명 매수
                otherVotes.offer(votes - 1);
                // 빼앗은 표는 내 거
                myVote++;
                // 매수 진행 횟수
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new five1417().solution());
    }
}
