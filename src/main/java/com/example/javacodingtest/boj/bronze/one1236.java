package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class one1236 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int colNum = Integer.parseInt(infoToken.nextToken());
        int rowNum = Integer.parseInt(infoToken.nextToken());

        char[][] map = new char[colNum][rowNum];
        boolean[] col = new boolean[colNum];
        boolean[] row = new boolean[rowNum];

        for (int i = 0; i < colNum; i++) {
            String input = reader.readLine();
            for (int j = 0; j < rowNum; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'X') {
                    col[i] = true;
                    row[j] = true;
                }
            }
        }

        int result = 0;

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                if (!col[i] && !row[j]) {
                    col[i] = true;
                    row[j] = true;
                    result++;
                }
            }
        }

        for (int i = 0; i < colNum; i++) {
            if (!col[i]) result++;
        }

        for (int i = 0; i < rowNum; i++) {
            if (!row[i]) result++;
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new one1236().solution();
    }
}
