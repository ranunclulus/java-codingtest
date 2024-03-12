package com.example.javacodingtest.programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Programmers258712 {

    public int solution(String[] friends, String[] gifts) {
        List<List<Integer>> giftRecords = new ArrayList<>();
        int[] scores = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            giftRecords.add(new ArrayList<>());
        }

        for (String gift : gifts) {
            String[] input = gift.split(" ");
            String give = input[0];
            String take = input[1];

            int giveIndex = -1;
            for (int i = 0; i < friends.length; i++) {
                if (friends[i].equals(give)) {
                    giveIndex = i;
                    break;
                }
            }
            int takeIndex = -1;
            for (int i = 0; i < friends.length; i++) {
                if (friends[i].equals(take)) {
                    takeIndex = i;
                    break;
                }
            }
            giftRecords.get(giveIndex).add(takeIndex);

            scores[giveIndex]--;
            scores[takeIndex]++;
        }

        int[] nextGifts = new int[friends.length];

        for (int i = 0; i < friends.length; i++) {

        }

        System.out.println(Arrays.toString(scores));
        return 0;
    }

    public static void main(String[] args) {
        String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
        System.out.println(new Programmers258712().solution(friends, gifts));
    }
}
