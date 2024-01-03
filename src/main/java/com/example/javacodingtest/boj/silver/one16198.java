package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class one16198 {
    public int n;
    public int max = Integer.MIN_VALUE;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        List<Integer> weighted = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            weighted.add(sc.nextInt());
        }

        dfs(weighted, 0);

        System.out.println(max);
    }

    private void dfs(List<Integer> weighted, int sum) {
        if (weighted.size() <= 2) {
            max = Math.max(max, sum);
        }

        for (int i = 1; i < weighted.size() - 1; i++) {
            int temp = weighted.get(i);
            int addEnergy = (weighted.get(i - 1) * weighted.get(i + 1));
            weighted.remove(i);
            dfs(weighted, sum + addEnergy);
            weighted.add(i, temp);
        }
    }


    public static void main(String[] args) throws IOException {
        new one16198().solution();
    }
}
