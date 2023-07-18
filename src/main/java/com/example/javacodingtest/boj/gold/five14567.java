package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://www.acmicpc.net/problem/14567
public class five14567 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int lectures = Integer.parseInt(infoToken.nextToken());
        int preReqs = Integer.parseInt(infoToken.nextToken());

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < lectures + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < preReqs; i++) {
            StringTokenizer reqToken = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(reqToken.nextToken());
            int end = Integer.parseInt(reqToken.nextToken());
            adjList.get(start).add(end);
        }

        // 진입 차수 정리
        int[] inDegrees = new int[lectures + 1];
        for(List<Integer> neighbors: adjList) {
            for(int neighbor: neighbors) {
                inDegrees[neighbor]++;
            }
        }
        // 진입 차수에 따른 시작 정점 결정
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 1; i < lectures + 1; i++) {
            if(inDegrees[i] == 0) queue.add(new int[] {i, 1});
        }
        // Queue를 가지고 순회
        // Queue에 담기는 시점에 방문하고 있던 정점의 소요 시간 정보 기록
        int[] firstSam = new int[lectures + 1];
        while(!queue.isEmpty()) {
            int[] lecture = queue.poll();
            int node = lecture[0];
            int semester = lecture[1];
            firstSam[node] = semester;

            for(int nextLec:adjList.get(node)) {
                inDegrees[nextLec]--;
                if(inDegrees[nextLec] == 0) {
                    queue.offer(new int[] {nextLec, semester + 1});
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 1; i < lectures + 1; i++) {
            answer.append(firstSam[i]).append(' ');
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new five14567().solution();
    }
}
