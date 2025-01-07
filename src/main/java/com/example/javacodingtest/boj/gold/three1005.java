package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2025.01.07
 @link https://www.acmicpc.net/problem/1005
 @timecomplex
 @performance 243736kb 740ms
 @category
 @note
 */
public class three1005 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum;
    static int n, k, x, y, totalTime, w;
    static int[] time, inDegree, result;
    static List<List<Integer>> adjList;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 0; test < testNum; test++) {
            totalTime = 0;
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            k = Integer.parseInt(tokenizer.nextToken());

            adjList = new ArrayList<List<Integer>>();
            inDegree = new int[n];
            time = new int[n];
            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                time[i] = Integer.parseInt(tokenizer.nextToken());
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < k; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                x = Integer.parseInt(tokenizer.nextToken()) - 1;
                y = Integer.parseInt(tokenizer.nextToken()) - 1;
                inDegree[y]++;
                adjList.get(x).add(y);
            }

            w = Integer.parseInt(reader.readLine()) - 1;

            result = new int[n];
            Deque<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                    result[i] = time[i];
                }
            }

            while(!queue.isEmpty()) {
                int now = queue.poll();

                for(int next : adjList.get(now)) {
                    inDegree[next]--;
                    result[next] = Math.max(result[next], result[now] + time[next]);
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }

            }
            builder.append(result[w]).append("\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three1005().solution();
    }
}
