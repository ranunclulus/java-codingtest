package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class four4485 {
    private int[][] adjMat;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int problemNum = 1;
        while (nodes != 0) {
            this.adjMat = new int[nodes][nodes];

            for (int i = 0; i < nodes; i++) {
                StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
                for (int j = 0; j < nodes; j++) {
                    adjMat[i][j] = Integer.parseInt(edgeToken.nextToken());
                }
            }
            for (int[] row: adjMat
                 ) {
                System.out.println(Arrays.toString(row));
            }


            nodes = Integer.parseInt(reader.readLine());
            problemNum++;
        }
    }

    public static void main(String[] args) throws IOException {
        new four4485().solution();
    }
}
