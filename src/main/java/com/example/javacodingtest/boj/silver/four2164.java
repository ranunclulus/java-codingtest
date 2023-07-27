package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//https://www.acmicpc.net/problem/2164
public class four2164 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Queue<Integer> card = new LinkedList<>();
        if(n == 1) {
            System.out.println(1);
            return;
        }
        for (int i = 1; i <= n; i++) {
            card.add(i);
        }
        while(card.size() > 1) {
            card.poll();
            if (card.size() == 1) {
                break;
            }
            int top = card.poll();
            card.add(top);

        }
        System.out.println(card.peek());
    }

    public static void main(String[] args) throws IOException {
        new four2164().solution();
    }
}
