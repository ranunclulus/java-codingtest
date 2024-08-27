package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.28
 @link https://www.acmicpc.net/problem/15683
 @timecomplex
 @performance 40668 KB	344 MS
 @category
 @note
 */

public class five13023 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static private int answer = 0;
    static private int n;
    static private int m;
    static private List<Integer>[] relationships;
    static private boolean[] visited;
    static private int[] distance;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        relationships = new List[n];
        for (int i = 0; i < n; i++) {
            relationships[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int person1 = Integer.parseInt(st.nextToken());
            int person2 = Integer.parseInt(st.nextToken());
            relationships[person1].add(person2);
            relationships[person2].add(person1);
        }


        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            distance = new int[n];
            dfs(i);
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
    }

    private void dfs(int index) {
        visited[index] = true;
        for (int next : relationships[index]) {
            if (!visited[next]) {
                distance[next] = distance[index] + 1;
                if (distance[next] >= 4) {
                    answer = 1;
                    return;
                }
                dfs(next);
                visited[next] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new five13023().solution();
    }
}
