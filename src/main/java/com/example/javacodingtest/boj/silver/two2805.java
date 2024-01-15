package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class two2805 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] tree = new int[n];
        int low = 0;
        int high = 0;
        for (int i = 0; i < n; i++) {
            tree[i] = sc.nextInt();
            if (high < tree[i]) high = tree[i];
        }
        while (low < high) {
            int height = (low + high) / 2;
            long remain = 0;
            for (int treeHeight : tree) {
                if (treeHeight - height > 0) remain += (treeHeight - height);
            }

            if (remain < m) high = height;
            else low = height + 1;
        }
        System.out.println(low - 1);
    }

    public static void main(String[] args) throws IOException {
        new two2805().solution();
    }
}
