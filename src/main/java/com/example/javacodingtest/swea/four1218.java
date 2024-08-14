package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.14
 @link
 @timecomplex
 @performance 18496 KB 119 MS
 @category #while
 @note
 */
public class four1218 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public void solution() throws IOException {
        for (int test = 1; test <= 10; test++) {
            sb.append("#").append(test).append(" ");
            int size = Integer.parseInt(br.readLine());

            if (size % 2 == 1) {
                String input = br.readLine();
                sb.append("0").append('\n');
            } else {
                String input = br.readLine();
                int[] braces = new int[4];

                boolean signal = true;
                for (int i = 0; i < input.length(); i++) {
                    char cur = input.charAt(i);
                    switch (cur) {
                        case '(':
                            braces[0]++;
                            break;
                        case '[':
                            braces[1]++;
                            break;
                        case '{':
                            braces[2]++;
                            break;
                        case '<':
                            braces[3]++;
                            break;
                        case ')':
                            braces[0]--;
                            break;
                        case ']':
                            braces[1]--;
                            break;
                        case '}':
                            braces[2]--;
                            break;
                        case '>':
                            braces[3]--;
                            break;
                    }
                    for (int cnt : braces) {
                        if (cnt < 0) {
                            signal = false;
                            break;
                        }
                    }
                    if (!signal) {
                        sb.append("0").append('\n');
                        break;
                    }
                }
                if (signal) sb.append("1").append('\n');
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new four1218().solution();
    }
}
