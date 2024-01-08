package com.example.javacodingtest.boj.gold;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class four12851 {
    public int n, k, time, cnt;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        bfs();
        System.out.println(time);
        System.out.println(cnt);
    }

    private void bfs() {
        int[] visit = new int[100001];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(n, 0));
        visit[n] = 1;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.x == k) {
                if (cnt == 0) time = now.y;
                if (time == now.y) cnt++;
            }

            int[] nextPosition = {now.x - 1, now.x + 1, now.x * 2};
            for (int next : nextPosition) {
                if (next < 0 || next > 100000) continue;
                if (visit[next] == 0 || visit[next] == now.y + 1) {
                    visit[next] = now.y + 1;
                    queue.offer(new Point(next, now.y + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four12851().solution();
    }
}
