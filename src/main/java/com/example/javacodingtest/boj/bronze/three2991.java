package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class three2991 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        //배달원은 3명이기에 3번 반복한다.
        for (int i = 0; i < 3; i++) {
            int N = Integer.parseInt(st.nextToken());
            //첫번째 개와 두번째 개한테서의 배달원 시간을 저장
            int Fdog = N % (A + B);
            int Sdog = N % (C + D);
            int count = 0;

            //각 시간이 두 개의 공격 시간에 포함되는지를 확인
            if (Fdog <= A && Sdog <= C) {
                count = 2;
            } else if (Fdog <= A || Sdog <= C) {
                count = 1;
            }

            //나머지가 0이란 것은 개의 쉬는 시간에 포함되는 것이기에 나온 count값에 -1을 해준다.
            if (Fdog == 0) {
                count -= 1;
            }
            if (Sdog == 0) {
                count -= 1;
            }

            System.out.println(count);
        }
    }

}
