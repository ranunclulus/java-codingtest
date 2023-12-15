package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class four5568 {
    private int n;
    private int k;
    private int[] numbers;
    private int[] arr;
    private boolean[] visited;
    private Map<String, Integer> candidates;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        numbers = new int[n];
        visited = new boolean[n];
        arr = new int[k];
        candidates = new HashMap<>();
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        permutation(0);
        
        System.out.println(candidates.keySet().size());
    }

    private void permutation(int depth) {
        if (depth == k) {
            String atrArr = "";
            for (int val : arr) {
                atrArr += val;
            }
            if (!candidates.containsKey(atrArr)) {
                candidates.put(atrArr, 0);
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = numbers[i];
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four5568().solution();
    }
}
