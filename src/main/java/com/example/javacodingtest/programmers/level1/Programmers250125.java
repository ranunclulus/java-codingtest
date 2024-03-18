package com.example.javacodingtest.programmers.level1;


public class Programmers250125 {
    public int solution(String[][] board, int h, int w) {
        String color = board[h][w];
        int size = board.length;

        int[] dh = {0, 1, -1, 0};
        int[] dw = {1, 0, 0, -1};

        int answer = 0;

        for (int i = 0; i < 4; i++) {
            int nh = h + dh[i];
            int nw = w + dw[i];

            if (nh < 0 || nh >= size || nw < 0 || nw >= size) continue;
            if (board[nh][nw].equals(color)) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[][] board = {
                {"blue", "red", "orange", "red"},
                {"red", "red", "blue", "orange"},
                {"blue", "orange", "red", "red"},
                {"orange", "orange", "red", "blue"}
        };
        int h = 1;
        int w = 1;
        System.out.println(new Programmers250125().solution(board, h, w));
    }
}
