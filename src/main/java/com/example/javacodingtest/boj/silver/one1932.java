package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class one1932 {
    private static List<int[]> dp;
    private static List<Integer>[] triangle;
    private static int height;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        height = Integer.parseInt(reader.readLine());
        triangle = new List[height];
        dp = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            triangle[i] = new ArrayList<>();
            StringTokenizer angleToken = new StringTokenizer(reader.readLine());
            dp.add(new int[i + 1]);
            for (int j = 0; j < i + 1; j++) {
                triangle[i].add(Integer.parseInt(angleToken.nextToken()));
            }
        }

        dp.get(0)[0] = triangle[0].get(0);

        for (int i = 0; i < height - 1; i++) {
            for (int j = 0; j < i + 1 ; j++) {
                int pre = dp.get(i)[j];
                dp.get(i + 1)[j] = Math.max(dp.get(i + 1)[j], pre + triangle[i + 1].get(j));
                dp.get(i + 1)[j + 1] = Math.max(dp.get(i + 1)[j + 1], pre + triangle[i + 1].get(j + 1));
            }
        }

        int result = Integer.MIN_VALUE;

        for (int value : dp.get(height - 1)) {
            result = Math.max(result, value);
        }

        System.out.println(result);

    }

    public static void main(String[] args) throws IOException {
        new one1932().solution();
    }
}
