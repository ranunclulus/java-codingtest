package com.example.javacodingtest.programmers.level1;

import java.util.Arrays;

public class Programmers178871 {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            answer[i] = players[i];
        }

        for (String call : callings) {
            int index = -1;
            // 인덱스 찾기
            for (int i = 0; i < players.length; i++) {
                if (answer[i].equals(call)) {
                    index = i;
                    break;
                }
            }
            String temp = answer[index];
            answer[index] = answer[index - 1];
            answer[index - 1] = temp;
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};
        System.out.println(Arrays.toString(new Programmers178871().solution(players, callings)));
    }
}
