package com.example.javacodingtest.boj.gold;

import java.util.*;
import java.io.*;

/*
 @author ranuinclulus
 @since 2025.06.24
 @link https://www.acmicpc.net/problem/3665
 @timecomplex 54216B 580MS
 @performance
 @category
 @note
 */
public class one3665 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int T, N, M;
    static int[] inDegrees;
    static boolean[][] edges;
    public void solution() throws IOException {
        T = Integer.parseInt(reader.readLine());
               
        while(T --> 0) {
            N = Integer.parseInt(reader.readLine());
            
            inDegrees = new int[N + 1];
            edges = new boolean[N + 1][N + 1];
          
            tokenizer = new StringTokenizer(reader.readLine());
            for(int i = 0; i < N; i++) {
                int num = Integer.parseInt(tokenizer.nextToken());
                inDegrees[num] = i;
              
                for(int j = 1; j <= N; j++) {
                    if (j != num && !edges[j][num]) edges[num][j] = true;
                }
            }
            M = Integer.parseInt(reader.readLine());
            for(int i = 0; i < M; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());

                if (!edges[a][b]) {
                    edges[a][b] = true;
                    edges[b][a] = false;
                    inDegrees[a]--;
                    inDegrees[b]++;
                 } else {
                    edges[a][b] = false;
                    edges[b][a] = true;
                    inDegrees[a]++;
                    inDegrees[b]--;
                }
            }

            writer.write(topologicalSort());
            writer.write('\n');
        }
        writer.close(); 
   }

   public String topologicalSort() {
        builder = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        builder = new StringBuilder();

        for(int i = 1; i <= N; i++) {
            if (inDegrees[i] == 0) queue.offer(i);
        }
     
        for(int i = 1; i <= N; i++) {
            if (queue.size() == 0) return "IMPOSSIBLE";
            if (queue.size() > 1) return "?";
            int current = queue.poll();
            builder.append(current).append(' ');

            for(int j = 1; j <= N; j++) {
                if(edges[current][j]) {
                    edges[current][j] = false;
                    if (--inDegrees[j] == 0) queue.offer(j);
                }
            }
        }
        return builder.toString();
   }

    public static void main(String[] args) throws IOException {
        new one3665().solution();
    }
}
