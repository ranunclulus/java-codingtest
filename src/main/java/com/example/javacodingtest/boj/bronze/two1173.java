package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class two1173 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(infoToken.nextToken());
        int m = Integer.parseInt(infoToken.nextToken());
        int M = Integer.parseInt(infoToken.nextToken());
        int T = Integer.parseInt(infoToken.nextToken());
        int R = Integer.parseInt(infoToken.nextToken());
        int current = m;
        int time = 0;
        int index = 0;

        if (m + T > M) {
            System.out.println(-1);
            return;
        }

        while (time < N) {
            index++;
            if (current + T <= M) {
                current += T;
                time++;
            } else {
                if (current - R >= m) {
                    current -= R;
                } else {
                    current = m;
                }
            }
        }
        System.out.println(index);

    }

    public static void main(String[] args) throws IOException {
        new two1173().solution();
    }
}

