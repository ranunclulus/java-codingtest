package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class four1486 {
    int min = Integer.MAX_VALUE;
    int n;
    int height;
    int[] people;
    boolean[] selected;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());
        min = Integer.MAX_VALUE;
        for (int test = 1; test <= testCase; test++) {
            min = Integer.MAX_VALUE;
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(infoToken.nextToken());
            height = Integer.parseInt(infoToken.nextToken());
            people = new int[n];
            selected = new boolean[n];

            infoToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                people[i] = Integer.parseInt(infoToken.nextToken());
            }

            backTracking(0);

            System.out.printf("#%d %d\n", test, min);
        }
    }

    private void backTracking(int cnt) {
        if (cnt == n) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (selected[i]) sum += people[i];
            }
            if (sum >= height) {
                min = Math.min(min, sum - height);
            }
            return;
        }
        selected[cnt] = true;
        backTracking(cnt + 1);
        selected[cnt] = false;
        backTracking(cnt + 1);
    }


    public static void main(String[] args) throws IOException {
        new four1486().solution();
    }
}
