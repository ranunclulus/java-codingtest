package com.example.javacodingtest.swea;

import java.io.IOException;
import java.util.Scanner;

public class three18662 {
    private int difference;
    private int[] numbers;

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int test = 0; test < testNum; test++) {
            difference = -1;

            numbers = new int[3];
            for (int i = 0; i < 3; i++) {
                numbers[i] = sc.nextInt() * 10;
            }

            boolean moreToSearch = true;

            while (moreToSearch) {
                difference += 1;
                int[] diff = new int[]{difference, -difference};
                for (int i = 0; i < 2; i++) {
                    int first = numbers[0] + diff[i];
                    for (int j = 0; j < 2; j++) {
                        int second = numbers[1] + diff[j];
                        for (int k = 0; k < 2; k++) {
                            int third = numbers[2] + diff[k];
                            if ((third - second) == (second - first)) {
                                moreToSearch = false;
                                break;
                            }
                        }
                        if (!moreToSearch) break;
                    }
                    if (!moreToSearch) break;
                }
                if (!moreToSearch) break;
            }

            System.out.printf("#%d %.1f\n", test + 1, difference / 10.0);
        }
    }

    public static void main(String[] args) throws IOException {
        new three18662().solution();
    }
}
