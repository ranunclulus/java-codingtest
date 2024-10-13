package com.example.javacodingtest.programmers.level2;

import java.util.*;
import java.io.*;
public class Programmers150368 {
    int[] sales;
    int m;
    int[] answer;

    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE};
        m = emoticons.length;
        sales = new int[m];
        makeSales(users, emoticons, 0);
        return answer;
    }

    public void makeSales(int[][] users, int[] emoticons, int depth) {
        if (depth == m) {
            purchase(users, emoticons);
            return;
        }
        for(int i = 10; i <= 40; i += 10) {
            sales[depth] = i;
            makeSales(users, emoticons, depth + 1);
        }
    }

    public void purchase(int[][] users, int[] emoticons) {
        int[] prices = new int[m];
        for(int i = 0; i < m; i++) {
            prices[i] = (emoticons[i] * (100 - sales[i])) / 100;
        }

        int totalPrice = 0;
        int emoticonPlus = 0;
        for(int[] user : users) {
            int userPrice = 0;
            for (int i = 0; i < m; i++) {
                if (sales[i] < user[0]) continue;
                userPrice += prices[i];
            }
            if (user[1] <= userPrice) {
                emoticonPlus++;
            } else totalPrice += userPrice;
        }

        if (emoticonPlus > answer[0]) {
            answer[0] = emoticonPlus;
            answer[1] = totalPrice;
        } else if (emoticonPlus == answer[0]) {
            if (totalPrice > answer[1]) answer[1] = totalPrice;
        }
    }
}
