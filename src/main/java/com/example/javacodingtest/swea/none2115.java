package com.example.javacodingtest.swea;
/*
 @author ranuinclulus
 @since 2024.09.02
 @link
 @timecomplex
 @performance 31960 KB, 166 MS
 @category
 @note
 */

import java.io.*;
import java.util.StringTokenizer;

public class none2115 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum;
    static int n, m, c;
    static int[][] map;
    static int[][] bees;
    static int result;
    static int subsetMax;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            c = Integer.parseInt(tokenizer.nextToken());
            map = new int[n][n];
            bees = new int[2][2];

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(tokenizer.nextToken());
                }
            }
            result = Integer.MIN_VALUE;

            choice(0, 0);
            builder.append("#").append(test).append(" ").append(result).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void choice(int depth, int index) {
        if (depth == 2) {
            int maxOne = calculateHoney(bees[0][0], bees[0][1]);
            int maxTwo = calculateHoney(bees[1][0], bees[1][1]);
            result = Math.max(result, maxOne + maxTwo);
            return;
        }
        if (index >= n * n) return;
        int row = index / n;
        int col = index % n;

        if ((col + m) > n) choice(depth, (index + m) - (index + m) % n);
        else {
            bees[depth][0] = row;
            bees[depth][1] = col;
            choice(depth + 1, index + m);
            choice(depth, index + 1);
        }

    }



    private int calculateHoney(int row, int col) {
        int[] honey = new int[m];
        for (int i = 0; i < m; i++) {
            honey[i] = (map[row][col + i]);
        }

        subsetMax = Integer.MIN_VALUE;
        makeSubSet(0, new boolean[m], honey);

        return subsetMax;
    }

    private void makeSubSet(int depth, boolean[] visited, int[] honey) {
        if (depth == m) {
            int sum = 0;
            int totalCost = 0;
            for (int i = 0; i < m; i++) {
                if (visited[i]) {
                    sum += honey[i];
                    totalCost += (honey[i] * honey[i]);
                }
                if (sum > c) {
                    return;
                }
            }
            if (totalCost > subsetMax) {
                subsetMax = Math.max(totalCost, subsetMax);
            }
            return;
        }
        visited[depth] = true;
        makeSubSet(depth + 1, visited, honey);
        visited[depth] = false;
        makeSubSet(depth + 1, visited, honey);
    }

    public static void main(String[] args) throws IOException {
        new none2115().solution();
    }

}


/*
#1 174
#2 131
#3 145
#4 155
#5 166
#6 239
#7 166
#8 172
#9 291
#10 464
 */
