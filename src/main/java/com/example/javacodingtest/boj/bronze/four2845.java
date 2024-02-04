package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class four2845 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int result = Integer.parseInt(infoToken.nextToken()) * Integer.parseInt(infoToken.nextToken());

        StringTokenizer peopleToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < 5; i++) {
            int peopleNum = Integer.parseInt(peopleToken.nextToken());
            System.out.printf("%d ", peopleNum - result);
        }
    }

    public static void main(String[] args) throws IOException {
        new four2845().solution();
    }
}
