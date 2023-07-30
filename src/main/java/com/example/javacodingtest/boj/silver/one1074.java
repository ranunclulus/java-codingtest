package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1074
public class one1074 {
    static int row;
    static int col;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = (int) Math.pow(2, Integer.parseInt(infoToken.nextToken()));
        row = Integer.parseInt(infoToken.nextToken());
        col = Integer.parseInt(infoToken.nextToken());
        int count = 0;
        int nRow = 0;
        int nCol = 0;
        while(n > 0) {
            n /= 2;
            if (row < nRow + n && col < nCol + n) count += 0;
            else if (row < nRow + n) {
                nCol += n;
                count += n * n;
            }
            else if (col < nCol + n) {
                nRow += n;
                count += 2 * n * n;
            }
            else {
                nRow += n;
                nCol += n;
                count += 3 * n * n;
            }
            if (n == 1) {
                System.out.println(count);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new one1074().solution();
    }
}
