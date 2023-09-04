package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class three15650 {
    public static List<List<Integer>> generateCombinations(int n, int r) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        generateCombinationsHelper(result, combination, 1, n, r);
        return result;
    }

    private static void generateCombinationsHelper(List<List<Integer>> result, List<Integer> combination, int start, int n, int r) {
        if (r == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i <= n; i++) {
            combination.add(i);
            generateCombinationsHelper(result, combination, i + 1, n, r - 1);
            combination.remove(combination.size() - 1);
        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());

        List<List<Integer>> combinations = generateCombinations(n, m);

        StringBuilder builder = new StringBuilder();

        for (List<Integer> combination : combinations) {
            for (Integer number : combination) {
                builder.append(number);
                builder.append(" ");
            }
            builder.append('\n');
        }
        System.out.println(builder);
    }

    public static void main(String[] args) throws IOException {
        new three15650().solution();
    }
}
