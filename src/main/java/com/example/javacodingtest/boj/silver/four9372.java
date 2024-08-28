package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.28
 @link https://www.acmicpc.net/problem/9372
 @timecomplex
 @performance 61216 KB, 440 MS
 @category
 @note
 */
public class four9372 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int m;
    static List<List<Integer>> flights;
    static boolean[] visited;
    static int min;
    static int count;

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 0; test < testNum; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            flights = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                flights.add(new LinkedList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken()) - 1;
                int second = Integer.parseInt(st.nextToken()) - 1;

                flights.get(first).add(second);
                flights.get(second).add(first);
            }
//
//            min = 1001;
//            for (int i = 0; i < n; i++) {
//                visited = new boolean[n];
//                count = 0;
//                bfs(i);
//            }
            sb.append(n - 1).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void bfs(int flight) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {flight, 0});
        visited[flight] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            count = now[1];
            for(int next : flights.get(now[0])) {
                if (visited[next]) continue;
                queue.add(new int[] {next, count + 1});
                visited[next] = true;
            }
        }
        min = Math.min(count * 2, min);
    }


    public static void main(String[] args) throws IOException {
        new four9372().solution();
    }
}
