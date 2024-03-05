package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Fish {
    int value;
    int direction;

    public Fish(int value, int direction) {
        this.value = value;
        this.direction = direction;
    }

    public void die() {
        this.direction = -1;
    }

    @Override
    public String toString() {
        return "{" +
                "value=" + value +
                ", direction=" + direction +
                "}       ";
    }
}

class Shark {
    int col;
    int row;
    int direction;
    int kill;

    public Shark(int col, int row, int direction, int kill) {
        this.col = col;
        this.row = row;
        this.direction = direction;
        this.kill = kill;
    }

    @Override
    public String toString() {
        return "Shark{" +
                "col=" + col +
                ", row=" + row +
                "}";
    }
}

public class two19236 {
    // 1부터 순서 대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    public int[] dCol = {-1, -1, 0, 1, 1, 1, 0, -1};
    public int[] dRow = {0, -1, -1, -1, 0, 1, 1, 1};
    public Fish[][] map;
    public Shark shark;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        map = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            StringTokenizer sharkToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 4; j++) {
                int value = Integer.parseInt(sharkToken.nextToken());
                int direction = Integer.parseInt(sharkToken.nextToken()) - 1;
                map[i][j] = new Fish(value, direction);
            }
        }

        // 상어는 (0,0) 물고기를 먹고 시작함
        shark = new Shark(0, 0, map[0][0].direction, map[0][0].value);
        map[0][0].die();

        // 물고기들 이동
        move();

        // 상어의 이동 ---? 최대가 되게 하는 상어의 이동...

        // print
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j].toString());
            }
            System.out.println();
        }


    }

    public void move() {
        // 작은 물고기부터 이동하기
        for (int number = 1; number <= 16; number++) {
            boolean finishMove = false;
            for (int i = 0; i < 4; i++) {
                if (finishMove) break;
                for (int j = 0; j < 4; j++) {
                    // 해당 차례가 되었다면
                    if (map[i][j].value == number) {
                        int direction = map[i][j].direction; // 이동할 물고기의 방향
                        if (direction == -1) {
                            finishMove = true;
                            break;
                        }
                        int nextCol = 0, nextRow = 0;
                        // 이동 가능한 지점 찾을 때까지
                        for (int d = 0; d < 8; d++) {
                            if (canMove(i, j, (direction + d) % 8)) {
                                nextCol = i + dCol[(direction + d) % 8];
                                nextRow = j + dRow[(direction + d) % 8];
                                break;
                            }
                        }
                        Fish temp = new Fish(map[i][j].value, map[i][j].direction);
                        map[i][j].value = map[nextCol][nextRow].value;
                        map[i][j].direction = map[nextCol][nextRow].direction;
                        map[nextCol][nextRow].value = temp.value;
                        map[nextCol][nextRow].direction = temp.direction;
                        finishMove = true;
                        break;
                    }
                }
            }
        }
    }

    private boolean canMove(int col, int row, int direction) {

        int nextCol = col + dCol[direction];
        int nextRow = row + dRow[direction];

        // 범위 밖이면 이동 불가능
        if (nextCol < 0 || nextCol >= 4 || nextRow < 0 || nextRow >= 4) return false;

        // 상어가 있는 곳이면 이동 불가능
        if (shark.col == nextCol && shark.row == nextRow) return false;

        return true;
    }


    public static void main(String[] args) throws IOException {
        new two19236().solution();
    }
}
