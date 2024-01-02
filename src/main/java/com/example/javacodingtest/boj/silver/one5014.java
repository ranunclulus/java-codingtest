package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class one5014 {
    public int f, s, g, u, d;
    public boolean[] visited;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        f = sc.nextInt();
        s = sc.nextInt();
        g = sc.nextInt();
        u = sc.nextInt();
        d = sc.nextInt();

        visited = new boolean[f + 1];

        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{s, 0});
        boolean moreToSearch = true;
        visited[s] = true;
        while (!toVisit.isEmpty()) {
            int[] nowPosition = toVisit.poll();
            int now = nowPosition[0];
            if (now == g) {
                moreToSearch = false;
                System.out.println(nowPosition[1]);
                break;
            }

            int up = now + u;
            int down = now - d;
            if (up <= f && up >= 1 && !visited[up]) {
                toVisit.add(new int[]{up, nowPosition[1] + 1});
                visited[up] = true;
            }
            if (down >= 1 && down <= f && !visited[down]) {
                toVisit.add(new int[]{down, nowPosition[1] + 1});
                visited[down] = true;
            }
        }

        if (moreToSearch) {
            System.out.println("use the stairs");
        }
    }

    public static void main(String[] args) throws IOException {
        new one5014().solution();
    }
}
