package com.example.javacodingtest.programmers.level2;
// https://school.programmers.co.kr/learn/courses/30/lessons/1844?language=java
import java.util.LinkedList;
import java.util.Queue;

public class GameMapShortestPath {
    // 사방 탐색을 위한 변수
    private final int[] dx = {-1, 1, 0, 0};
    private final int[] dy = {0, 0, -1, 1};

    // 최단 거리를 돌려 준다
    public int solution(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        // bfs 로직을 사용하는데
        // 다음에 접근할 수 있는 칸을 maze의 가로 세로 크기 이내의 인접한 칸을 기준으로 판단

        // int[]를 담는 Queue {x, y, distance}
        Queue<int[]> visitNext = new LinkedList<>();
        // 미로에서 이미 도달한 적 있는 칸임을 나타내기 위한 boolean[][] visited
        boolean[][] visited = new boolean[n][m];
        // 시작점인 (5, 0)에서 시작하고 아직 거리가 0
        visitNext.offer(new int[] {0, 0, 1});
        // 정답을 담기 위한 answer
        int answer = -1;

        // bfs 시작
        // queue가 비어 있지 않은 동안에
        while(!visitNext.isEmpty()) {
            // 다음에 갈 곳을 queue에서 꺼낸다
            int[] next = visitNext.poll();
            int nowX = next[0];
            int nowY = next[1];
            int steps = next[2];
            visited[nowX][nowY] = true;

            // 네 개의 방향을 확인한다
            int[] top = new int[] {nowX - 1, nowY, steps + 1}; //
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                // 현재 칸이 3이라면 이미 도달
                // bfs이기에 제일 먼저 도달한 애가 제일 짧은 거리임을 기대할 수 있음
                if (nowX == n - 1 && nowY == m - 1) {
                    answer = steps;
                    break;
                }
                if(
                    // 1. 미로의 범위를 벗어나는가?
                    // 2. 길이 존재하는가?
                    // 3. 이미 방문하지 않았는가?
                        (checkBounds(nextX, nextY, n, m)) &&
                        (maze[nextX][nextY] == 1) &&
                        (!visited[nextX][nextY])
                ) {
                    // 방문 대상으로 기록
                    visitNext.offer(new int[] {nextX, nextY, steps + 1});
                    // 방문 표시
                    visited[nextX][nextY] = true;
                }

            }
        }

        return answer;
    }

    // 미로의 범위 내에 있는지 검사
    private boolean checkBounds(int x, int y, int n, int m) {
        return -1 < x && x < n && -1 < y && y < m;
    }

    public static void main(String[] args) {
        // 2가 시작점 3이 목적지
        // {5, 0} 시작 {0, 5} 도착
        // 실제로는 x가 y 같고 y가 x처럼 움직임
        int answer = new GameMapShortestPath().solution(new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        });

        System.out.println("answer = " + answer);
    }
}