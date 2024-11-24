package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.22
 @link https://www.acmicpc.net/problem/21609
 @timecomplex
 @performance 18704kb 180ms
 @category
 @note
 */
public class two21609 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;

    class Group implements Comparable<Group> {
        int row;
        int col;
        int rainbowCount;
        int totalCount;

        public Group(int row, int col, int rainbowCount, int totalCount) {
            this.row = row;
            this.col = col;
            this.rainbowCount = rainbowCount;
            this.totalCount = totalCount;
        }

        @Override
        public int compareTo(Group o) {
            if (this.totalCount == o.totalCount) {
               if (this.rainbowCount == o.rainbowCount) {
                   if (this.row == o.row) {
                       return -Integer.compare(this.col, o.col);
                   }
                   return -Integer.compare(this.row, o.row);
               }
                return -Integer.compare(this.rainbowCount, o.rainbowCount);
            }
            return -Integer.compare(this.totalCount, o.totalCount);
        }
    }

    static int n, m;
    static boolean[][] visited;
    static int[][] map;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static List<Group> groups;
    static int answer;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        while(true) {
            if (!findGroups()) break;
            gravity();
            rotation();
            gravity();
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    private void rotation() {
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[n - j - 1][i] = map[i][j];
            }
        }
        map = temp;
    }

    private void gravity() {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] >= 0) {
                    int nextRow = i;
                    while (true) {
                        nextRow++;
                        if (nextRow >= n || map[nextRow][j] != -2) break;
                    }
                    nextRow--;
                    if (nextRow != i) {
                        map[nextRow][j] = map[i][j];
                        map[i][j] = -2;
                    }
                }
            }
        }
    }

    private boolean findGroups() {
        groups = new LinkedList<>();
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    bfs(i, j, true);
                }
            }
        }
        visited = new boolean[n][n];
        if (groups.isEmpty()) return false;
        else {
            Collections.sort(groups);
            bfs(groups.get(0).row, groups.get(0).col, false);
            removeBlock();
        }
        return true;
    }

    private void removeBlock() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    count++;
                    map[i][j] = -2;
                }
            }
        }
        answer += (int) Math.pow(count, 2);
    }

    private void bfs(int row, int col, boolean flag) {
        int rainbow = 0;
        int total = 1;
        visited[row][col] = true;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {row, col});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = now[0] + deltas[i][0];
                int nextCol = now[1] + deltas[i][1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
                if (visited[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] == map[row][col] || map[nextRow][nextCol] == 0) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[] {nextRow, nextCol});
                    if (map[nextRow][nextCol] == 0) rainbow++;
                    total++;
                }
            }
        }
        if (total >= 2) groups.add(new Group(row, col, rainbow, total));

        if (flag) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 0) visited[i][j] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two21609().solution();
    }
}
