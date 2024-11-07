package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.07
 @link https://www.acmicpc.net/problem/2240
 @timecomplex
 @performance 15684kb 326ms
 @category
 @note
 */
public class four1062 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k, result;
    static String[] words;
    static boolean[] visited;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        words = new String[n];
        for (int i = 0; i < n; i++) {
            String word = reader.readLine();
            words[i] = word.substring(4, word.length() - 4);
        }

        if (k < 5) result = 0;
        else if (k == 26) result = n;
        else {
            String preAndPostFix = "antic";
            visited = new boolean[30];
            for (int i = 0; i < 5; i++) {
                visited[preAndPostFix.charAt(i) - 97] = true;
            }
            backTracking(0, 0);
        }
        builder.append(result);
        writer.write(builder.toString());
        writer.flush();
    }

    private void backTracking(int alpha, int length) {
        if (length == k - 5) {
            int count = 0;
            for(String word : words) {
                boolean signal = true;
                for (int i = 0; i < word.length(); i++) {
                    if (!visited[word.charAt(i) - 97]) {
                        signal = false;
                        break;
                    }
                }
                if (signal) count++;
            }
            result = Math.max(result, count);
            return;
        }

        for (int i = alpha; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTracking(i, length + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four1062().solution();
    }
}
