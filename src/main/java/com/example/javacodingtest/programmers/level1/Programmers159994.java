package com.example.javacodingtest.programmers.level1;

public class Programmers159994 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        boolean signal = true;
        int cards1Index = 0;
        int cards2Index = 0;
        for (String word : goal) {
            if (cards1Index < cards1.length && cards1[cards1Index].equals(word)) {
                cards1Index++;
            } else if (cards2Index < cards2.length && cards2[cards2Index].equals(word)) {
                cards2Index++;
            } else {
                signal = false;
                break;
            }
        }

        if (!signal) answer = "No";
        return answer;
    }

    public static void main(String[] args) {
        String[] cards1 = {"i", "water", "drink"};
        String[] cards2 = {"want", "to"};
        String[] goal = {"i", "want", "to", "drink", "water"};
        System.out.println(new Programmers159994().solution(cards1, cards2, goal));
    }
}
