package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class three19113 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(reader.readLine());
        for (int testCase = 0; testCase < test; testCase++) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> sales = new ArrayList<>();
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n * 2; i++) {
                sales.add(Integer.parseInt(infoToken.nextToken()));
            }

            int[] answer = new int[n];
            int size = 2 * n - 1;
            for (int i = 0; i < n; i++) {
                int max = sales.get(size);
                int sale = (int) (max * 0.75);
                answer[n - i - 1] = sale;
                sales.remove(size);
                sales.remove(sales.indexOf(sale));
                size -= 2;
            }
            System.out.printf("#%d ", testCase + 1);
            for (int i = 0; i < n; i++) {
                System.out.printf("%d ", answer[i]);
            }
            System.out.println();
        }
    }
}
