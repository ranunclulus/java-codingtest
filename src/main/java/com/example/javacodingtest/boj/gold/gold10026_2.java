package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.30
 @link https://www.acmicpc.net/problem/10026
 @timecomplex
 @performance 15280 KB, 112 MS
 @category
 @note
 */
public class gold10026_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[][] cowArt;
    static int[][] notCowArt;
    static boolean[][] visited;
    static int cowArtCnt;
    static int notCowArtCnt;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());

        cowArt = new int[n][n];
        notCowArt = new int[n][n];

        // R - 1, G - 2, B - 3
        // R - 1, G - 1, B - 2
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                switch (input[j]) {
                    case 'R':
                        cowArt[i][j] = 1;
                        notCowArt[i][j] = 1;
                        break;
                    case 'G':
                        cowArt[i][j] = 1;
                        notCowArt[i][j] = 2;
                        break;
                    case 'B':
                        cowArt[i][j] = 2;
                        notCowArt[i][j] = 3;
                        break;
                }
            }
        }

        // 적록색약X의 검사
        visited = new boolean[n][n];
        notCowArtCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;
                notCowArtCnt++;
                bfs(i, j, notCowArt[i][j], notCowArt);
            }
        }

        // 적록색약의 검사
        visited = new boolean[n][n];
        cowArtCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;
                cowArtCnt++;
                bfs(i, j, cowArt[i][j], cowArt);
            }
        }
        sb.append(notCowArtCnt).append(" ").append(cowArtCnt);
        bw.write(sb.toString());
        bw.flush();
    }


    private void bfs(int row, int col, int value, int[][] map) {
        Deque<int[]> toVisit = new ArrayDeque<>();
        toVisit.add(new int[] {row, col});
        visited[row][col] = true;

        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = now[0] + deltas[i][0];
                int nextCol = now[1] + deltas[i][1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
                if (visited[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] != value) continue;

                toVisit.add(new int[] {nextRow, nextCol});
                visited[nextRow][nextCol] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new gold10026_2().solution();
    }
}


/*
5
R R R B B
G G B B B
B B B R R
B B R R R
R R R R R
 */
