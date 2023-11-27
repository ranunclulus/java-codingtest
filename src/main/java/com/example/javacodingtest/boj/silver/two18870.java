package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

// https://www.acmicpc.net/problem/18870
public class two18870 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] origin = new int[N];
        int[] sorted = new int[N];
        HashMap<Integer, Integer> rankingMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < N; i++) {
            sorted[i] = origin[i] = sc.nextInt();
        }

        Arrays.sort(sorted);

        int rank = 0;
        for (int v : sorted) {
            if (!rankingMap.containsKey(v)) {
                rankingMap.put(v, rank);
                rank++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int key : origin) {
            int ranking = rankingMap.get(key);
            sb.append(ranking).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        new two18870().solution();
    }
}
