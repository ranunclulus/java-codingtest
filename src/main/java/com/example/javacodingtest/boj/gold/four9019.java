package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class four9019 {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        for (int test = 0; test < testCase; test++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(infoToken.nextToken());
            int end = Integer.parseInt(infoToken.nextToken());
            String[] command = new String[10000];
            boolean[] visited = new boolean[10000];
            Queue<Integer> toVisit = new LinkedList<>();

            toVisit.add(start);
            visited[start] = true;
            Arrays.fill(command, "");

            while (!toVisit.isEmpty() && !visited[end]) {
                int now = toVisit.poll();
                int D = (now * 2) % 10000;
                int S = (now == 0) ? 9999 : now - 1;
                int L = (now % 1000) * 10 + now / 1000;
                int R = (now % 10) * 1000 + now / 10;

                if (!visited[D]) {
                    toVisit.add(D);
                    visited[D] = true;
                    command[D] = command[now] + "D";
                }
                if (!visited[S]) {
                    toVisit.add(S);
                    visited[S] = true;
                    command[S] = command[now] + "S";
                }
                if (!visited[L]) {
                    toVisit.add(L);
                    visited[L] = true;
                    command[L] = command[now] + "L";
                }
                if (!visited[R]) {
                    toVisit.add(R);
                    visited[R] = true;
                    command[R] = command[now] + "R";
                }
            }
            System.out.println(command[end]);
        }
    }


    public static void main(String[] args) throws IOException {
        new four9019().solution();
    }
}
