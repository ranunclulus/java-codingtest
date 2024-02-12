package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class four1302 {
    public void solution() throws IOException {
        HashMap<String, Integer> bookSale = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sales = Integer.parseInt(reader.readLine());
        for (int i = 0; i < sales; i++) {
            String book = reader.readLine();
            if (!bookSale.containsKey(book)) {
                bookSale.put(book, 1);
            } else {
                bookSale.put(book, bookSale.get(book) + 1);
            }
        }

        int maxCount = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : bookSale.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }

        String bestSeller = "";
        for (Map.Entry<String, Integer> entry : bookSale.entrySet()) {
            if (entry.getValue() == maxCount) {
                if (bestSeller.equals("")) {
                    bestSeller = entry.getKey();
                } else {
                    int compare = bestSeller.compareTo(entry.getKey());
                    if (compare > 0) {
                        bestSeller = entry.getKey();
                    }
                }
            }
        }
        System.out.println(bestSeller);

    }

    public static void main(String[] args) throws IOException {
        new four1302().solution();
    }
}
