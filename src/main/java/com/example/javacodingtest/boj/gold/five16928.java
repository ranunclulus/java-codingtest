package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/16928
public class five16928 {
    int[] count = new int[101];
    int[] ladderAndSnake = new int[101];
    boolean[] visited = new boolean[101];

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int ladderNum = Integer.parseInt(infoToken.nextToken());
        int snakeNum = Integer.parseInt(infoToken.nextToken());

        for (int i = 0; i < ladderNum + snakeNum; i++) {
            StringTokenizer jumpToken = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(jumpToken.nextToken());
            int end = Integer.parseInt(jumpToken.nextToken());
            ladderAndSnake[start] = end;
        }

        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.offer(1);
        count[1] = 0;
        visited[1] = true;

        while (!toVisit.isEmpty()) {
            int now = toVisit.poll();

            if (now == 100) {
                System.out.println(count[100]);
                break;
            }

            for (int i = 1; i < 7; i++) {
                int next = now + i;
                if (next > 100) continue;
                if (visited[next]) continue;
                visited[next] = true;

                if (ladderAndSnake[next] != 0) {
                    if (!visited[ladderAndSnake[next]]) {
                        toVisit.add(ladderAndSnake[next]);
                        count[ladderAndSnake[next]] = count[now] + 1;
                        visited[ladderAndSnake[next]] = true;
                    }
                } else {
                    toVisit.add(next);
                    count[next] = count[now] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five16928().solution();
    }
}
