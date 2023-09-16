package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class four1987 {
    static int r, c;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int ans = 0;
    private boolean[] visit = new boolean[26];

    public void dfs(int x, int y, int count) {
        if (visit[map[x][y]]) {
            ans = Math.max(count, ans);
            return;
        }
        else {
            visit[map[x][y]] = true;
            for (int i = 0; i < 4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                if (cx >= 0 && cy >= 0 && cx < r && cy < c) {
                    dfs(cx, cy, count + 1);
                }
            }
            visit[map[x][y]] = false;
        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        r = Integer.parseInt(infoToken.nextToken());
        c = Integer.parseInt(infoToken.nextToken());

        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            String mapInfo = reader.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = mapInfo.charAt(j) - 'A';
            }
        }

        dfs(0, 0, 0);

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        new four1987().solution();
    }
}
