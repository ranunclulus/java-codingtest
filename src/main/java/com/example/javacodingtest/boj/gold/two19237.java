package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class two19237 {
    class Shark {
        int col;
        int row;
        int direction;
        int number;
        int timeStamp;
        int[][] position;

        int[][] dirPriority = new int[4][4];
        boolean isAlive;

        public Shark(int number, int timeStamp, int col, int row) {
            this.number = number;
            this.timeStamp = timeStamp;
            this.position = new int[timeStamp][2];
            for (int i = 0; i < timeStamp; i++) {
                Arrays.fill(this.position[i], -1);
            }
            this.position[0][0] = col;
            this.position[0][1] = row;
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

        public void print() {
            System.out.println("direction = " + direction);
            System.out.println("number = " + number);
            System.out.println("timeStamp = " + timeStamp);
            System.out.println();
            System.out.println("position");
            for (int[] row : position) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
            System.out.println("dir priority");
            for (int[] row : dirPriority) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }

        public void move(int nextCol, int nextRow, int direction) {
            this.direction = direction;
            for (int i = timeStamp - 1; i > 0; i--) {
                position[i][0] = position[i - 1][0];
                position[i][1] = position[i - 1][1];
            }
            position[0][0] = nextCol;
            position[0][1] = nextRow;
            this.col = nextCol;
            this.row = nextRow;
        }

        public void dead(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }

    // 1, 2, 3, 4는 각각 위, 아래, 왼쪽, 오른쪽
    public int[] dCol = {-1, 1, 0, 0};
    public int[] dRow = {0, 0, -1, 1};
    public int n, m, k;
    public int[][] map;
    public Shark[] sharks;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());
        k = Integer.parseInt(infoToken.nextToken());

        map = new int[n][n];
        sharks = new Shark[m];

        // 지도와 상어 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer mapToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(mapToken.nextToken());
                if (map[i][j] != 0) {
                    sharks[map[i][j] - 1] = new Shark(map[i][j], k, i, j);
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
            time++;
            moveShark();
            if (time >= 1000) {
                time = -1;
                break;
            }
        }
        System.out.println(time);
    }

    private boolean oneLive() {
        for (int i = 1; i < m; i++) {
            if (sharks[i].isAlive) return false;
        }
        return true;
    }

    private void moveShark() {
        for (int current = 0; current < m; current++) {
            Shark shark = sharks[current];
            // 죽었다면 패스
            if (!shark.isAlive) {
                shark.move(-1, -1, -1);
                if (shark.position[k - 1][0] != -1 && shark.position[k - 1][1] != -1) {
                    map[shark.position[k - 1][0]][shark.position[k - 1][1]] = 0;
                }
                continue;
            }
            int[] moveDir = shark.dirPriority[shark.direction];

            boolean canMove = false;
            for (int i = 0; i < 4; i++) {
                if (!shark.isAlive) break;
                int nextCol = shark.col + dCol[moveDir[i]];
                int nextRow = shark.row + dRow[moveDir[i]];
                // 범위 밖이라면 패스
                if (nextCol < 0 || nextCol >= n || nextRow < 0 || nextRow >= n) continue;
                // 다른 상어 향기
                boolean isSharked = false;
                if (map[nextCol][nextRow] != 0) {
                    // 상어가 있는 경우
                    for (int j = 0; j < current; j++) {
                        if (sharks[j].col == nextCol && sharks[j].row == nextRow) {
                            shark.isAlive = false;
                            shark.dead(-1, -1);
                            isSharked = true;
                            canMove = true;
                            break;
                        }
                    }
                    // 향기만 있는 경우
                    if (!isSharked) continue;
                }
                if (isSharked) break;

                // 마지막 향기 제거 후 새 향기 추가
                if (shark.position[k - 1][0] != -1) {
                    map[shark.position[k - 1][0]][shark.position[k - 1][1]] = 0;
                }
                map[nextCol][nextRow] = shark.number;
                shark.move(nextCol, nextRow, moveDir[i]);
                canMove = true;
                break;
            }
            if (!canMove) {
                int nextCol = 0;
                int nextRow = 0;
                int direction = 0;
                int[] directions = shark.dirPriority[shark.direction];
                for (int i = 0; i < 4; i++) {
                    nextCol = shark.col + dCol[directions[i]];
                    nextRow = shark.row + dRow[directions[i]];
                    if (nextCol < 0 || nextCol >= n || nextRow < 0 || nextRow >= n) continue;
                    if (map[nextCol][nextRow] == shark.number) {
                        direction = directions[i];
                        break;
                    }
                }
                // 마지막 향기 제거 후 새 향기 추가
                if (shark.position[k - 1][0] != -1) {
                    map[shark.position[k - 1][0]][shark.position[k - 1][1]] = 0;
                }
                map[nextCol][nextRow] = shark.number;

                shark.move(nextCol, nextRow, direction);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two19237().solution();
    }
}
