package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11729
public class one11729 {
    private StringBuilder towerBuilder;
    private int[] pillars = new int[] {1, 2, 3};
    public void solution() throws IOException {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        this.towerBuilder = new StringBuilder();
        this.towerBuilder.append((int) (Math.pow(2, n) - 1)).append('\n');
        hanoi(n, 1, 3, 2);
        System.out.println(this.towerBuilder);
    }

    public void hanoi(int height, int start, int end, int other) {
        // height: 옮기고자 하는 탑의 높이
        // start: 시작 위치
        // end: 목표 위치
        // other: 이동하지 않는 위치 -> 재귀 호출 시에는 end로 바로 가지 않고 other로 바로 보내야 하기 때문
        if (height == 1) {
            // 하나짜리 원반은 바로 이동
            this.towerBuilder.append(start + " " + end + "\n");
        }
        else {
            // 하나가 아니라면
            // N 보다 작은 원반들을 start에서 other로
            hanoi(height - 1, start, other, end);
            // n 번째 원반을 start에서 end로 이동
            this.towerBuilder.append(start + " " + end + "\n");
            // n보다 작은 원반들을 other에서 end로
            hanoi(height - 1, other, end, start);
        }
    }

    public static void main(String[] args) throws IOException {
        new one11729().solution();
    }
}
