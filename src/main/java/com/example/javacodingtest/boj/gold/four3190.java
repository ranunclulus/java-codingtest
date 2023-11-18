package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

class Snake {
    public List<int[]> snake = new ArrayList<>();
    public int direction;

    Snake() {
        snake.add(new int[]{0, 0});
        this.direction = 1;
    }
}

public class four3190 {
    private int size;
    private int[][] map;
    private int appleNum;
    private int time = 0;
    private int moveCount;
    private int[] dCol = new int[]{-1, 0, 1, 0};
    private int[] dRow = new int[]{0, 1, 0, -1};
    private Snake snake;
    private HashMap<Integer, String> hash = new HashMap<>();

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(reader.readLine());
        appleNum = Integer.parseInt(reader.readLine());
        map = new int[size][size];

        for (int i = 0; i < appleNum; i++) {
            StringTokenizer appleToken = new StringTokenizer(reader.readLine());
            int appleCol = Integer.parseInt(appleToken.nextToken()) - 1;
            int appleRow = Integer.parseInt(appleToken.nextToken()) - 1;
            map[appleCol][appleRow] = 1;
        }

        moveCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < moveCount; i++) {
            StringTokenizer moveToken = new StringTokenizer(reader.readLine());
            hash.put(Integer.parseInt(moveToken.nextToken()), moveToken.nextToken());
        }

        snake = new Snake();

        int nowCol = 0;
        int nowRow = 0;

        while (true) {
            time++;
            int direction = snake.direction;
            int nextCol = nowCol + dCol[direction];
            int nextRow = nowRow + dRow[direction];

            if (isFinished(nextCol, nextRow)) break;

            if (map[nextCol][nextRow] == 1) {
                map[nextCol][nextRow] = 0;
                snake.snake.add(new int[]{nextCol, nextRow});
            } else {
                snake.snake.remove(0);
                snake.snake.add(new int[]{nextCol, nextRow});
            }

            if (hash.containsKey(time)) {
                if (hash.get(time).equals("D")) {
                    snake.direction = (direction + 1) % 4;
                } else {
                    snake.direction = (direction + 3) % 4;
                }
            }

            nowCol = nextCol;
            nowRow = nextRow;
        }

        System.out.println(time);
    }

    private boolean isFinished(int nextCol, int nextRow) {
        if (nextCol < 0 || nextCol >= size || nextRow < 0 || nextRow >= size) {
            return true;
        }

        for (int[] snakePoint : snake.snake) {
            if (snakePoint[0] == nextCol && snakePoint[1] == nextRow) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        new four3190().solution();
    }
}
