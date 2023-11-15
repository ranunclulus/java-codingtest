package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

public class three13732 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 1; test <= testNum; test++) {
            boolean isSquare = true;
            int size = sc.nextInt();
            String[][] map = new String[size][size];

            for (int i = 0; i < size; i++) {
                map[i] = sc.next().split("");
            }

            int startCol = -1;
            int startRow = -1;
            int length = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (map[i][j].equals("#")) {
                        startCol = i;
                        startRow = j;
                        break;
                    }
                }
                if (startCol != -1) break;
            }

            for (int i = startRow; i < size; i++) {
                if (map[startCol][i].equals("#")) {
                    length++;
                } else break;
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i >= startCol && i < startCol + length && j >= startRow && j < startRow + length) {
                        if (map[i][j].equals(".")) {
                            isSquare = false;
                            break;
                        }
                    } else {
                        if (map[i][j].equals("#")) {
                            isSquare = false;
                            break;
                        }
                    }
                }
                if (!isSquare) break;
            }
            System.out.printf("#%d %s\n", test, isSquare ? "yes" : "no");
        }
    }

    public static void main(String[] args) throws IOException {
        new three13732().solution();
    }
}
