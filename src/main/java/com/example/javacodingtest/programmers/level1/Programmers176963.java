package com.example.javacodingtest.programmers.level1;

import java.util.Arrays;

public class Programmers176963 {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        for (int i = 0; i < photo.length; i++) {
            int point = 0;
            for (String person : photo[i]) {
                int personIndex = -1;
                for (int j = 0; j < name.length; j++) {
                    if (name[j].equals(person)) {
                        personIndex = j;
                        break;
                    }
                }
                if (personIndex != -1) {
                    point += yearning[personIndex];
                }
            }
            answer[i] = point;
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] name = {"may", "kein", "kain", "radi"};
        int[] yearning = {5, 10, 1, 3};
        String[][] photo = {{"may", "kein", "kain", "radi"},
                {"may", "kein", "brin", "deny"},
                {"kon", "kain", "may", "coni"}};
        int[] result = new Programmers176963().solution(name, yearning, photo);
        System.out.println(Arrays.toString(result));
    }
}
