package com.example.javacodingtest.programmers.level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Programmers178871 {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> participants = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            participants.put(players[i], i);
        }

        for (String call : callings) {
            int rank = participants.get(call);
            String temp = players[rank - 1];
            players[rank - 1] = players[rank];
            players[rank] = temp;
            participants.put(call, rank - 1);
            participants.put(temp, rank);
        }
        return players;
    }

    public static void main(String[] args) {
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};
        System.out.println(Arrays.toString(new Programmers178871().solution(players, callings)));
    }
}
