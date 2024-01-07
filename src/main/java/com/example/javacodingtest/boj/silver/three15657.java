package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class three15657 {
    public int n, m;
    public int[] numbers;
    public int[] sequence;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        numbers = new int[n];
        sequence = new int[m];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }
        Arrays.sort(numbers);

        dfs(0, 0);
    }

    private void dfs(int startIndex, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                System.out.printf("%d ", sequence[i]);
            }
            System.out.println();
            return;
        }
        for (int i = startIndex; i < n; i++) {
            sequence[depth] = numbers[i];
            dfs(i, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        new three15657().solution();
    }
}
