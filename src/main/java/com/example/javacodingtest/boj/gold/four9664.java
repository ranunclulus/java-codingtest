package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.acmicpc.net/problem/9663
public class four9664 {
    // 판을 int[][]로 다 구현하는 방법
    // 아니면 각 줄에 어느 위치에 queeen이 존재하는지 기록하는 방법
    private int[] queenPos;
    // n
    private int size;
    // 몇 개의 queen을 뒀는지
    private int count;

    public int solution() throws IOException {
        size = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        queenPos = new int[size];
        count = 0;
        setQueenPos(0);
        return count;
    }

    private void setQueenPos(int row) {
        // 전부 배치 완료된 경우
        if(row == size) {
            count++;
        }
        else for (int i = 0; i < size; i++) {
            // row 번째 queen 위치는 순회 중인 i
            queenPos[row] = i;
            // 이번 줄에 배치한 결과가 조건에 부합하면 다음으로
            if(checkQueen(row)) setQueenPos(row + 1);
        }
    }

    // 유망성 조사
    // 현재 배치가 요구사항을 만족하는지
    private boolean checkQueen(int row) {
        // 세로줄에 겹치는지
        for (int i = 0; i < row; i++) {
            if(queenPos[i] == queenPos[row]) return false;
            else if(Math.abs(queenPos[i] - queenPos[row]) == row - i) return false;
        }

        // 가로선은 겹치지 않음
        return true;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new four9664().solution());
    }

}
