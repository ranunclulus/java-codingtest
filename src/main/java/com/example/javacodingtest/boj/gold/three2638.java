package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class three2638 {
    public int n, m;
    public int[][] map;
    public int[] dx = new int[]{0, 0, 1, -1};
    public int[] dy = new int[]{1, -1, 0, 0};
    public List<int[]> cheeses = new ArrayList<>();
    public boolean[][] visited;
    public int time = 0;


    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(infoToken.nextToken());
        m = Integer.parseInt(infoToken.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            infoToken = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(infoToken.nextToken());
                if (map[i][j] == 1) cheeses.add(new int[]{i, j});
            }
        }

        while (cheeses.size() != 0) {
            time++;
            visited = new boolean[n][m];
            dfs(0, 0);
            melting();
        }
        System.out.println(time);
    }

    private void melting() {
        for (int i = 0; i < cheeses.size(); i++) {
            int x = cheeses.get(i)[0];
            int y = cheeses.get(i)[1];
            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (map[nx][ny] == 2) {
                    cnt++;
                }
            }

            if (cnt >= 2) {
                map[x][y] = 0;
                cheeses.remove(i);
                i--;
            }
        }
    }

    private void dfs(int x, int y) {
        visited[x][y] = true;
        map[x][y] = 2;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (visited[nx][ny] || map[nx][ny] == 1) continue;
            dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws IOException {
        new three2638().solution();
    }
}
