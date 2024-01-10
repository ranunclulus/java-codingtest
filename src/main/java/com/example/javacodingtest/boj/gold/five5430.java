package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.Scanner;

public class five5430 {
    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        for (int test = 0; test < testCase; test++) {
            boolean isError = false;
            boolean isFront = true;
            String move = sc.nextLine();
            int back = Integer.parseInt(sc.nextLine());
            String input = sc.nextLine();
            String[] numbers = input.substring(1, input.length() - 1).split(",");
            int front = 0;

            for (int i = 0; i < move.length(); i++) {
                char movement = move.charAt(i);
                if (movement == 'D') {
                    if (front == back) {
                        isError = true;
                        break;
                    }
                    if (isFront) front++;
                    else back--;
                }
                if (movement == 'R') {
                    isFront = !isFront;
                }
            }

            if (isError) {
                System.out.println("error");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                if (isFront) {
                    for (int i = front; i < back; i++) {
                        sb.append(numbers[i]).append(",");
                    }
                } else {
                    for (int i = back - 1; i >= front; i--) {
                        sb.append(numbers[i]).append(",");
                    }
                }
                if (sb.length() != 1) sb.replace(sb.length() - 1, sb.length(), "");
                sb.append("]");
                System.out.println(sb);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five5430().solution();
    }
}
