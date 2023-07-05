package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11047
public class four11047 {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer info = new StringTokenizer(reader.readLine());
        // 동전의 종류
        int coinKinds = Integer.parseInt(info.nextToken());
        // 만들고자 하는 금액
        int targetAmount = Integer.parseInt(info.nextToken());
        // 개별 동전 가치 저장
        int[] coinAmounts = new int[coinKinds];

        for (int i = 0; i < coinKinds; i++) {
            int coinAmount = Integer.parseInt(reader.readLine());
            // 큰 동전을 0에 두기 위해 역손으로 저장
            coinAmounts[coinKinds - i - 1] = coinAmount;
        }

        // 실제로 사용한 동전의 개수
        int coinUsed = 0;
        /*
        // 1. 사전적으로 풀이
        int currentCoin = 0;
        while (targetAmount > 0) {
            // 현재 선택한 동전을 사용할 수 있을 때
            if(targetAmount >= coinAmounts[currentCoin]) {
                // 사용
                targetAmount -= coinAmounts[currentCoin];
                coinUsed++;
            }
            // 아니라면 다음 동전 사용
            else currentCoin++;
        }

         */

        // 2. 수학적으로 계산
        // 큰 동전부터 순서대로 확인
        for (int i = 0; i < coinKinds; i++) {
            // 남은 금액에서 현재 동전으로 지불할 수 있는 최대 갯수
            coinUsed += targetAmount / coinAmounts[i];
            // 지불하고 남은 금액 갱신
            targetAmount %= coinAmounts[i];
        }

        return coinUsed;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new four11047().solution());
    }
}
