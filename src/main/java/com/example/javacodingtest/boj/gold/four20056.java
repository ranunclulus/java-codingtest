package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.23
 @link https://www.acmicpc.net/problem/20056
 @timecomplex
 @performance
 @category
 @note
 */
public class four20056 {
    class Fireball {
        int row;
        int col;
        int weight;
        int direction;
        int speed;

        public Fireball(int row, int col, int weight, int direction, int speed) {
            this.row = row;
            this.col = col;
            this.weight = weight;
            this.direction = direction;
            this.speed = speed;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k;
    static int[][] deltas = new int[][]
            {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static int[][] map;
    static List<Fireball> fireballs;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][n];
        fireballs = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            Fireball fireball = new Fireball(
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()));
            fireballs.add(fireball);
        }

        while (k --> 0) {

        }

    }

    public static void main(String[] args) throws IOException {
        new four20056().solution();
    }
}
