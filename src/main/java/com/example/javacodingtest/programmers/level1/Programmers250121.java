package com.example.javacodingtest.programmers.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Programmers250121 {
    public List<int[]> solution(int[][] data, String ext, int val_ext, String sort_by) {
        String[] columns = {"code", "date", "maximum", "remain"};
        List<int[]> answerList = new ArrayList<>();

        int extIndex = 0;
        int sortIndex = 0;
        for (int i = 0; i < 4; i++) {
            if (columns[i].equals(ext)) {
                extIndex = i;
            }
            if (columns[i].equals(sort_by)) {
                sortIndex = i;
            }
        }

        for (int[] row : data) {
            if (row[extIndex] < val_ext) {
                answerList.add(row);
            }
        }

        final int finalSortIndex = sortIndex;
        Collections.sort(answerList, (o1, o2) -> o1[finalSortIndex] - o2[finalSortIndex]);

        return answerList;
    }

    public static void main(String[] args) {
        int[][] data = {
                {1, 20300104, 100, 80},
                {2, 20300804, 847, 37},
                {3, 20300401, 10, 8}
        };
        String ext = "date";
        int val_ext = 20300501;
        String sort_by = "remain";
    }
}
