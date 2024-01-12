package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class three9375 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        for (int test = 0; test < testCase; test++) {
            int total = Integer.parseInt(reader.readLine());
            HashMap<String, Integer> items = new HashMap<>();

            for (int i = 0; i < total; i++) {
                StringTokenizer itemToken = new StringTokenizer(reader.readLine());
                String itemName = itemToken.nextToken();
                String itemType = itemToken.nextToken();

                if (items.containsKey(itemType)) {
                    items.put(itemType, items.get(itemType) + 1);
                } else {
                    items.put(itemType, 1);
                }
            }

            int result = 1;
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                result *= (entry.getValue() + 1);
            }
            System.out.println(result - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        new three9375().solution();
    }
}
