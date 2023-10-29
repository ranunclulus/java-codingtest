package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.*;

public class two15663 {
    private static int n;
    private static int m;
    private static int[] numbers;
    private static boolean[] visited;
    private static int[] sequence;
    private static LinkedHashSet<String> result;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        result = new LinkedHashSet<>();

        numbers = new int[n];
        sequence = new int[m];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);
        permutation(0);
        result.forEach(System.out::println);
    }

    private void permutation(int depth) {
        if (depth == m) {
            StringBuilder sb = new StringBuilder();
            for (int p : sequence) {
                sb.append(p).append(" ");
            }
            result.add(sb.toString());
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
        new two15663().solution();
    }
}