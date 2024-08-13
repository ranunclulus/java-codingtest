package com.example.javacodingtest.swea;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.13
 @link https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
 @timecomplex
 @performance 25192 249
 @category #while
 @note
 */
public class four1210 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static boolean[][] visited;


    public void solution() throws IOException {
        for (int test = 1; test <= 10 ; test++) {
            int testCase = Integer.parseInt(br.readLine());
            map = new int[100][100];
            visited = new boolean[100][100];
            int startCol = 0;
            int endCol = 0;
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < 100; i++) {
                if (map[99][i] == 2) {
                    endCol = i;
                }
            }
            int currentCol = endCol;
            int currentRow = 99;
            visited[currentRow][currentCol] = true;
            while (true) {
                if ((currentCol - 1) >= 0 && map[currentRow][currentCol - 1] == 1 && !visited[currentRow][currentCol - 1]) {
                    currentCol--;
                }
                else if ((currentCol + 1) < 100 && map[currentRow][currentCol + 1] == 1 && !visited[currentRow][currentCol + 1]) {
                    currentCol++;
                }
                else if (map[currentRow - 1][currentCol] == 1 && !visited[currentRow - 1][currentCol]) {
                    currentRow--;
                    if (currentRow == 0) {
                        startCol = currentCol;
                        break;
                    }
                }
                visited[currentRow][currentCol] = true;
            }
            sb.append('#').append(test).append(' ').append(startCol).append('\n');

        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new four1210().solution();
    }
}
