package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;
/*
 @author ranuinclulus
 @since 2025.03.120
 @link https://www.acmicpc.net/problem/17143
 @timecomplex
 @performance 47172kb 620ms
 @category
 @note
 */
public class one17143 {

    class Shark {
        int row;
        int col;
        int speed;
        int direction;
        int size;

        Shark(int row, int col, int speed, int direction, int size) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int R, C, M, totalSize;
    static List<Shark> sharks;
    static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    
    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        sharks = new ArrayList<>();
        for (int i = 1; i <= M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            sharks.add(new Shark(
                Integer.parseInt(tokenizer.nextToken()) - 1,
                Integer.parseInt(tokenizer.nextToken()) - 1,
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()) - 1,
                Integer.parseInt(tokenizer.nextToken())));
        }

        for(int fishMan = 0; fishMan < C; fishMan++) {
            catchShark(fishMan);
            moveShark();
            eatShark();

        }

        builder.append(totalSize);
        writer.write(builder.toString());
        writer.flush();

    }

    private void catchShark(int column) {
        List<Shark> candidate = new ArrayList<>();
        for(Shark shark : sharks) {
            if (shark.col == column) candidate.add(shark);
        }
        if (candidate.size() == 0) return;
        Collections.sort(candidate, (o1, o2) -> Integer.compare(o1.row, o2.row));
        Shark target = candidate.get(0);
        totalSize += target.size;
        sharks.remove(target);
    }

    private void moveShark() {
        for (Shark shark : sharks) {
            int speed = shark.speed;
            if (shark.direction == 0 || shark.direction == 1) { // Up or Down
                speed %= (R - 1) * 2; // 상어가 위아래로 이동할 때 한 사이클의 이동 길이
            } else { // Left or Right
                speed %= (C - 1) * 2; // 상어가 좌우로 이동할 때 한 사이클의 이동 길이
            }
            for (int i = 0; i < speed; i++) {
                int nextRow = shark.row + deltas[shark.direction][0];
                int nextCol = shark.col + deltas[shark.direction][1];

                if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) {
                    if (shark.direction == 0) {
                        shark.direction = 1;
                    } else if (shark.direction == 1) {
                        shark.direction = 0;
                    } else if (shark.direction == 2) {
                        shark.direction = 3;
                    } else if (shark.direction == 3) {
                        shark.direction = 2;
                    }
                    nextRow = shark.row + deltas[shark.direction][0];
                    nextCol = shark.col + deltas[shark.direction][1];
                }
                shark.row = nextRow;
                shark.col = nextCol;
            }
        }
    }


    public void eatShark() {
        Shark[][] sharkMap = new Shark[R][C];

        for(Shark shark : sharks) {
            if (sharkMap[shark.row][shark.col] == null) {
                sharkMap[shark.row][shark.col] = shark;
            } else if (sharkMap[shark.row][shark.col].size < shark.size) {
                sharkMap[shark.row][shark.col] = shark;
            }
        }

        List<Shark> survive = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sharkMap[i][j] != null) {
                    survive.add(sharkMap[i][j]);
                }
            }
        }

        sharks.clear();
        sharks.addAll(survive);
    }

    public static void main(String[] args) throws IOException {
        new one17143().solution();
    }
}
