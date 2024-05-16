package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class three19185 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(infoToken.nextToken());
            int m = Integer.parseInt(infoToken.nextToken());

            String[] s = reader.readLine().split(" ");
            String[] t = reader.readLine().split(" ");

            int q = Integer.parseInt(reader.readLine());

            System.out.printf("#%d ", test);

            for (int i = 0; i < q; i++) {
                int year = Integer.parseInt(reader.readLine()) - 1;
                int sNum = year % n;
                int tNum = year % m;
                System.out.printf("%s%s ", s[sNum], t[tNum]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        new three19185().solution();
    }
}
