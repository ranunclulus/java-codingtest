package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class two2961 {
    private int n;
    private int[][] taste;
    private int result = Integer.MAX_VALUE;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        taste = new int[n][2];

        for (int i = 0; i < n; i++) {
            taste[i][0] = sc.nextInt();
            taste[i][1] = sc.nextInt();
        }

        makeFood(0, 0, 1, 0);
        System.out.println(result);
    }

    private void makeFood(int ingredient, int count, long sour, long bitter) {
        if (count == n) {
            if (ingredient != 0) {
                result = (int) Math.min(result, Math.abs(sour - bitter));
            }
            return;
        }

        makeFood(ingredient, count + 1, sour, bitter);
        makeFood(ingredient + 1, count + 1, sour * taste[count][0], bitter + taste[count][1]);
    }

    public static void main(String[] args) throws IOException {
        new two2961().solution();
    }
}
