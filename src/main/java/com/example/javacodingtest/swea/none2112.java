package com.example.javacodingtest.swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.21
 @link
 @timecomplex
 @performance 107416KB, 1785MS
 @category
 @note
 */
public class none2112 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int testNum;
    static int d;
    static int w;
    static int k;
    static int[][] films;
    static int count;
                                        // 상 하 좌 우
    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            // 입력
            count = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            films = new int[d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    films[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            recursive(0, 0);

            sb.append("#").append(test).append(" ").append(count).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void recursive(int depth, int start) {
        if (start > d) return;
        if (check()) {
            count = Math.min(count, depth);
            return;
        }
        for (int row = start; row < d; row++) {
            int[] target = films[row].clone();
            Arrays.fill(films[row], 0);
            recursive(depth + 1, row + 1);
            Arrays.fill(films[row], 1);
            recursive(depth + 1, row + 1);
            films[row] = target;
        }
    }

    private boolean check() {
        for (int col = 0; col < w; col++) {
            int count = 1;
            for (int row = 0; row < d - 1; row++) {
                if (count >= k) break;
                if (films[row][col] == films[row + 1][col]) {
                    count ++;
                }
                else {
                    count = 1;
                }
            }
            if (count < k) return false;
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        new none2112().solution();
    }
}
