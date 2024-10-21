package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.21
 @link https://www.acmicpc.net/problem/1339
 @timecomplex
 @performance 14444kb, 104ms
 @category
 @note
 */
public class four1339 {
    class Alpha {
        char alpha;
        int radix;

        public Alpha(char alpha, int radix) {
            this.alpha = alpha;
            this.radix = radix;
        }

        @Override
        public String toString() {
            return "Alpha{" +
                    "alpha=" + alpha +
                    ", radix=" + radix +
                    '}';
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static int n, answer;
    static List<Alpha> alphaList;
    static String[] inputs;
    static HashMap<Character, Integer> alphaHash;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        alphaList = new LinkedList<>();
        alphaHash = new HashMap<>();
        inputs = new String[n];

        for (int i = 0; i < n; i++) {
            inputs[i] = reader.readLine();
            char[] input = inputs[i].toCharArray();

            for (int j = 0; j < input.length; j++) {
                int r = input.length - j;

                boolean signal = false;
                for(Alpha alpha : alphaList) {
                    if (alpha.alpha == input[j]) {
                        alpha.radix += (int) Math.pow(10, r);
                        signal = true;
                    }
                }
                if (!signal) alphaList.add(new Alpha(input[j], (int) Math.pow(10, r)));
            }
        }

        Collections.sort(alphaList, new Comparator<Alpha>() {
            @Override
            public int compare(Alpha o1, Alpha o2) {
                return -Integer.compare(o1.radix, o2.radix);
            }
        });



        int num = 9;
        for (Alpha alpha : alphaList) {
            alphaHash.put(alpha.alpha, num);
            num--;
        }

        for (String input : inputs) {
            for (int i = 0; i < input.length(); i++) {
                answer += alphaHash.get(input.charAt(i)) * (int) Math.pow(10, input.length() - i - 1);
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four1339().solution();
    }
}
