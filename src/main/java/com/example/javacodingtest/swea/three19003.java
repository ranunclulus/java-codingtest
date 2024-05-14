package com.example.javacodingtest.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class three19003 {
    public static int n;
    public static int m;
    public static String[] words;
    public static int[] reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(infoToken.nextToken());
            m = Integer.parseInt(infoToken.nextToken());

            words = new String[n];
            reverse = new int[n];
            for (int i = 0; i < n; i++) {
                words[i] = reader.readLine();
            }
            checkPalindrom();
            System.out.printf("#%d %d\n", test, maxLen());
        }
    }

    public static void checkPalindrom() {
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(words[i]).reverse();
            if (words[i].equals(sb.toString())) {
                reverse[i] = 2;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (words[i].equals(checkReverse(words[j]))) {
                    reverse[i] = 1;
                }
            }
        }
    }

    private static String checkReverse(String word) {
        StringBuilder sb = new StringBuilder(word).reverse();
        return sb.toString();
    }

    public static int maxLen() {
        int ret = 0;
        int oneCnt = 0, twoCnt = 0;
        for (int i = 0; i < n; i++) {
            if (reverse[i] == 1) oneCnt++;
            else if (reverse[i] == 2) twoCnt++;
        }
        if (oneCnt != 0) {
            ret += (m * 2 * oneCnt);
        }
        if (twoCnt != 0) {
            ret += m;
        }
        return ret;
    }
}
