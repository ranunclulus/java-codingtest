package com.example.javacodingtest.boj.silver;

import java.util.Scanner;

public class five14916 {
    public void solution() throws Exception {
        Scanner sc = new Scanner(System.in);
        int cost = sc.nextInt();
        int coin = 0;

        coin = cost / 5;
        cost %= 5;

        if (cost % 2 == 1) {
            cost += 5;
            coin--;
        }
        coin += cost / 2;

        System.out.println(coin);
    }

    public static void main(String[] args) throws Exception {
        new five14916().solution();
    }
}
