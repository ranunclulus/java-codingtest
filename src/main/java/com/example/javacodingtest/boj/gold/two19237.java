package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class two19237 {
    class Spot {
        int number;
        int time;

        public Spot(int number, int time) {
            this.number = number;
            this.time = time;
        }
    }

    class Shark {
        int col;
        int row;
        int direction;
        int number;
        int[][] dirPriority = new int[4][4];
        boolean isAlive;

        public Shark(int number, int col, int row) {
            this.number = number;
            this.isAlive = true;
            this.col = col;
            this.row = row;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public void setDirPriority(int[][] dirPriority) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    this.dirPriority[i][j] = dirPriority[i][j];
                }
            }
        }
    }

    // 1, 2, 3, 4는 각각 위, 아래, 왼쪽, 오른쪽
    public int[] dCol = {-1, 1, 0, 0};
    public int[] dRow = {0, 0, -1, 1};
    public int n, m, k;
    public Spot[][] map;
    public Shark[] sharks;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());
        k = Integer.parseInt(infoToken.nextToken());

        map = new Spot[n][n];
        sharks = new Shark[m];

        // 지도와 상어 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer mapToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                int number = Integer.parseInt(mapToken.nextToken());
                if (number != 0) {
                    map[i][j] = new Spot(number, k);
                    sharks[number - 1] = new Shark(number, i, j);
                } else {
                    map[i][j] = new Spot(0, 0);
                }
            }
        }

        // 상어 방향 세팅
        StringTokenizer directionToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            sharks[i].setDirection(Integer.parseInt(directionToken.nextToken()) - 1);
        }

        // 상어 방향 우선순위 세팅
        int[][] temp = new int[4][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                StringTokenizer priorityToken = new StringTokenizer(reader.readLine());
                for (int l = 0; l < 4; l++) {
                    temp[j][l] = Integer.parseInt(priorityToken.nextToken()) - 1;
                }
            }
            sharks[i].setDirPriority(temp);
        }

        int time = 0;

        while (!oneLive()) {
            if (time > 1000) {
                time = -1;
                break;
            }
            // 맵 정리
            setMap();
            moveShark();
        }

        System.out.println(time);

    }

    private void moveShark() {
        for (int number = 0; number < m; number++) {
            Shark shark = sharks[number];
            int[] directionArray = shark.dirPriority[shark.direction];

            for (int i = 0; i < 4; i++) {
                int nextCol = shark.col + dCol[directionArray[i]];
                int nextRow = shark.row + dRow[directionArray[i]];

                // 범위 밖인 경우
                if (nextCol < 0 || nextCol >= n || nextRow < 0 || nextRow >= n) continue;

                // 가려는 칸이 비어서 그냥 전진
                if (map[nextCol][nextRow].number == 0) {

                }

                // 가려는 칸에 다른 향기가 있음
                // 가려는 칸에 향기만 있어서 못 감
                // 가려는 칸에 상어가 있어서 잡아먹음

            }
            // 다 못 가서 뒤로 후진
        }
    }

    private void setMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].time == 1) {
                    map[i][j].number = 0;
                }
                if (map[i][j].time != 0) {
                    map[i][j].time--;
                }
            }
        }
    }

    private boolean oneLive() {
        for (int i = 1; i < m - 1; i++) {
            if (sharks[i].isAlive) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new two19237().solution();
    }
}
