package com.example.javacodingtest.boj.silver;

import java.util.Scanner;

public class five14916 {
    public void solution() throws Exception {
        Scanner sc = new Scanner(System.in);
        int cost = sc.nextInt();
        int coin = 0;

        coin = cost / 5;
        int rest = cost % 5;

        if (rest % 2 == 1 && cost > 5) {
            rest += 5;
            coin--;
        }

        coin += rest / 2;
        rest %= 2;

        if (rest == 0) System.out.println(coin);
        else System.out.println(-1);
    }

    public static void main(String[] args) throws Exception {
        new five14916().solution();
    }
}
