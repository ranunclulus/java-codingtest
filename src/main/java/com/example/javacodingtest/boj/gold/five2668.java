package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://www.acmicpc.net/problem/2668
public class five2668 {
    public int n;
    public int[] numbers;
    boolean[] checked;
    int maxSize = 0;
    List<Integer> maxList = new ArrayList<>();

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        numbers = new int[n + 1];
        checked = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }

        for (int i = 1; i <= n; i++) {
            checked[i] = true;
            dfs(i, i);
            checked[i] = false;
        }

        Collections.sort(maxList);
        System.out.println(maxList.size());
        for(int value : maxList) {
            System.out.println(value);
        }
    }

    private void dfs(int start, int target) {
        if (!checked[numbers[start]]) {
            checked[numbers[start]] = true;
            dfs(numbers[start], target);
            checked[numbers[start]] = false;
        }

        if (numbers[start] == target) {
            maxList.add(target);
        }
    }

    public static void main(String[] args) throws IOException {
        new five2668().solution();
    }
}
