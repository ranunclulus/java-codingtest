package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class four14938 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken()); // 지역의 개수
        int m = Integer.parseInt(infoToken.nextToken()); // 수색 범위
        int r = Integer.parseInt(infoToken.nextToken()); // 길의 개수

        int[] items = new int[n]; // 각 지역에 아이템이 몇 개 있는지
        infoToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(infoToken.nextToken());
        }

        int[][] roads = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) roads[i][j] = 0;
                else roads[i][j] = 100000000;
            }
        }
        for (int i = 0; i < r; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            int one = Integer.parseInt(infoToken.nextToken()) - 1;
            int two = Integer.parseInt(infoToken.nextToken()) - 1;
            int weight = Integer.parseInt(infoToken.nextToken());
            roads[one][two] = weight;
            roads[two][one] = weight;
        }

        for (int k = 0; k < n; k++) { // k: 거쳐 가는 노드 개수
            for (int i = 0; i < n; i++) { // 노드 i에서 j로 가는 경우
                for (int j = 0; j < n; j++) {
                    roads[i][j] = Math.min(roads[i][j], roads[i][k] + roads[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (roads[i][j] > m) roads[i][j] = 0;
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int sum = items[i];
            for (int j = 0; j < n; j++) {
                if (roads[i][j] > 0) sum += items[j];
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        new four14938().solution();
    }

}
