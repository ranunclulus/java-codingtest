package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class five10867 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());

        Set<Integer> numbers = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        for (int i = 0; i < n; i++) {
            numbers.add(Integer.parseInt(infoToken.nextToken()));
        }

        for (int num : numbers) {
            System.out.printf("%d ", num);
        }
    }

    public static void main(String[] args) throws IOException {
        new five10867().solution();
    }

}
