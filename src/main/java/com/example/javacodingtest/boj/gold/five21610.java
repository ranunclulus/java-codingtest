package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.12.31
 @link https://www.acmicpc.net/problem/21610
 @timecomplex
 @performance 18540kb 204ms
 @category
 @note
 */
public class five21610 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, answer;
    static int[][] baskets;
    static int[][] raindrops;
    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int[][] deltas = new int[][] {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static Deque<int[]> clouds;
    static boolean[][] checked;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        baskets = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                baskets[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        raindrops = new int[m][2];
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            raindrops[i][0] = Integer.parseInt(tokenizer.nextToken()) - 1;
            raindrops[i][1] = Integer.parseInt(tokenizer.nextToken()) % n;
        }

        clouds = new ArrayDeque<>();
        // (N, 1), (N, 2), (N-1, 1), (N-1, 2)
        clouds.add(new int[] {n - 1, 0});
        clouds.add(new int[] {n - 1, 1});
        clouds.add(new int[] {n - 2, 0});
        clouds.add(new int[] {n - 2, 1});

        for(int[] raindrop : raindrops) {
            int d = raindrop[0];
            int s = raindrop[1];
            checked = new boolean[n][n];

            moveAndRain(d, s);
            waterCopyBug();
            makeNewClouds();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += baskets[i][j];
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    private void moveAndRain(int d, int s) {
        for(int[] cloud : clouds) {
            cloud[0] = ((cloud[0] + deltas[d][0] * s) + n) % n;
            cloud[1] = ((cloud[1] + deltas[d][1] * s) + n) % n;
            baskets[cloud[0]][cloud[1]]++;
        }
    }

    private void waterCopyBug() {
        while (!clouds.isEmpty()) {
            int[] cloud = clouds.poll();
            checked[cloud[0]][cloud[1]] = true;

            int fullCount = 0;
            for (int i = 1; i <= 7; i += 2) {
                int copyCol = cloud[0] + deltas[i][0];
                int copyRow = cloud[1] + deltas[i][1];

                if (copyCol < 0 || copyCol >= n || copyRow < 0 || copyRow >= n) continue;
                if (baskets[copyCol][copyRow] > 0) fullCount++;
            }
            baskets[cloud[0]][cloud[1]] += fullCount;
        }
    }

    private void makeNewClouds() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (checked[i][j]) continue;
                if (baskets[i][j] >= 2) {
                    clouds.add(new int[] {i, j});
                    baskets[i][j] -= 2;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five21610().solution();
    }
}


/*
[1, 1, 1, 0, 0]
[0, 1, 0, 1, 1]
[3, 2, 1, 7, 0]
[2, 1, 8, 13, 0]
[6, 6, 4, 3, 0]
 */


/*
[1, 1, 1, 0, 0]
[0, 1, 0, 1, 1]
[3, 2, 1, 7, 0]
[2, 1, 8, 13, 0]
[6, 6, 4, 3, 0]
 */
