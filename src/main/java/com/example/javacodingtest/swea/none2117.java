package com.example.javacodingtest.swea;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/*
 @author ranuinclulus
 @since 2024.09.03
 @link
 @timecomplex
 @performance 24816 KB, 257 MS
 @category
 @note
 */
public class none2117 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum;
    static int n; // 도시의 크기
    static int m; // 집이 지불할 수 있는 비용
    static int[][] map;
    static List<Home> homes;
    static int maxHomeCount;


    class Home {
        int row;
        int col;

        public Home(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            map = new int[n][n];
            homes = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                    if (map[i][j] == 1) homes.add(new Home(i, j));
                }

            }

            maxHomeCount = 0;
            prevent();

            builder.append("#").append(test).append(" ").append(maxHomeCount).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public void prevent() {
        int k = n + 2;
        while (k --> 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int count = 0;
                    for(Home home : homes) {
                        int distance = Math.abs(i - home.row) + Math.abs(j - home.col);
                        if (distance < k) count++;
                    }
                    if ((k * k + (k -1) * (k - 1)) <= count * m) maxHomeCount = Math.max(maxHomeCount, count);
                }

            }
        }
    }


    public static void main(String[] args) throws IOException {
        new none2117().solution();
    }
}

/*

1
8 3
0 0 0 0 0 1 0 0
0 1 0 1 0 0 0 1
0 0 0 0 0 0 0 0
0 0 0 1 0 1 0 0
0 0 1 1 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 1 0 1 0
1 0 0 0 0 0 0 0
 */
