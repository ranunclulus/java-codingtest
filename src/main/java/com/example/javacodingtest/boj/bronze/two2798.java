package com.example.javacodingtest.boj.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2798
public class two2798 {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 카드 개수, 목표 숫자
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int cardCount = Integer.parseInt(infoToken.nextToken());
        int target = Integer.parseInt(infoToken.nextToken());

        StringTokenizer cardTokens = new StringTokenizer(reader.readLine());
        int[] cards = new int[cardCount];
        for (int i = 0; i < cardCount; i++) {
            cards[i] = Integer.parseInt(cardTokens.nextToken());
        }

        int max = 0;
        // 3장의 카드를 뽑는다
        // 첫 번째 카드 -> n장의 카드가 있다면 첫 번째는 n-2 까지
        for (int i = 0; i < cardCount - 2; i++) {
            // 두 번째 카드 -> 두 번째는 n - 1 까지
            for (int j = i + 1; j < cardCount - 1; j++) {
                // 세 번째 카드 -> 세 번째 카드는 n까지
                for (int k = j + 1; k < cardCount; k++) {
                    int sum = cards[i] + cards[j] + cards[k];
                    // 이번 카드 3장의 합이 여태까지 구한 것 중 제일 크면
                    if (sum <= target && sum > max) max = sum;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new two2798().solution());
    }
}
