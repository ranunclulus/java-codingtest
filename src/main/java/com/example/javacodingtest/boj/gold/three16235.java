package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.12.10
 @link https://www.acmicpc.net/problem/16235
 @timecomplex
 @performance 298008kb 776ms
 @category
 @note
 */
public class three16235 {
    class Tree implements Comparable<Tree>{
        int row;
        int col;
        int age;

        public Tree(int row, int col, int age) {
            this.row = row;
            this.col = col;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "row=" + row +
                    ", col=" + col +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k, answer;
    static int[][] map;
    static int[][] s2d2;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static List<Tree> trees, alived, dead;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][n];
        s2d2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                s2d2[i][j] = Integer.parseInt(tokenizer.nextToken());
                map[i][j] = 5;
            }
        }

        trees = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            int age = Integer.parseInt(tokenizer.nextToken());
            trees.add(new Tree(row, col, age));
        }

        while (k --> 0) {
            alived = new ArrayList<>();
            dead = new ArrayList<>();
            spring();
            summer();
            fall();
            winter();
            trees = alived;
        }

        builder.append(trees.size());
        writer.write(builder.toString());
        writer.flush();

    }

    /*
    봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
    각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
    하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
    만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
     */
    private void spring() {
        Collections.sort(trees);
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (tree.age <= map[tree.row][tree.col]) {
                map[tree.row][tree.col] -= tree.age;
                tree.age++;
                alived.add(new Tree(tree.row, tree.col, tree.age));
            }
            else {
                dead.add(new Tree(tree.row, tree.col, tree.age));
            }
        }
    }

    /*
    여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
    각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
    소수점 아래는 버린다.
     */
    private void summer() {
        for (int i = 0; i < dead.size(); i++) {
            Tree tree = dead.get(i);
            map[tree.row][tree.col] += tree.age / 2;
        }
    }

    /*
    가을에는 나무가 번식한다.
    번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
    어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
    상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
     */
    private void fall() {
        for (int i = 0; i < alived.size(); i++) {
            Tree tree = alived.get(i);
            if (tree.age % 5 != 0) continue;
            for (int j = 0; j < 8; j++) {
                int nextRow = tree.row + deltas[j][0];
                int nextCol = tree.col + deltas[j][1];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;

                alived.add(new Tree(nextRow, nextCol, 1));
            }
        }
    }

    /*
    겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
    각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
     */
    private void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += s2d2[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new three16235().solution();
    }
}
