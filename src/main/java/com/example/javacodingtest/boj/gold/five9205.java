package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.10
 @link
 @timecomplex
 @performance 15840kb 128ms
 @category
 @note
 */
public class five9205 {
    class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n;
    static Position[] positions;
    static int[][] canGo;
    static boolean[] visited;
    static boolean possible;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 0; test < testNum; test++) {
            n = Integer.parseInt(reader.readLine()) + 2;

            positions = new Position[n];
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                positions[i] = new Position(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            }

            canGo = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int distance = Math.abs(positions[i].x - positions[j].x) + Math.abs(positions[i].y - positions[j].y);
                    if (distance > 1000) continue;
                    canGo[i][j] = distance;
                    canGo[j][i] = distance;
                }
            }


            possible = false;
            visited = new boolean[n];
            Deque<Integer> toVisit = new ArrayDeque<>();
            toVisit.add(0);
            visited[0] = true;

            while(!toVisit.isEmpty()) {
                int now = toVisit.poll();
                if (now == n -1) {
                    possible = true;
                    break;
                }

                for (int i = 0; i < n; i++) {
                    if (visited[i]) continue;
                    if (canGo[now][i] != 0) {
                        toVisit.add(i);
                        visited[i] = true;
                    }
                }
            }

            builder.append(possible ? "happy" : "sad").append("\n");
        }
        writer.write(builder.toString());
        writer.flush();;
    }



    public static void main(String[] args) throws IOException {
        new five9205().solution();
    }
}
