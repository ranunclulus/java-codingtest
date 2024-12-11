package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.12.10
 @link https://www.acmicpc.net/problem/16235
 @timecomplex
 @performance
 @category
 @note
 */
public class three16235 {
    class Tree {
        int row;
        int col;
        int age;
        boolean alived;

        public Tree(int row, int col, int age) {
            this.row = row;
            this.col = col;
            this.age = age;
            this.alived = true;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "row=" + row +
                    ", col=" + col +
                    ", age=" + age +
                    ", alived=" + alived +
                    '}';
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k, answer;
    static int[][] map;
    static int[][] s2d2;
    static int[][] treeCount;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static List<Tree> trees;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        map = new int[n][n];
        s2d2 = new int[n][n];
        treeCount = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                s2d2[i][j] = Integer.parseInt(tokenizer.nextToken());
                map[i][j] = 5;
            }
        }

        trees = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            int age = Integer.parseInt(tokenizer.nextToken());
            trees.add(new Tree(row, col, age));
            treeCount[row][col]++;
        }

        while (k --> 0) {
            spring();
            summer();
            fall();
            winter();
        }

        for(Tree tree : trees) {
            if (tree.alived) answer++;
        }

        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();

    }

    private void print() {
        System.out.println("==========map의 상태==========");
        for(int[] row : map) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        System.out.println("===========trees===========");
        for(Tree tree : trees) {
            System.out.println(tree.toString());
        }
        System.out.println();
        System.out.println();
    }

    /*
    봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
    각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
    하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
    만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
     */
    private void spring() {
        boolean[] visited = new boolean[trees.size()];
        for (int i = 0; i < trees.size(); i++) {
            if (visited[i]) continue;
            int currentRow = trees.get(i).row;
            int currentCol = trees.get(i).col;
            List<Tree> candidate = new LinkedList<>();
            candidate.add(trees.get(i)); // 이번 순회 나무 넣기
            visited[i] = true;

            if (treeCount[currentRow][currentCol] != 1) { // 그 칸에 나무가 하나가 아니라면
                for (int j = i + 1; j < trees.size(); j++) { // i 번째 뒤를 돌면서 같은 칸에 있는 나무 넣기
                    if (trees.get(j).row == currentRow && trees.get(j).col == currentCol) {
                        candidate.add(trees.get(j));
                        visited[j] = true;
                    }
                }
            }

            Collections.sort(candidate, new Comparator<Tree>() { // 나이가 어린 순으로 정렬
                @Override
                public int compare(Tree o1, Tree o2) {
                    return Integer.compare(o1.age, o2.age);
                }
            });

            for(Tree tree : candidate) {
                if (map[currentRow][currentCol] < tree.age) {
                    tree.alived = false;
                    treeCount[currentRow][currentCol]--;
                    break;
                } else {
                    map[currentRow][currentCol] -= tree.age;
                    tree.age++;
                }
            }
        }
    }

    /*
    여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
    각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
    소수점 아래는 버린다.
     */
    private void summer() {
        List<Tree> deleteList = new LinkedList<>();
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (!tree.alived) {
                map[tree.row][tree.col] += (tree.age / 2);
                deleteList.add(tree);
            }
        }

        for (int i = 0; i < deleteList.size(); i++) {
            trees.remove(deleteList.get(i));
        }
    }

    /*
    가을에는 나무가 번식한다.
    번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
    어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
    상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
     */
    private void fall() {
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (tree.age % 5 != 0) continue;
            for (int j = 0; j < 8; j++) {
                int nextRow = tree.row + deltas[j][0];
                int nextCol = tree.col + deltas[j][1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;

                trees.add(new Tree(nextRow, nextCol, 1));
                treeCount[nextRow][nextCol]++;
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
