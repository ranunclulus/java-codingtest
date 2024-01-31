package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class three9536 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(reader.readLine());

        for (int test = 0; test < testCase; test++) {
            String[] sounds = reader.readLine().split(" ");
            List<String> animalSounds = new ArrayList<>();
            while (true) {
                String input = reader.readLine();

                if (input.equals("what does the fox say?")) break;

                StringTokenizer soundsToken = new StringTokenizer(input);
                soundsToken.nextToken();
                soundsToken.nextToken();
                String valueStr = soundsToken.nextToken();
                animalSounds.add(valueStr);
            }

            for (String sound : sounds) {
                if (!animalSounds.contains(sound)) {
                    System.out.printf("%s ", sound);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three9536().solution();
    }
}
