package com.example.javacodingtest.swea;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.19
 @link
 @timecomplex
 @performance 23500KB, 168MS
 @category
 @note
 */
public class none4012 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static boolean[] isSelected;
    static int[][] foonds;
    static int[] plateOne;
    static int[] plateTwo;
    static int min;
    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            min = Integer.MAX_VALUE;
            n = Integer.parseInt(br.readLine());
            plateOne = new int[n /2];
            plateTwo = new int[n /2];
            isSelected = new boolean[n];
            foonds = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    foonds[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            recursive(0, 0);

            sb.append("#").append(test).append(" ").append(min).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void recursive(int depth, int start) {
        if (depth == n / 2) {

            int index = 0;
            for (int i = 0; i < n; i++) {
                if (!isSelected[i]) {
                    plateTwo[index] = i;
                    index++;
                }
            }
            calculate();
            return;
        }
        if (start >= n) return;

        isSelected[start] = true;
        plateOne[depth] = start;
        recursive(depth + 1, start + 1);
        isSelected[start] = false;
        recursive(depth, start + 1);
    }

    private void calculate() {
        int sumOne = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i + 1; j < n / 2; j++) {
                sumOne += foonds[plateOne[i]][plateOne[j]];
                sumOne += foonds[plateOne[j]][plateOne[i]];
            }
        }

        int sumTwo = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i + 1; j < n / 2; j++) {
                sumTwo += foonds[plateTwo[i]][plateTwo[j]];
                sumTwo += foonds[plateTwo[j]][plateTwo[i]];
            }
        }

        min = Math.min(min, Math.abs(sumOne - sumTwo));
    }


    public static void main(String[] args) throws IOException {
        new none4012().solution();
    }
}

