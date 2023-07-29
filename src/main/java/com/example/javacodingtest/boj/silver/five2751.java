package com.example.javacodingtest.boj.silver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class five2751 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();

        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            list.add(in.nextInt());
        }

        Collections.sort(list);

        for(int value : list) {
            sb.append(value).append('\n');
        }
        System.out.println(sb);
    }
}