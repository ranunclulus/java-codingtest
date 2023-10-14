package com.example.javacodingtest.boj.silver;
// https://www.acmicpc.net/problem/21736

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class two21763 {
    public static int nRow;
    public static int nCol;
    public static char[][] campus;
    public static boolean[][] visited;
    public static int[] dRow = {1, -1, 0, 0};
    public static int[] dCol = {0, 0, 1, -1};

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        nCol = Integer.parseInt(infoToken.nextToken());
        nRow = Integer.parseInt(infoToken.nextToken());
        campus = new char[nCol][nRow];
        visited = new boolean[nCol][nRow];

        int startCol = 0;
        int startRow = 0;

        for (int i = 0; i < nCol; i++) {
            String campusToken = reader.readLine();
            for (int j = 0; j < nRow; j++) {
                campus[i][j] = campusToken.charAt(j);
                if (campus[i][j] == 'I') {
                    startCol = i;
                    startRow = j;
                }
            }
        }

        int meeting = 0;

        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.offer(new int[] {startCol, startRow});
        visited[startCol][startRow] = true;

        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();

            for (int i = 0; i < 4; i++) {
                int nextCol = now[0] + dCol[i];
                int nextRow = now[1] + dRow[i];

                if (nextCol < 0 || nextCol >= nCol || nextRow < 0 || nextRow >= nRow) continue;
                if (visited[nextCol][nextRow]) continue;
                if (campus[nextCol][nextRow] == 'X') continue;

                if (campus[nextCol][nextRow] == 'P') meeting++;
                toVisit.offer(new int[] {nextCol, nextRow});
                visited[nextCol][nextRow] = true;
            }
        }

        if (meeting == 0) {
            System.out.println("TT");
        } else {
            System.out.println(meeting);
        }
    }

    public static void main(String[] args) throws IOException {
        new two21763().solution();
    }
}
