package com.example.javacodingtest.codingtest.windmill;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CardManage {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int balance = Integer.parseInt(infoToken.nextToken());
        int transaction = Integer.parseInt(infoToken.nextToken());
        Queue<Integer> reservation = new LinkedList<>();
        for (int i = 0; i < transaction; i++) {
            while (balance >= 0) {
                if (reservation.isEmpty()) break;
                balance -= reservation.poll();
            }

            StringTokenizer transactionToken = new StringTokenizer(reader.readLine());
            String type = transactionToken.nextToken();
            int amount = Integer.parseInt(transactionToken.nextToken());

            if (type.equals("deposit")) {
                balance += amount;
            }
            if (type.equals("pay")) {
                if (balance >= amount) balance -= amount;
                else reservation.add(amount);
            }
            if (type.equals("reservation")) {
                reservation.add(amount);
            }
        }
        System.out.println(balance);
    }
}
