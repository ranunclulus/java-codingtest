package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class five13549 {
    private int n;
    private int k;
    private int[] distance = new int[200001];

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        LinkedList<Integer> toVisit = new LinkedList<>();

        Arrays.fill(distance, -1);
        distance[n] = 0;
        toVisit.add(n);

        while (!toVisit.isEmpty()) {
            int now = toVisit.poll();
            if (now == k) {
                System.out.println(distance[k]);
                break;
            }

            if (now * 2 <= 100000 && distance[now * 2] == -1) {
                toVisit.addFirst(now * 2);
                distance[now * 2] = distance[now];
            }

            for (int next : new int[]{now - 1, now + 1}) {
                if (next < 0 || next >= 100000 || distance[next] != -1) continue;
                toVisit.addLast(next);
                distance[next] = distance[now] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five13549().solution();
    }
}
