package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.07
 @link https://www.acmicpc.net/problem/15685
 @timecomplex
 @performance
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
    static int count;
    static int[][] deltas = new int[][] {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
                                        // 우 상 좌 하

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            x = Integer.parseInt(tokenizer.nextToken());
            y = Integer.parseInt(tokenizer.nextToken());
            d = Integer.parseInt(tokenizer.nextToken());
            g = Integer.parseInt(tokenizer.nextToken());

            List<int[]> dragons = new LinkedList<>();
            dragons.add(new int[] {x, y, d});
            for (int generation = 1; generation <= g; generation++) {
                System.out.printf("%d 세대\n", generation);
                System.out.println("============");
//                Deque<int[]> toVisitDragons = new ArrayDeque<>();
//                for (int j = 0; j < dragons.size(); j++) {
//                    toVisitDragons.add(dragons.get(j));
//                }
                int size = dragons.size();

                int nowX = dragons.get(size - 1)[0];
                int nowY = dragons.get(size - 1)[1];
                for(int j = 0; j < size; j++) {
                    int[] dragon = dragons.get(size - 1 - j);
                    int dragonDirection = dragon[2];

                    int newX = nowX + deltas[dragonDirection][0];
                    int newY = nowY + deltas[dragonDirection][1];
                    int newDirection = (dragonDirection + 1) % 4;

                    int[] newDragon = new int[] {newX, newY, newDirection};
                    if (!dragons.contains(newDragon)) {
                        dragons.add(newDragon);
                        nowX = newX;
                        nowY = newY;
                    }
                }
                printDragon(dragons);
                System.out.println("============");
                System.out.println();
                System.out.println();
            }
        }
        System.out.println();
    }

    private void printDragon(List<int[]> dragons) {
        for(int[] dra : dragons) {
            String dir = "";
            switch (dra[2]) {
                case 0:
                    dir = "우";
                    break;
                case 1:
                    dir = "상";
                    break;
                case 2:
                    dir = "좌";
                    break;
                case 3:
                    dir = "하";
                    break;
            }
            System.out.printf("x: %d, y: %d, direction: %s\n", dra[0], dra[1], dir);
        }
    }

    public static void main(String[] args) throws IOException {
        new three15685().solution();
    }

}
