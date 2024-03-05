package com.example.javacodingtest.boj.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Fish {
    int col;
    int row;
    int number;
    int direction;
    boolean isAlive;

    public Fish(int col, int row, int number, int direction, boolean isAlive) {
        this.col = col;
        this.row = row;
        this.number = number;
        this.direction = direction;
        this.isAlive = isAlive;
    }
}

class Shark {
    int col;
    int row;
    int direction;
    int eatSum;

    public Shark(int col, int row, int direction, int eatSum) {
        this.col = col;
        this.row = row;
        this.direction = direction;
        this.eatSum = eatSum;
    }
}

public class two19236 {
    // 1부터 순서 대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    public int[] dCol = {-1, -1, 0, 1, 1, 1, 0, -1};
    public int[] dRow = {0, -1, -1, -1, 0, 1, 1, 1};
    public int maxSum;


    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Fish> fishes = new ArrayList<>();
        int[][] map = new int[4][4];

        for (int i = 0; i < 4; i++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 4; j++) {
                int number = Integer.parseInt(infoToken.nextToken());
                int direction = Integer.parseInt(infoToken.nextToken()) - 1;
                fishes.add(new Fish(i, j, number, direction, true));
                map[i][j] = number;
            }
        }

        Collections.sort(fishes, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.number - o2.number;
            }
        });

        // 상어는 (0, 0) 위치 물고기 먹고 시작
        Fish start = fishes.get(map[0][0] - 1);
        Shark shark = new Shark(0, 0, start.direction, start.number);
        start.isAlive = false;
        map[0][0] = -1;

        dfs(map, shark, fishes);
        System.out.println(maxSum);
    }

    // 상어가 이동할 수 없을 때까지 반복
    private void dfs(int[][] map, Shark shark, List<Fish> fishes) {
        if (shark.eatSum > maxSum) {
            maxSum = shark.eatSum;
        }

        fishes.forEach(fish -> moveFish(fish, map, fishes));

        for (int dist = 1; dist < 4; dist++) {
            int nextCol = shark.col + dCol[shark.direction] * dist;
            int nextRow = shark.row + dRow[shark.direction] * dist;

            if (nextCol < 0 || nextCol >= 4 || nextRow < 0 || nextRow >= 4) continue;
            if (map[nextCol][nextRow] <= 0) continue;

            int[][] copyMap = copyMap(map);
            List<Fish> copyFishes = copyFish(fishes);

            copyMap[shark.col][shark.row] = 0;
            Fish nextTarget = copyFishes.get(map[nextCol][nextRow] - 1);
            Shark nextShark = new Shark(nextTarget.col, nextTarget.row, nextTarget.direction, shark.eatSum + nextTarget.number);
            nextTarget.isAlive = false;
            copyMap[nextTarget.col][nextTarget.row] = -1;

            dfs(copyMap, nextShark, copyFishes);
        }

    }

    private List<Fish> copyFish(List<Fish> fishes) {
        List<Fish> result = new ArrayList<>();
        for (Fish fish : fishes) {
            result.add(new Fish(fish.col, fish.row, fish.number, fish.direction, fish.isAlive));
        }
        return result;
    }

    private int[][] copyMap(int[][] map) {
        int[][] result = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = map[i][j];
            }
        }
        return result;
    }

    private void moveFish(Fish fish, int[][] map, List<Fish> fishes) {
        if (!fish.isAlive) return;

        for (int i = 0; i < 8; i++) {
            int nextDirection = (fish.direction + i) % 8;
            int nextCol = fish.col + dCol[nextDirection];
            int nextRow = fish.row + dRow[nextDirection];

            // 범위 밖
            if (nextCol < 0 || nextCol >= 4 || nextRow < 0 || nextRow >= 4) continue;
            // 상어가 있는 곳
            if (map[nextCol][nextRow] == -1) continue;

            map[fish.col][fish.row] = 0;
            // 이동 가능한 칸이 빈칸이면
            if (map[nextCol][nextRow] == 0) {
                fish.col = nextCol;
                fish.row = nextRow;
            } else { // 빈칸이 아니라면 교환
                Fish temp = fishes.get(map[nextCol][nextRow] - 1);
                temp.col = fish.col;
                temp.row = fish.row;
                map[fish.col][fish.row] = temp.number;

                fish.col = nextCol;
                fish.row = nextRow;
            }
            map[nextCol][nextRow] = fish.number;
            fish.direction = nextDirection;
            return;
        }
    }


    public static void main(String[] args) throws IOException {
        new two19236().solution();
    }
}
