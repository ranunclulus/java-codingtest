package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class three2206 {
    private int[] drow = new int[] {0, 0, 1, -1};
    private int[] dcol = new int[] {1, -1, 0, 0};
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int colNum = Integer.parseInt(infoToken.nextToken());
        int rowNum = Integer.parseInt(infoToken.nextToken());
        int[][] map = new int[colNum][rowNum];

        for (int i = 0; i < colNum; i++) {
            String[] mapInfo = reader.readLine().split("");
            for (int j = 0; j < rowNum; j++) {
                map[i][j] = Integer.parseInt(mapInfo[j]);
            }
        }

        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[] {0, 0, 0});
        boolean[][][] visited = new boolean[2][colNum][rowNum];
        int[][] dist = new int[colNum][rowNum];

        while(!toVisit.isEmpty()) {
            int[] current = toVisit.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = current[1] + drow[i];
                int nextCol = current[0] + dcol[i];

                if (nextCol < 0 || nextCol >= colNum || nextRow < 0 || nextRow >= rowNum)
                    continue;

                // 다음 칸에 벽이 있을 때
                if(map[nextCol][nextRow] == 1) {
                    // 벽을 부순 적이 있는지, 벽을 방문한 적이 있는지
                    if (current[2] == 0 && !visited[1][nextCol][nextRow]) {
                        visited[current[2]][nextCol][nextRow] = true;
                        dist[nextCol][nextRow] = dist[current[0]][current[1]] + 1;
                        toVisit.offer(new int[] {nextCol, nextRow, 1});
                    }
                }
                // 벽이 아닐 경우
                // 벽을 부순 여부에 따라 방문을 했는지 체크
                else {
                    if (!visited[current[2]][nextCol][nextRow]) {
                        visited[current[2]][nextCol][nextRow] = true;
                        dist[nextCol][nextRow] = dist[current[0]][current[1]] + 1;
                        toVisit.offer(new int[] {nextCol, nextRow, current[2]});
                    }
                }
                if (nextCol == colNum - 1 && nextRow == rowNum - 1) {
                    System.out.println(dist[nextCol][nextRow] + 1);
                    System.exit(0);
                }
            }
        }
        if (colNum == 1 && rowNum == 1) System.out.println(1);
        else System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        new three2206().solution();
    }
}
