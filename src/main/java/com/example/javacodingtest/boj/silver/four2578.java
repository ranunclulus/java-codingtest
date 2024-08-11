package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class four2578 {
    static int[][] bingo;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        bingo = new int[5][5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 5; j++) {
                bingo[i][j] = Integer.parseInt(infoToken.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 5; j++) {
                int value = Integer.parseInt(infoToken.nextToken());

                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (bingo[k][l] == value) {
                            bingo[k][l] = -1;

                            int bingoNum = calBingo();
                            if (bingoNum >= 3) {
                                System.out.println((5 * i) + j + 1);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private int calBingo() {
        int answer = 0;

        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (bingo[i][j] == -1) {
                    count++;
                }
            }
            if (count == 5) answer++;
        }

        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (bingo[j][i] == -1) {
                    count++;
                }
            }
            if (count == 5) answer++;
        }

        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (bingo[i][i] == -1) {
                count++;
            }
        }
        if (count == 5) answer++;

        count = 0;
        for (int i = 0; i < 5; i++) {
            if (bingo[4 - i][i] == -1) {
                count++;
            }
        }
        if (count == 5) answer++;

        return answer;
    }

    public static void main(String[] args) throws IOException {
        new four2578().solution();
    }
}
