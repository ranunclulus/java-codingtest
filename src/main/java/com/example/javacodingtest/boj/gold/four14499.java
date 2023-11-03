package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class four14499 {
    private int n;
    private int m;
    private int x;
    private int y;
    private int k;
    private int[][] map;
    private int[] movement;
    private int[] dice = new int[6];
    private int[] dx = new int[] {0, 0, 0, -1, 1};
    private int[] dy = new int[] {0, 1, -1, 0, 0};
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());
        x = Integer.parseInt(infoToken.nextToken());
        y = Integer.parseInt(infoToken.nextToken());
        k = Integer.parseInt(infoToken.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            StringTokenizer mapToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(mapToken.nextToken());
            }
        }

        movement = new int[k];
        StringTokenizer moveToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < k; i++) {
            movement[i] = Integer.parseInt(moveToken.nextToken());
        }


        for (int direction : movement) {
            moveDice(direction);
        }
    }

    private void moveDice(int direction) {
        if(!validateDice(direction)) return;
        if (direction == 1) {
            int temp = dice[5];
            dice[5] = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[1];
            dice[1] =temp;
        } else if (direction == 2) {
            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = dice[5];
            dice[5] = temp;
        } else if (direction == 3) {
            int temp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[4];
            dice[4] = dice[5];
            dice[5] = temp;
        } else {
            int temp = dice[5];
            dice[5] = dice[4];
            dice[4] = dice[2];
            dice[2] = dice[0];
            dice[0] = temp;
        }
        System.out.println(dice[2]);
        paintDice();
    }

    private boolean validateDice(int direction) {
        int nextX = x + dx[direction];
        int nextY = y + dy[direction];
        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) return false;
        else {
            x = nextX;
            y = nextY;
            return true;
        }
    }

    private void paintDice() {
        if (map[x][y] == 0) map[x][y] = dice[5];
        else {
            dice[5] = map[x][y];
            map[x][y] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        new four14499().solution();
    }
}
