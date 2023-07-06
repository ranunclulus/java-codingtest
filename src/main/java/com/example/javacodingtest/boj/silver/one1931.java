package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1931
public class one1931 {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int meetingCount = Integer.parseInt(reader.readLine());
        int[][] meetings = new int[meetingCount][2];
        for (int i = 0; i < meetingCount; i++) {
            StringTokenizer tokenizer =
                    new StringTokenizer(reader.readLine());
            meetings[i][0] = Integer.parseInt(tokenizer.nextToken());
            meetings[i][1] = Integer.parseInt(tokenizer.nextToken());
        }
        // 배열을 정렬한다
        // 회의 정보를 종료 시간 기준으로 정렬
        // 종료 시간이 같으면 시작 시간을 기준으로 정렬
        Arrays.sort(meetings,
                (o1, o2) -> {
            // o1은 {시작시간, 종료시간}
                    // o2도 {시작시간, 종료시간}
                    // 종료 시간이 다르다면 종료 시간 기준 비교
                    if (o1[1] != o2[1]) return o1[1] - o2[1];
                    // 아니라면 시작 시간 기준 비교
                    return o1[0] - o2[0];
                });

        // 답안을 저장하기 위한 용도
        int answer = 0;
        // 마지막 종료 시간을 저장하기 위한 용도
        int lastEnd = 0;

        for (int i = 0; i < meetingCount; i++) {
            // 이번 미팅이 선택이 가능한지 판단하기 위해
            // i 번쨰 미팅의 시작 시간과 현재의 lastEnd를 비교한다
            if(meetings[i][0] >= lastEnd) {
                answer++;
                lastEnd = meetings[i][1];
            }
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        System.out.println(new one1931().solution());
    }
}
