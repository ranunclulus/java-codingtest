package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class one1283 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String[]> words = new ArrayList<>();
        boolean[] shortcut = new boolean[26];

        for (int i = 0; i <= n; i++) {
            String[] word = sc.nextLine().split(" ");
            words.add(word);
        }

        for (int i = 1; i <= n; i++) {
            boolean done = false;
            String[] word = words.get(i);
            // 첫 글자 검사
            for (int j = 0; j < word.length; j++) {
                String w = word[j];
                int ascii = calAscii(w.charAt(0));

                if (!shortcut[ascii]) {
                    shortcut[ascii] = true;
                    String temp = "[";
                    temp += w.charAt(0);
                    temp += "]";
                    temp += w.substring(1);
                    word[j] = temp;
                    done = true;
                    break;
                }
            }
            if (done) continue;

            for (int j = 0; j < word.length; j++) {
                String w = word[j];
                for (int k = 1; k < w.length(); k++) {
                    int ascii = calAscii(w.charAt(k));

                    if (ascii < 0 || ascii >= 26) continue;
                    if (shortcut[ascii]) continue;

                    shortcut[ascii] = true;
                    String temp = w.substring(0, k);
                    temp += "[";
                    temp += w.charAt(k);
                    temp += "]";
                    temp += w.substring(k + 1);
                    word[j] = temp;
                    done = true;
                    break;
                }
                if (done) break;
            }
        }

        for (int i = 1; i <= n; i++) {
            String[] word = words.get(i);
            for (String w : word) {
                System.out.printf("%s ", w);
            }
            System.out.println();
        }
    }

    public int calAscii(char ch) {
        int ascii = (int) ch;
        if (ascii >= 97) {
            ascii -= 97;
        } else {
            ascii -= 65;
        }
        return ascii;
    }

    public static void main(String[] args) throws IOException {
        new one1283().solution();
    }
}
