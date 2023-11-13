package com.example.javacodingtest.boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class one14889 {
    private int peopleCnt;
    private int[][] soccerMap;
    private int minValue;
    private int[] isTeam;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        peopleCnt = sc.nextInt();
        soccerMap = new int[peopleCnt][peopleCnt];
        isTeam = new int[peopleCnt];

        for (int i = 0; i < peopleCnt; i++) {
            for (int j = 0; j < peopleCnt; j++) {
                soccerMap[i][j] = sc.nextInt();
            }
        }

        minValue = Integer.MAX_VALUE;
        selectTeam(0, 0);

        System.out.println(minValue);
    }

    private void selectTeam(int index, int depth) {
        if (depth == peopleCnt / 2) {
            int startValue = 0;
            int linkValue = 0;
            for (int i = 0; i < peopleCnt; i++) {
                for (int j = i; j < peopleCnt; j++) {
                    if (isTeam[i] == 1 && isTeam[j] == 1)
                        startValue += (soccerMap[i][j] + soccerMap[j][i]);
                    if (isTeam[i] == 0 && isTeam[j] == 0)
                        linkValue += (soccerMap[i][j] + soccerMap[j][i]);
                }
            }
            minValue = Math.min(minValue, Math.abs(startValue - linkValue));
            return;
        }
        for (int i = index; i < peopleCnt; i++) {
            isTeam[i] = 1;
            selectTeam(i + 1, depth + 1);
            isTeam[i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        new one14889().solution();
    }
}
