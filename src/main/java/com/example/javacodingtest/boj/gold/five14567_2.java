package com.example.javacodingtest.boj.gold;

import java.util.*;
import java.io.*;

/*
 @author ranuinclulus
 @since 2025.03.04
 @link https://www.acmicpc.net/problem/14567
 @timecomplex 128036KB 556MS
 @performance
 @category
 @note
 */
public class five14567_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int N, M;
    static int[] income, semesters;
    static List<List<Integer>> adjList = new ArrayList<>();

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        income = new int[N + 1];
        semesters = new int[N + 1];

        for(int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            adjList.get(a).add(b);
            income[b]++;
        }

        Deque<int[]> toVisit = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if (income[i] == 0) {
                toVisit.offer(new int[] {i, 1});
            }
        }

        while(!toVisit.isEmpty()) {
            int[] lecture = toVisit.poll();
            semesters[lecture[0]] = lecture[1];
            for(int next : adjList.get(lecture[0])) {
                income[next]--;
                if (income[next] == 0) toVisit.offer(new int[] {next, lecture[1] + 1});
            }
        }

        for(int i = 1; i <= N; i++) {
            builder.append(semesters[i]).append(" ");
        }

        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five14567_2().solution();
    }
}
