package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2636
public class four2636 {
    // foreach 구문 사용을 위해 한 큐에
    private final int[][] deltas = new int[][] {
            new int[] {-1, 0},
            new int[] {1, 0},
            new int[] {0, -1},
            new int[] {0, 1},
    };

    private void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer colRowToken = new StringTokenizer(reader.readLine());
        int y = Integer.parseInt(colRowToken.nextToken());
        int x = Integer.parseInt(colRowToken.nextToken());

        int cheeseCount = 0;
        int lastCheese = 0;

        int[][] board = new int[y][x];

        for (int i = 0; i < y; i++) {
            StringTokenizer rowToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < x; j++) {
                board[i][j] = Integer.parseInt(rowToken.nextToken());
                if (board[i][j] == 1) cheeseCount++;
            }
        }

        boolean[][] visited = new boolean[y][x];
        // 몇 시간이 지났는지
        int reps = 0;
        Queue<int[]> thisVisit = new LinkedList<>();
        thisVisit.add(new int[] {0, 0});

        // 치즈가 남아 있는 동안
        while (cheeseCount > 0) {
            reps++;
            // 치즈를 만나면 이번이 아닌 다음 번에 주변을 살펴 봐야 하기 때문에
            // thisVisit과 분리
            Queue<int[]> nextVisit = new LinkedList<>();
            Queue<int[]> nextMelt = new LinkedList<>();

            // 치즈를 만나면 멈추었을 때
            // 현재 방문 가능한 점들을 방문하는 반복문
            while (!thisVisit.isEmpty()) {
                int[] now = thisVisit.poll();
                for (int[] delta: deltas) {
                    int nextY = now[0] - delta[0];
                    int nextX = now[1] - delta[1];
                    if (-1 < nextX && nextX < x
                            && -1 < nextY && nextY < y
                            && !visited[nextY][nextX]
                    ) {
                        visited[nextY][nextX] = true;
                        int[] next = new int[] {nextY, nextX};

                        // 다음 방문할 곳이 치즈다
                        if (board[nextY][nextX] == 1) {
                            nextMelt.add(next);
                            nextVisit.add(next);
                        }
                        // 아니라면 이번에 방문할 곳
                        else {
                            thisVisit.add(next);
                        }
                    }
                }
            }
            // 이번에 어떤 치즈가 녹을지 알아냈다
            // 다음 시간에 적용하자
            // 다음 시간에 방문할 곳 지정
            thisVisit = nextVisit;
            // 현재 치즈의 개수가 정답에서 요구하는
            // 직전 시간의 치즈 갯수일 가능성이 높다
            lastCheese = cheeseCount;
            // 남아 있는 치즈의 개수 계산
            cheeseCount -= nextMelt.size();

            while(!nextMelt.isEmpty()) {
                int[] melt = nextMelt.poll();
                board[melt[0]][melt[1]] = 0;
            }
        }
        System.out.println(reps);
        System.out.println(lastCheese);
    }

    public static void main(String[] args) throws IOException {
        new four2636().solution();
    }
}
