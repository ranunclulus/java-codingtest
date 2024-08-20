package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.20
 @link https://www.acmicpc.net/problem/16926
 @timecomplex
 @performance 40016KB, 924MS
 @category
 @note
 */
public class five16926 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int r;
    static int[][] map;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < r; i++) {
            circle(0, 0, n - 1, m - 1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private void circle(int startRow, int startCol, int endRow, int endCol) {
        // 왼쪽
        int left = map[endRow][startCol];
        for (int i = endRow; i > startRow ; i--) {
            map[i][startCol] = map[i - 1][startCol];
        }

        // 아래쪽
        int bottom = map[endRow][endCol];
        for (int i = endCol; i > startCol ; i--) {
            map[endRow][i] = map[endRow][i - 1];
        }
        map[endRow][startCol + 1] = left;

        // 오른쪽
        int right = map[startRow][endCol];
        for (int i = startRow; i <endRow; i++) {
            map[i][endCol] = map[i + 1][endCol];
        }
        map[endRow - 1][endCol] = bottom;

        // 위쪽
        for (int i = startCol; i < endCol; i++) {
            map[startRow][i] = map[startRow][i + 1];
        }
        map[startRow][endCol - 1] = right;

        if ((startRow + 1 < endRow - 1) && (startCol + 1 < endCol - 1)) {
            circle(startRow + 1, startCol + 1, endRow - 1, endCol - 1);
        }
    }



    public static void main(String[] args) throws IOException {
        new five16926().solution();
    }
}


/*
5 4 1
1 2 3 4
7 8 9 10
13 14 15 16
19 20 21 22
25 26 27 28
 */
