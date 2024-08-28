package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.28
 @link
 @timecomplex
 @performance 23872 KB, 127 MS
 @category
 @note
 */
public class four1238 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int testNum = 10;
    static int connectNum;
    static int startNode;
    static int[][] edges;
    static int queSize;
    static int maxValue;
    static Deque<Integer> queue;
    static boolean[] visited;


    public void solution() throws IOException {
        for (int test = 1; test <= testNum; test++) {
            st = new StringTokenizer(br.readLine());
            connectNum = Integer.parseInt(st.nextToken());
            startNode = Integer.parseInt(st.nextToken());
            edges = new int[101][101];
            visited = new boolean[101];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < connectNum / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edges[from][to] = 1;
            }
            queue = new ArrayDeque<>();
            queSize = 0;
            for (int i = 1; i <= 100; i++) {
                if (edges[startNode][i] == 1) {
                    queSize++;
                    queue.add(i);
                    visited[i] = true;
                }
            }

            while (queSize != 0) {
                maxValue = Integer.MIN_VALUE;
                for(int val : queue) {
                    maxValue = Math.max(maxValue, val);
                }

                int forCnt = queSize;
                queSize = 0;
                for (int i = 0; i < forCnt; i++) {
                    int now = queue.poll();
                    for (int j = 1; j <= 100; j++) {
                        if (edges[now][j] == 1 && !visited[j]) {
                            queSize++;
                            queue.add(j);
                            visited[j] = true;
                        }
                    }
                }
            }

            sb.append("#").append(test).append(" ").append(maxValue).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private int countOne() {
        int c = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (edges[i][j] == 1) c++;
            }
        }
        return c;
    }


    public static void main(String[] args) throws IOException {
        new four1238().solution();
    }
}


/*
24 2
2 7 11 6 6 2 2 15 15 4 4 2 4 10 7 1 1 7 1 8 1 17 3 22
 */


/*
#1 17
#2 96
#3 49
#4 39
#5 49
#6 1
#7 28
#8 45
#9 59
#10 64

 */
