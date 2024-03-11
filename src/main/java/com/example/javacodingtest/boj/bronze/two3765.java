package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class two3765 {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = br.readLine();
            if (s == null) break;
            sb.append(s).append('\n');
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new two3765().solution();
    }
}
