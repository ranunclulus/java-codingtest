package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/5904
public class gold5904 {
    public char solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        // 최초의 수열의 길이는 3
        int length = 3;
        // 목표는 반복해서 수열을 만들어 length 가 n 보다 커지게 만들면서
        // length 가 moo 수열의 길이 만한 값으로 유지되기

        // reps == k
        int reps = 0;

        // while 문의 조건도 동일하게 가져간다.
        while (length < n) {
            // 처음 만드는 수열은 S(1)이니
            // 증가시키고 시작
            reps++;
            // len(S(k - 1)) * 2 + ((k + 2) * 'o' + 'm')
            length = length * 2 + (reps + 3);
//            System.out.println(length);
        }

        // reps(k)와 length 의 정보가 있다면,
        // moo 수열의 구역을 3단위로 나눌 수 있다.
        // 좌우대칭 앞과 뒤, + reps + 3 으로 이뤄진 moo...o
        // 가운데 구간에 n이 위치한다면 정확하게 어떤 글자인지 판단할 수 있다.
        // 앞쪽 또는 뒤쪽이라면, 구간을 줄여서 다시 3등분,
        // 반복해서 가운데에 위치할 때까지 진행한다.
        // 인덱스 기준으로 찾을거니, n의 값을 사전 조정 해준다.
        n--;
        while (true) {
            // 먼저 가운데 인덱스의 위치를 찾는다.
            int midIndex = (length - (reps + 3)) / 2;
            // 그리고 끝 구간의 시작 인덱스를 찾는다.
            // 가운데 시작 인덱스 부터 가운데 구간 길이 합
            int lastIndex = (length - (reps + 3)) / 2 + (reps + 3);
            // 만약 n == midIndex 라면, 가운데 구간의 시작
            // 구간의 시작이면 m 이다
            if (n == midIndex) return 'm';
                // 시작은 아니지만, 가운데 구간이면 o 다
            else if (midIndex < n && n < lastIndex) return 'o';
                // 아니라면, 길이를 줄여서 다시 풀어본다.
            else if (n >= lastIndex) {
                // 버리는 길이만큼 n 과 length 조정
                n -= lastIndex;
                length -= lastIndex;
            } else {
                // 가운데 구간의 길이와
                length -= reps + 3;
                // 가운데 구간까지의 길이를 둘다 뺀다.
                length -= midIndex;
                // n은 조정 불필요
            }
            reps--;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new gold5904().solution());
    }
}
