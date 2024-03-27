package com.example.javacodingtest.programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Programmers172927 {
    class Mineral {
        int diamond;
        int iron;
        int stone;


        public Mineral(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
    }

    public int[][] scoreBoard = new int[3][3];
    public List<Mineral> list = new ArrayList<>();

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        scoreBoard = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

        int totalPicks = Arrays.stream(picks).sum();

        for (int i = 0; i < minerals.length; i += 5) {
            if (totalPicks == 0) break;

            int dia = 0, iron = 0, stone = 0;
            for (int j = i; j < i + 5; j++) {
                if (j == minerals.length) break;

                String mineral = minerals[j];

                int score = (mineral.equals("iron")) ? 1 : (mineral.equals("stone")) ? 2 : 0;

                dia += scoreBoard[0][score];
                iron += scoreBoard[1][score];
                stone += scoreBoard[2][score];
            }

            list.add(new Mineral(dia, iron, stone));
            totalPicks--;
        }
        Collections.sort(list, (o1, o2) -> (o2.stone - o1.stone));

        for (Mineral mineral : list) {
            int dia = mineral.diamond;
            int iron = mineral.iron;
            int stone = mineral.stone;

            if (picks[0] > 0) {
                answer += dia;
                picks[0]--;
                continue;
            }
            if (picks[1] > 0) {
                answer += iron;
                picks[1]--;
                continue;
            }
            if (picks[2] > 0) {
                answer += stone;
                picks[2]--;
                continue;
            }
        }

        return answer;
    }


    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(new Programmers172927().solution(picks, minerals));
    }
}
