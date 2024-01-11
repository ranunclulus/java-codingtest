package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class four17218 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> password = new HashMap<>();

        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());

        for (int i = 0; i < n; i++) {
            StringTokenizer passwordToken = new StringTokenizer(reader.readLine());
            password.put(passwordToken.nextToken(), passwordToken.nextToken());
        }

        for (int i = 0; i < m; i++) {
            String site = reader.readLine();
            System.out.println(password.get(site));
        }
    }

    public static void main(String[] args) throws IOException {
        new four17218().solution();
    }
}
