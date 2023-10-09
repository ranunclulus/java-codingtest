package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class one14940 {
    public static int rowNum;
    public static int colNum;
    public static int[][] map;
    public static int[][] answer;
    public static boolean[][] visited;
    public static int[] dRow = {0, 0, 1, -1};
    public static int[] dCol = {1, -1, 0, 0};
    
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());

        // 지도의 사이즈 입력받기
        colNum = Integer.parseInt(infoToken.nextToken());
        rowNum = Integer.parseInt(infoToken.nextToken());

        // 지도 초기화하기
        map = new int[colNum][rowNum];
        answer = new int[colNum][rowNum];
        visited = new boolean[colNum][rowNum];

        // 시작점
        int startCol = -1;
        int startRow = -1;

        // 지도 정보 입력받기
        for (int i = 0; i < colNum; i++) {
            StringTokenizer mapInfo = new StringTokenizer(reader.readLine());
            for (int j = 0; j < rowNum; j++) {
                map[i][j] = Integer.parseInt(mapInfo.nextToken());
                if (map[i][j] == 2) {
                    startCol = i;
                    startRow = j;
                }
            }
        }

        bfs(startCol, startRow);

        // 정답 출력해 보기
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    builder.append(-1 + " ");
                }
                else {
                    builder.append(answer[i][j] + " ");
                }
            }
            builder.append('\n');
        }

        System.out.println(builder);
    }

    private void bfs(int col, int row) {
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[] {col, row});
        visited[col][row] = true;

        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowCol = now[0];
            int nowRow = now[1];

            for (int i = 0; i < 4; i++) {
                int nextCol = nowCol + dCol[i];
                int nextRow = nowRow + dRow[i];

                if (nextCol < 0 || nextCol >= colNum || nextRow < 0 || nextRow >= rowNum) continue;
                if (map[nextCol][nextRow] == 0) continue;
                if (visited[nextCol][nextRow]) continue;

                toVisit.add(new int[] {nextCol, nextRow});
                answer[nextCol][nextRow] = answer[nowCol][nowRow] + 1;
                visited[nextCol][nextRow] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new one14940().solution();
    }
}
