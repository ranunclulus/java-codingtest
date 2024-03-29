package com.example.javacodingtest.codingtest.windmill;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class WaterTankInstall {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[][] channels = new int[n][n];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer channelToken = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(channelToken.nextToken()) - 1;
            int end = Integer.parseInt(channelToken.nextToken()) - 1;
            channels[start][end] = 1;
        }

        for (int i = 0; i < n; i++) {
            boolean[] watered = new boolean[n];
            Queue<Integer> toVisit = new LinkedList<>();
            toVisit.add(i);
            watered[i] = true;

            while (!toVisit.isEmpty()) {
                int start = toVisit.poll();
                for (int j = 0; j < n; j++) {
                    if (channels[start][j] == 1) {
                        if (!watered[j]) {
                            toVisit.add(j);
                            watered[j] = true;
                        }
                    }
                }
            }

            boolean signal = true;
            for (int j = 0; j < n; j++) {
                if (!watered[j]) signal = false;
            }

            if (signal) {
                System.out.println(i + 1);
                return;
            }
        }
    }
}
