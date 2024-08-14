package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.14
 @link https://www.acmicpc.net/problem/18418
 @timecomplex
 @performance 22708 KB 152 MS
 @category #backtracking
 @note
 */
public class five18428 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static char[][] map;
    static boolean[][] visited;
    static int[][] pop;
    static int[][] delta = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean success = false;
    static List<int[]> teachers = new ArrayList<>();

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visited = new boolean[n][n];
        pop = new int[3][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') {
                    teachers.add(new int[] {i, j});
                }
            }
        }

        dfs(0, 0);
        if (!success) sb.append("NO");
        bw.write(sb.toString());
        bw.flush();
    }

    private void dfs(int depth, int index) throws IOException {
        if (depth == 3) {
            success = isComplete();
            if (success) {
                sb.append("YES");
                bw.write(sb.toString());
                bw.flush();
                System.exit(0);
            }
            return;
        }
        for (int i = index; i < n * n; i++) {
            int row = i / n;
            int col = i % n;
            if (!visited[row][col]) {
                if (map[row][col] == 'X') {
                    visited[row][col] = true;
                    pop[depth][0] = row;
                    pop[depth][1] = col;
                    dfs(depth + 1, index + 1);
                    visited[row][col] = false;
                }
            }
        }
    }


    private boolean isComplete() {
        for (int[] choose : pop) {
            map[choose[0]][choose[1]] = 'O';
        }

        for (int[] teacher : teachers) {
            boolean[][] check = new boolean[n][n];
            for (int dir = 0; dir < 4; dir++) {
                for (int i = 1; i < n; i++) {
                    int row = teacher[0] + (delta[dir][0] * i);
                    int col = teacher[1] + (delta[dir][1] * i);

                    if (row < 0 || row >= n || col < 0 || col >= n) continue;
                    if (check[row][col]) continue;

                    check[row][col] = true;
                    if (map[row][col] == 'O') break;

                    if (map[row][col] == 'S') {
                        for (int[] choose : pop) {
                            map[choose[0]][choose[1]] = 'X';
                        }
                        return false;
                    }
                }
            }
        }

        for (int[] choose : pop) {
            map[choose[0]][choose[1]] = 'X';
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        new five18428().solution();
    }
}
