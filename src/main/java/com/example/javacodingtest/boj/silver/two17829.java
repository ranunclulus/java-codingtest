package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17829
public class two17829 {
    private int[][] matrix;
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        return pooling(size, 0, 0);
    }

    public int pooling(int n, int x, int y) {
        // 재귀 호출 하지 않고 계산한 결과를 반환
        if (n == 2) {
            int[] four = new int[] {
                    matrix[y][x],
                    matrix[y + 1][x],
                    matrix[y][x + 1],
                    matrix[y + 1][x + 1]
            };
            Arrays.sort(four);
            return four[2];
        }
        // 재귀 호출
        else {
            int half = n / 2;
            int [] four = new int[] {
                    pooling(half, x, y),
                    pooling(half, x + half, y),
                    pooling(half, x, y + half),
                    pooling(half, x + half, y + half)
            };
            Arrays.sort(four);
            return four[2];
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new two17829().solution());
    }
}
