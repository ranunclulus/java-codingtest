package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.10
 @link
 @timecomplex
 @performance 14296	104
 @category
 @note
 */
public class four3055_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int colNum, rowNum;
    static char[][] board;
    static boolean[][] visited;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        colNum = Integer.parseInt(tokenizer.nextToken());
        rowNum = Integer.parseInt(tokenizer.nextToken());
        board = new char[colNum][rowNum];
        visited = new boolean[colNum][rowNum];


        Queue<int[]> nextVisit = new LinkedList<>();
        Queue<int[]> nextWater = new LinkedList<>();
        for (int i = 0; i < colNum; i++) {
            board[i] = reader.readLine().toCharArray();
            for (int j = 0; j < rowNum; j++) {
                if (board[i][j] == 'S')
                    nextVisit.add(new int[]{i, j});
                else if (board[i][j] == '*')
                    nextWater.add(new int[]{i, j});
            }
        }

        boolean success = false;
        int answer = 0;
        while (!success) {
            answer += 1;

            Queue<int[]> thisWater = nextWater;
            nextWater = new LinkedList<>();
            while (!thisWater.isEmpty()) {
                int[] now = thisWater.poll();
                for (int[] delta: deltas) {
                    int nextCol = now[0] + delta[0];
                    int nextRow = now[1] + delta[1];
                    if (checkBounds(nextCol, nextRow)
                            && (board[nextCol][nextRow] == '.' || board[nextCol][nextRow] == 'S')
                    ) {
                        board[nextCol][nextRow] = '*';
                        nextWater.add(new int[]{nextCol, nextRow});
                    }
                }
            }

            Queue<int[]> thisVisit = nextVisit;
            nextVisit = new LinkedList<>();
            while (!thisVisit.isEmpty()) {
                int[] now = thisVisit.poll();
                for (int[] delta: deltas) {
                    int nextCol = now[0] + delta[0];
                    int nextRow = now[1] + delta[1];
                    if (checkBounds(nextCol, nextRow) && !visited[nextCol][nextRow]) {
                        if (board[nextCol][nextRow] == '.') {
                            visited[nextCol][nextRow] = true;
                            nextVisit.add(new int[]{nextCol, nextRow});
                        }
                        else if (board[nextCol][nextRow] == 'D') {
                            success = true;
                        }
                    }
                }
                if (success) break;
            }
            // 다음 방문할 곳이 없다면
            if (nextVisit.isEmpty()) {
                break;
            }
        }
        if (!success) builder.append("KAKTUS");
        else builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    private boolean checkBounds(int col, int row) {
        return (-1 < col && col < colNum && -1 < row && row < rowNum);
    }

    public static void main(String[] args) throws IOException {
        new four3055_2().solution();
    }
}
