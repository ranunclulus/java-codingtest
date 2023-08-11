package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18111
public class two18111 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());
        int b = Integer.parseInt(infoToken.nextToken());

        int[][] map = new int[n][m];
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            StringTokenizer rowToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(rowToken.nextToken());
                minVal = Math.min(minVal, map[i][j]);
                maxVal = Math.max(maxVal, map[i][j]);
            }
        }

        int result = Integer.MAX_VALUE;
        int high = -1;

        for (int height = minVal; height <= maxVal; height++) {

            int timeValue = 0;
            int inventory = b;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int diff = map[i][j] - height;
                    // 맵에서 뺴야 한다면
                    if (diff > 0) {
                        timeValue += Math.abs(diff) * 2;
                        inventory += Math.abs(diff);
                    }
                    // 맵에 더해야 한다면
                    else if (diff < 0) {
                        timeValue += Math.abs(diff);
                        inventory -= Math.abs(diff);
                    }
                }
            }

            if (inventory >= 0) {
                if(timeValue <= result) {
                    result = timeValue;
                    high = height;
                }
            }
        }
        System.out.printf("%d %d", result, high);
    }

    public static void main(String[] args) throws IOException {
        new two18111().solution();
    }
}
