package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://www.acmicpc.net/problem/1920
public class four1920 {
    private List<Integer> numbers;
    public void solution() throws IOException {
        BufferedReader readear = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(readear.readLine());
        numbers = new ArrayList<>();
        StringTokenizer numberToken = new StringTokenizer(readear.readLine());
        for (int i = 0; i < n; i++) {
            numbers.add(Integer.parseInt(numberToken.nextToken()));
        }
        Collections.sort(numbers);

        int k = Integer.parseInt(readear.readLine());
        StringTokenizer searchToken = new StringTokenizer(readear.readLine());
        for (int i = 0; i < k; i++) {
            if (search(0, n - 1, Integer.parseInt(searchToken.nextToken()))) System.out.println(1);
            else System.out.println(0);
        }
    }

    private boolean search(int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) / 2;
            int midVal = numbers.get(mid);
            if (midVal == target) return true;
            else if (midVal < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        new four1920().solution();
    }
}
