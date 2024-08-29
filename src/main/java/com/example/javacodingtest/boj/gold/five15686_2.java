package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.29
 @link https://www.acmicpc.net/problem/1568
 @timecomplex
 @performance 17268 KB, 204 MS
 @category
 @note
 */
public class five15686_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static List<int[]> houses;
    static List<int[]> chickens;
    static boolean[] visited;
    static int houseNum;
    static int chickenNum;
    static long minDistance = Long.MAX_VALUE;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        houses = new LinkedList<>();
        chickens = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) {
                    houses.add(new int[] {i, j});
                    houseNum++;
                }
                if (val == 2) {
                    chickens.add(new int[] {i, j});
                    chickenNum++;
                }
            }
        }

        visited = new boolean[chickenNum];
        makeCombination(0, 0);

        sb.append(minDistance);
        bw.write(sb.toString());
        bw.flush();
    }

    private void makeCombination(int depth, int start) {
        if (depth == m) {
            calculateDistances();
            return;
        }
        if (start >= chickenNum) return;

        visited[start] = true;
        makeCombination(depth + 1, start + 1);
        visited[start] = false;
        makeCombination(depth, start + 1);
    }

    private void calculateDistances() {
        long sum = 0L;
        for(int[] house : houses) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < chickenNum; i++) {
                if (!visited[i]) continue;
                int[] chicken = chickens.get(i);
                int distance = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
                min = Math.min(min, distance);
            }
            sum += min;
        }
        minDistance = Math.min(minDistance, sum);
    }

    public static void main(String[] args) throws IOException {
        new five15686_2().solution();
    }
}
