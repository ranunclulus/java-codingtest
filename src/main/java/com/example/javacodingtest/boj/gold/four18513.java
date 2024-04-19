package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/18513
public class four18513 {
    class Spot {
        int position;
        long distance;

        public Spot(int position, long distance) {
            this.position = position;
            this.distance = distance;
        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(infoToken.nextToken());
        int k = Integer.parseInt(infoToken.nextToken());
        Queue<Spot> toVisit = new LinkedList<>();
        Set<Integer> visited = new HashSet<Integer>();
        int[] dx = new int[]{-1, 1};

        infoToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            int oasis = Integer.parseInt(infoToken.nextToken());
            visited.add(oasis);
            toVisit.add(new Spot(oasis, 0));
        }

        long distance = 0;
        while (!toVisit.isEmpty()) {
            if (k <= 0) break;
            Spot now = toVisit.poll();

            for (int i = 0; i < 2; i++) {
                int nx = now.position + dx[i];
                if (visited.contains(nx)) continue;
                distance += (now.distance + 1);
                k--;
                visited.add(nx);
                if (k <= 0) break;
                toVisit.add(new Spot(nx, now.distance + 1));
            }

        }

        System.out.println(distance);

    }

    public static void main(String[] args) throws IOException {
        new four18513().solution();
    }
}
