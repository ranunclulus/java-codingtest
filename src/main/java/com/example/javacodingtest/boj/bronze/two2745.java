package com.example.javacodingtest.boj.bronze;

import java.io.*;
import java.util.StringTokenizer;

public class two2745 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());
        br.close();

        int tmp = 1;
        int sum = 0;

        for(int i = N.length()-1 ; i >= 0; i--){ // 여기서, 맨오른쪽 부터 계산!
            char C = N.charAt(i);

            if ('A' <= C && C<= 'Z') {
                sum += (C - 'A' + 10) * tmp;
            } else {
                sum += (C - '0') * tmp;
            }
            tmp *= B;
        }

        System.out.println(sum);
    }
}
