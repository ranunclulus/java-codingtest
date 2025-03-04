package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

public class four17144 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int colNum;
    static int rowNum;
    static int time;

    static int[][] map;
    // 우 하 상 좌
    static int[] dCol = new int[]{1, 0, 0, -1};
    static int[] dRow = new int[]{0, -1, 1, 0};

    static int[][] cleaner = new int[2][2];

    public void solution() throws IOException {
        int count = 0;
        st = new StringTokenizer(br.readLine());
        rowNum = Integer.parseInt(st.nextToken());
        colNum = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());
        map = new int[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < colNum; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    cleaner[count][0] = i;
                    cleaner[count][1] = j;
                    count++;
                }
            }
        }

        while (time > 0) {
            spread();
            freshOne();
            freshTwo();
            time--;
        }


        count = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (map[i][j] > 0) count += map[i][j];
            }
        }

        sb.append(count);
        bw.write(sb.toString());
        bw.flush();

    }

    // 상 좌 하 우
    private void freshTwo() {
        int[] spotTwo = cleaner[1];

        for (int i = spotTwo[0] + 1; i < rowNum - 1; i++) {
            map[i][0] = map[i + 1][0];
        }

        for (int i = 0; i < colNum - 1; i++) {
            map[rowNum - 1][i] = map[rowNum - 1][i + 1];
        }

        for (int i = rowNum - 1; i > spotTwo[0]; i--) {
            map[i][colNum - 1] = map[i - 1][colNum - 1];
        }

        for (int i = colNum - 1; i > 0; i--) {
            map[spotTwo[0]][i] = map[spotTwo[0]][i - 1];
        }
        map[spotTwo[0]][1] = 0;
    }

    // 하 좌 상 우
    private void freshOne() {
        int[] spotOne = cleaner[0];

        for (int i = spotOne[0] - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }

        for (int i = 0; i < colNum - 1; i++) {
            map[0][i] = map[0][i + 1];
        }

        for (int i = 0; i < spotOne[0]; i++) {
            map[i][colNum - 1] = map[i + 1][colNum - 1];
        }

        for (int i = colNum - 1; i > 0; i--) {
            map[spotOne[0]][i] = map[spotOne[0]][i - 1];
        }
        map[spotOne[0]][1] = 0;
    }

    private void spread() {
        int[][] temp = new int[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                temp[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (map[i][j] > 0) {
                    int spreadAmount = temp[i][j] / 5;
                    if (spreadAmount == 0) continue;
                    int spreadCnt = 0;
                    // (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산
                    for (int d = 0; d < 4; d++) {
                        int nextRow = i + dRow[d];
                        int nextCol = j + dCol[d];
                        // 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않음
                        if (nextRow < 0 || nextRow >= rowNum || nextCol < 0 || nextCol >= colNum) continue;
                        if (map[nextRow][nextCol] == -1) continue;

                        map[nextRow][nextCol] = map[nextRow][nextCol] + spreadAmount;
                        spreadCnt++;
                    }
                    map[i][j] = map[i][j] - spreadAmount * spreadCnt;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new four17144().solution();
    }
}
