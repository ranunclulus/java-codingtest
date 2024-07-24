package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class two16953 {
    static int start;
    static int end;
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        start = Integer.parseInt(infoToken.nextToken());
        end = Integer.parseInt(infoToken.nextToken());
        int answer = 1;
        while (start != end) {
            if (start > end) {
                System.out.println(-1);
                return;
            }
            if (end % 2 == 0) {
                end /= 2;
            } else if (end % 10 == 1) {
                end = end / 10;
            } else {
                System.out.println(-1);
                return;
            }
            answer++;
        }
        System.out.println(answer);
    }



    public static void main(String[] args) throws IOException {
        new two16953().solution();
    }
}
