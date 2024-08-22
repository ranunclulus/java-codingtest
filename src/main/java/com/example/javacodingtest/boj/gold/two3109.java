package boj;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.22
 @link https://www.acmicpc.net/problem/3109
 @timecomplex
 @performance 49572 KB, 344 MS
 @category
 @note
 */
public class two3109 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int r;
    static int c;
    static char[][] map;
    static boolean[][] visited;
    static int count;
    static int[][] deltas = new int[][] {{-1, 1}, {0, 1}, {1, 1}};

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < r; i++) {
            if (booleanDfs(i, 0)) {
                count++;
            }
        }

        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
    }

    private boolean booleanDfs(int row, int col) {
        if (row < 0 || row >= r || col < 0 || col >= c) return false;
        if (map[row][col] != '.') return false;
        if (col == c - 1) return true;


        map[row][col] = 'P';
        return (booleanDfs(row - 1, col + 1) || booleanDfs(row , col + 1) || booleanDfs(row + 1, col + 1));
    }


    public static void main(String[] args) throws IOException {
        new two3109().solution();
    }
}
