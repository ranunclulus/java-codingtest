package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

public class four10830 {
    private static BufferedReader reader;
    private static StringTokenizer infoToken;
    private static int n;
    private static long b;
    private static int[][] arr;
    private static int[][] result;
    private static int[][] temp;
    private static StringBuilder builder;
    private static BufferedWriter writer;
    public void solution() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        builder = new StringBuilder();
        infoToken = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(infoToken.nextToken());
        b = Long.parseLong(infoToken.nextToken());
        arr = new int[n][n];
        result = new int[n][n];
        temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(infoToken.nextToken()) % 1000;
                result[i][j] = arr[i][j];
            }
        }
        result = pow(arr, b);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                builder.append(result[i][j]).append(" ");
            }
            builder.append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private int[][] pow(int[][] arr, long depth) {
        if (depth == 1L) return arr;

        int[][] ret = pow(arr, depth / 2);
        ret = multiply(ret, ret);
        if (depth % 2 == 1L) {
            ret = multiply(ret, arr);
        }
        return ret;
    }

    private int[][] multiply(int[][] arrOne, int[][] arrTwo) {
        int[][] temp = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                for (int i = 0; i < n; i++) {
                    temp[row][col] += (arrOne[row][i] * arrTwo[i][col]);
                    temp[row][col] %= 1000;
                }
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        new four10830().solution();
    }
}
