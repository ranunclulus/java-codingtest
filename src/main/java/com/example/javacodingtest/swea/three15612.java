package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class three15612 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            int[][] board = new int[8][8];
            int lookNum = 0;

            for (int i = 0; i < 8; i++) {
                String input = reader.readLine();
                for (int j = 0; j < 8; j++) {
                    if (input.charAt(j) == '.') board[i][j] = 1;
                    else {
                        board[i][j] = 0;
                        lookNum++;
                    }
                }
            }

            if (lookNum != 8) {
                System.out.printf("#%d %s\n", test, "no");
            } else {
                boolean signal = true;
                for (int i = 0; i < 8; i++) {
                    int colCnt = 0;
                    int rowCnt = 0;
                    for (int j = 0; j < 8; j++) {
                        if (board[i][j] == 0) rowCnt++;
                        if (board[j][i] == 0) colCnt++;
                    }

                    if (rowCnt >= 2 || colCnt >= 2) {
                        signal = false;
                        break;
                    }
                }
                System.out.printf("#%d %s\n", test, signal ? "yes" : "no");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three15612().solution();
    }
}
