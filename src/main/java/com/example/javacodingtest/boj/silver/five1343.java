package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class five1343 {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        String[] inputs = input.split("\\.");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < inputs.length; i++) {
            String x = inputs[i];
            if (x.length() % 2 != 0) {
                sb = new StringBuilder("-1");
                break;
            } else if (x.length() % 4 == 0) {
                sb.append("AAAA".repeat(x.length() / 4));
            } else {
                sb.append("AAAA".repeat(x.length() / 4)).append("BB");
            }

            if (i != inputs.length - 1) {
                sb.append(".");
            }
        }

        String result = sb.toString();
        if (!result.equals("-1")) {
            sb.append(".".repeat(input.length() - result.length()));
        }

        System.out.println(sb);
    }


    public static void main(String[] args) throws IOException {
        new five1343().solution();
    }
}
