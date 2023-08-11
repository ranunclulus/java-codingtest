package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/3055
public class four3055 {
    private int row;
    private int col;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer rowColToken = new StringTokenizer(reader.readLine());
        row = Integer.parseInt(rowColToken.nextToken());
        col = Integer.parseInt(rowColToken.nextToken());
        char[][] board = new char[row][];
        boolean[][] visited = new boolean[row][col];

        int[][] deltas = new int[][] {
                new int[]{-1, 0},
                new int[]{1, 0},
                new int[]{0, -1},
                new int[]{0, 1}
        };
        // 회차마다 다음번 방문할 예정인 점을 기록
        Queue<int[]> nextVisit = new LinkedList<>();
        // 회차마다 다음번 물이 들어찰 예정인 점을 기록
        Queue<int[]> nextWater = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            board[i] = reader.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                // 시작 지점일 경우
                if (board[i][j] == 'S')
                    nextVisit.add(new int[]{i, j});
                    // 물의 근원인 경우
                else if (board[i][j] == '*')
                    nextWater.add(new int[]{i, j});
            }
        }

        // 고슴도치 목적지 도착 표시용
        boolean success = false;
        // 몇번째 시행인지
        int reps = 0;
        while (!success) {
            reps += 1;

            // 이번에 방문할 물 칸 들
            Queue<int[]> thisWater = nextWater;
            nextWater = new LinkedList<>();
            while (!thisWater.isEmpty()) {
                int[] now = thisWater.poll();
                for (int[] delta: deltas) {
                    int nextY = now[0] - delta[0];
                    int nextX = now[1] - delta[1];
                    if (checkBounds(nextX, nextY)
                            && (board[nextY][nextX] == '.' || board[nextY][nextX] == 'S')
                    ) {
                        board[nextY][nextX] = '*';
                        nextWater.add(new int[]{nextY, nextX});
                    }
                }
            }

            // 이번에 방문할 고슴도치 점
            Queue<int[]> thisVisit = nextVisit;
            nextVisit = new LinkedList<>();
            while (!thisVisit.isEmpty()) {
                int[] now = thisVisit.poll();
                for (int[] delta: deltas) {
                    int nextY = now[0] - delta[0];
                    int nextX = now[1] - delta[1];
                    if (checkBounds(nextX, nextY) && !visited[nextY][nextX]) {
                        if (board[nextY][nextX] == '.') {
                            visited[nextY][nextX] = true;
                            nextVisit.add(new int[]{nextY, nextX});
                        }
                        else if (board[nextY][nextX] == 'D') {
                            success = true;
                        }
                    }
                }
                if (success) break;
            }
            // 다음 방문할 곳이 없다면
            if (nextVisit.isEmpty()) {
                break;
            }
        }
        if (!success) System.out.println("KAKTUS");
        else System.out.println(reps);
    }

    private boolean checkBounds(int x, int y) {
        return (-1 < y && y < row && -1 < x && x < col);
    }

    public static void main(String[] args) throws IOException {
        new four3055().solution();
    }
}
