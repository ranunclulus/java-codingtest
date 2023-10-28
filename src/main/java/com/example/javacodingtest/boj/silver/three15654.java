package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.*;

public class three15654 {
    private static int n;
    private static int m;
    private static int[] numbers;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();
    private static int[] sequence;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        numbers = new int[n];
        sequence = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);
        permutation(0);

        System.out.println(sb.toString());
    }

    private void permutation(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(sequence[i]).append(" ");
            }
            sb.append('\n');
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[depth] = numbers[i];
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three15654().solution();
    }
}
