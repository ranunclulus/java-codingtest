package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class four1764 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int hearCant = Integer.parseInt(infoToken.nextToken());
        int seeCant = Integer.parseInt(infoToken.nextToken());

        Map<String, Integer> hearAndSea = new HashMap<>();

        for (int i = 0; i < hearCant; i++) {
            hearAndSea.put(reader.readLine(), 1);
        }

        for (int i = 0; i < seeCant; i++) {
            String name = reader.readLine();
            if (hearAndSea.containsKey(name)) {
                hearAndSea.put(name, 2);
            }
        }

        List<String> answer = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : hearAndSea.entrySet()) {
            if(entry.getValue() == 2) {
                answer.add(entry.getKey());
            }
        }

        Collections.sort(answer);

        System.out.println(answer.size());
        for (String name : answer) {
            System.out.println(name);
        }
    }

    public static void main(String[] args) throws IOException {
        new four1764().solution();
    }
}
