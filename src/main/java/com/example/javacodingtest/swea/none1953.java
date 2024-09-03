package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;


/*
 @author ranuinclulus
 @since 2024.09.03
 @link
 @timecomplex
 @performance 26368 KB, 148 MS
 @category
 @note
 */
public class none1953 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum;
    static int n, m, r, c, l;
    static int[][] map;
    static List<int[][]> tunnels;
    static int count;
    static boolean[][] visited;
    public void solution() throws IOException {
        initTunnels();
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            count = 0;
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken()); // 세로
            m = Integer.parseInt(tokenizer.nextToken()); // 가로
            r = Integer.parseInt(tokenizer.nextToken()); // 맨홀 세로
            c = Integer.parseInt(tokenizer.nextToken()); // 맨홀 가로
            l = Integer.parseInt(tokenizer.nextToken()); // 탈주 후 시간

            map = new int[n][m];
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            Deque<int[]> toVisit = new ArrayDeque<>();
            visited = new boolean[n][m];
            toVisit.add(new int[] {r, c, 1});
            visited[r][c] = true;
            count++;

            while (!toVisit.isEmpty()) {
                int[] now = toVisit.poll();
                int tunnerIndex = map[now[0]][now[1]] - 1;
                if (now[2] == l) continue;

                for(int[] delta : tunnels.get(tunnerIndex)) {
                    int nextRow = now[0] + delta[0];
                    int nextCol = now[1] + delta[1];


                    if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
                    if (visited[nextRow][nextCol]) continue;
                    if (map[nextRow][nextCol] == 0) continue;
                    if (!connect(now[0], now[1], nextRow, nextCol)) continue;

                    toVisit.add(new int[] {nextRow, nextCol, now[2] + 1});
                    visited[nextRow][nextCol] = true;
                    count++;
                }
            }



            builder.append("#").append(test).append(" ").append(count).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private boolean connect(int row, int col, int nextRow, int nextCol) {
        int tunnerIndex = map[nextRow][nextCol] - 1;

        for(int[] delta : tunnels.get(tunnerIndex)) {
            int destRow = nextRow + delta[0];
            int destCol = nextCol + delta[1];

            if (destRow == row && destCol == col) return true;
        }
        return false;
    }

    private void initTunnels() {
        tunnels = new LinkedList<>();
        tunnels.add(new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}});
        tunnels.add(new int[][] {{-1, 0}, {1, 0}});
        tunnels.add(new int[][] {{0, -1}, {0, 1}});
        tunnels.add(new int[][] {{-1, 0}, {0, 1}});
        tunnels.add(new int[][] {{1, 0}, {0, 1}});
        tunnels.add(new int[][] {{1, 0}, {0, -1}});
        tunnels.add(new int[][] {{-1, 0}, {0, -1}});
    }


    public static void main(String[] args) throws IOException {
        new none1953().solution();
    }
}

/*
1
5 6 2 2 6
3 0 0 0 0 3
2 0 0 0 0 6
1 3 1 1 3 1
2 0 2 0 0 2
0 0 4 3 1 1

 */
