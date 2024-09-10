package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.10
 @link https://www.acmicpc.net/problem/15685
 @timecomplex
 @performance 14588kb, 116ms
 @category
 @note
 */
public class three15685 {


    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int x, y, d, g;
    static final int RIGHT = 0;
    static final int UP = 1;
    static final int LEFT = 2;
    static final int DOWN = 3;
    static final int LENGTH = 101;
    static boolean[][] visited = new boolean[LENGTH][LENGTH];

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            x = Integer.parseInt(tokenizer.nextToken());
            y = Integer.parseInt(tokenizer.nextToken());
            d = Integer.parseInt(tokenizer.nextToken());
            g = Integer.parseInt(tokenizer.nextToken());
            draw(x, y, getDirection(d, g));
        }
        builder.append(countDragonArea());
        writer.write(builder.toString());
        writer.flush();
    }

    private int countDragonArea() {
        int count = 0;
        for (int i = 0; i < LENGTH - 1; i++) {
            for (int j = 0; j < LENGTH - 1; j++) {
                if (visited[i][j] && visited[i + 1][j] && visited[i][j + 1] && visited[i + 1][j + 1]) count++;
            }
        }
        return count;
    }

    private void draw(int x, int y, List<Integer> directions) {
        visited[x][y] = true;
        for(int direction : directions) {
            switch (direction) {
                case RIGHT:
                    visited[++x][y] = true;
                    break;
                case UP:
                    visited[x][--y] = true;
                    break;
                case LEFT:
                    visited[--x][y] = true;
                    break;
                case DOWN:
                    visited[x][++y] = true;
                    break;
            }
        }
    }

    private List<Integer> getDirection(int d, int g) {
        List<Integer> directions = new LinkedList<>();
        directions.add(d);
        while(g --> 0) {
            int size = directions.size() - 1;
            for (int i = size; i >= 0; i--) {
                directions.add((directions.get(i) + 1) % 4);
            }
        }
        return directions;
    }


    public static void main(String[] args) throws IOException {
        new three15685().solution();
    }

}
