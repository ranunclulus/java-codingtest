package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class four1715 {
    private int n;
    private PriorityQueue<Integer> cards;
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cards = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            cards.offer(sc.nextInt());
        }


        int result = 0;
        PriorityQueue<Integer> mixCards = new PriorityQueue<>();
        while (cards.size() != 1) {
            for (int i = 0; i < (cards.size()/ 2); i++) {
                int plus = cards.poll() + cards.poll();
                cards.add(plus);
                result += plus;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new four1715().solution();
    }
}
